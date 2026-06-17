package totvs.substituirpecas.application.usecase;

import totvs.substituirpecas.application.dto.transport.LogPlanilhaLinha;
import totvs.substituirpecas.application.dto.transport.Pedido;
import totvs.substituirpecas.application.dto.transport.PlanilhaResultado;
import totvs.substituirpecas.application.port.in.GerarPlanilhaUseCase;
import totvs.substituirpecas.application.port.out.TotvsPedidoPort;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaResultadoMaker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GerarPlanilhaService implements GerarPlanilhaUseCase {

    private final TotvsPedidoPort pedidosPort;
    private final PlanilhaResultadoMaker planilhaResultadoMaker;

    public GerarPlanilhaService(TotvsPedidoPort pedidosPort,
                                PlanilhaResultadoMaker planilhaResultadoMaker) {
        this.pedidosPort = pedidosPort;
        this.planilhaResultadoMaker = planilhaResultadoMaker;
    }


    @Override
    public byte[] gerarPlanilhaSubstituicao(List<LogPlanilhaLinha> planilhaLinhas) {

        Map<String, List<LogPlanilhaLinha>> map = planilhaLinhas.stream()
                .collect(Collectors.groupingBy(r -> gerarChave(
                        r.pedido(),
                        r.referenciaOriginal(),
                        r.tamanhoOriginal(),
                        r.corOriginal(),
                        r.referenciaDestino(),
                        r.tamanhoDestino(),
                        r.corDestino()
                        )));

        Map<String, LogPlanilhaLinha> mapFiltrado = map.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> escolherPorStatus(e.getValue())
                ));

        List<Integer> pedidos = mapFiltrado.values().stream()
                .map(LogPlanilhaLinha::pedido)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .toList();

        Map<Integer, Pedido> buscarPedidos = pedidos.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        pedidosPort::buscarPedido
                ));

        List<PlanilhaResultado> resultados = toPlanilhaResultado(buscarPedidos, mapFiltrado.values().stream().toList());

        return planilhaResultadoMaker.resultToExcel(resultados);
    }


    private static String gerarChave(Integer idPedido,
                                     String referenciaOriginal,
                                     String tamanhoOriginal,
                                     String corOriginal,
                                     String referenciaDestino,
                                     String tamanhoDestino,
                                     String corDestino){
        return String.valueOf( idPedido + referenciaOriginal + tamanhoOriginal + corOriginal + referenciaDestino + tamanhoDestino + corDestino );
    }

    private int prioridadePorStatus(String status){

        if (status.equalsIgnoreCase("Item alterado quantidade") ||
        status.equalsIgnoreCase("Item novo incluido")){
            return 1;
        }

        if (status.equalsIgnoreCase("Item foi adicionada quantidade anteriormente, não feita nenhuma modificação")){
            return 2;
        }
        return 99;
    }

    private LogPlanilhaLinha escolherPorStatus(List<LogPlanilhaLinha> linha){
        return linha.stream()
                .min(Comparator
                        .comparing((LogPlanilhaLinha l) -> prioridadePorStatus(l.status()))
                        .thenComparing(LogPlanilhaLinha::id, Comparator.nullsFirst(Long::compareTo))
                        .reversed())
                .orElseThrow();
    }

    private List<PlanilhaResultado> toPlanilhaResultado(Map<Integer, Pedido> mapPedidos, List<LogPlanilhaLinha> linha){

        List<PlanilhaResultado> planilhaResultado = new ArrayList<>();

        for (LogPlanilhaLinha logPlanilhaLinha : linha) {

            Pedido pedido = mapPedidos.get(logPlanilhaLinha.pedido());

            planilhaResultado.add(
                    new PlanilhaResultado(
                            pedido.orderCode(),
                            pedido.clientName(),
                            pedido.cnpj(),
                            logPlanilhaLinha.referenciaOriginal(),
                            logPlanilhaLinha.tamanhoOriginal(),
                            logPlanilhaLinha.corOriginal(),
                            logPlanilhaLinha.referenciaDestino(),
                            logPlanilhaLinha.tamanhoDestino(),
                            logPlanilhaLinha.corDestino())
            );

        }

        return planilhaResultado;

    }

}
