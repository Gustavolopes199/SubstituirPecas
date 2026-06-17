package totvs.substituirpecas.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import totvs.substituirpecas.application.dto.commands.*;
import totvs.substituirpecas.application.dto.transport.*;
import totvs.substituirpecas.application.port.in.PlanilhaSubstituicaoUseCase;
import totvs.substituirpecas.application.port.out.LogPlanilhaRepositoryPort;
import totvs.substituirpecas.application.port.out.SubstituicaoLogRepositoryPort;
import totvs.substituirpecas.application.port.out.TotvsPedidoPort;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoCommand;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoGroup;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoReader;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProcessarPlanilhaSubstituicaoService implements PlanilhaSubstituicaoUseCase {

    private final PlanilhaSubstituicaoReader reader;
    private final PlanilhaSubstituicaoGroup group;
    private final TotvsPedidoPort port;
    private final SubstituicaoLogRepositoryPort logPort;
    private final LogPlanilhaRepositoryPort portLog;

    public ProcessarPlanilhaSubstituicaoService(PlanilhaSubstituicaoReader reader,
                                                PlanilhaSubstituicaoGroup group,
                                                TotvsPedidoPort port,
                                                SubstituicaoLogRepositoryPort logPort,
                                                LogPlanilhaRepositoryPort portLog) {
        this.reader = reader;
        this.group = group;
        this.port = port;
        this.logPort = logPort;
        this.portLog = portLog;
    }


    @Override
    public void executar(PlanilhaSubstituicaoCommand command) {

        try(InputStream in = command.arquivo()) {

            List<PlanilhaLinha> linhas = reader.execute(in);

            atualizarPorLinha(linhas);

            List<PedidoAgrupado> pedidos = group.execute(linhas);

            /*
            for (PedidoAgrupado pedido : pedidos) {
                try{

                    Pedido resp = port.buscarPedidoCompleto(pedido.getPedidoId());

                    Map<String, ItemPedido> itensResponse = resp.items()
                            .stream()
                            .collect(Collectors.toMap(
                                    i -> chave(i.referenceCode(), i.colorName(), i.sizeName()),
                                    Function.identity(),
                                    (a, b) -> a
                            ));


                    List<ItemIncluirComand> itensIncluir = new ArrayList<>();
                    List<ItemCancelamentoCommand> itensCancel = new ArrayList<>();
                    List<AdcionarItemCommand> itensAdicionar = new ArrayList<>();

                    for (PedidoAgrupado.ItemSubstituir alvo : pedido.getItems()) {
                        String k = chave(alvo.getReferenciaOriginal(), alvo.getCor(), alvo.getTamanho());
                        ItemPedido item = itensResponse.get(k);

                        if (item == null) {
                            continue;
                        }

                        List<Produto> produtoList = port.buscarProduto(alvo.getReferenciaSubstituicao());


                        for (Produto produto : produtoList) {
                            if (alvo.getCorDestino().equalsIgnoreCase(produto.colorName()) && alvo.getTamanhoDestino().equalsIgnoreCase(produto.size())) {
                                boolean jaNoPedido = resp.items().stream()
                                        .anyMatch(it -> it.productCode().equals(produto.productCode()));


                                BigDecimal preco = port.buscarPreco(produto.productCode());

                                if (jaNoPedido) {

                                    itensAdicionar.add(
                                            new AdcionarItemCommand(
                                                    produto.productCode(),
                                                    item.quantity() + item.pendingQuantity()
                                            )
                                    );
                                } else {
                                    itensIncluir.add(new ItemIncluirComand(
                                            produto.productCode(),
                                            item.pendingQuantity(),
                                            preco
                                    ));
                                }
                                if(item.pendingQuantity > 0){
                                    itensCancel.add(new ItemCancelamentoCommand(
                                        item.productCode(), item.pendingQuantity()
                                ));
                                }

                            }
                        }

                    }

                    logPort.salvarLog(SalvarLogCommand.novo(
                            pedido.getPedidoId(),
                            itensIncluir.stream()
                                    .collect(Collectors.summingInt(r -> r.quantity())),
                            itensCancel.stream()
                                    .collect(Collectors.summingInt(r -> r.cancelQuantity())),
                            itensAdicionar.stream()
                                    .collect(Collectors.summingInt(r -> r.quantity()))
                    ));

                    if (itensIncluir.isEmpty()) {
                        IncluirItemCommand incluirItemCommand = new IncluirItemCommand(pedido.getPedidoId(), itensIncluir);
                        port.inserirItem(incluirItemCommand);
                    }
                    if (!itensCancel.isEmpty()) {
                        CancelarItemCommand cancelarItemCommand = new CancelarItemCommand(pedido.getPedidoId(), itensCancel);
                        port.cancelarItem(cancelarItemCommand);
                    }
                    if (!itensAdicionar.isEmpty()) {
                        AdcionarCommand adcionarCommand = new AdcionarCommand(pedido.getPedidoId(), itensAdicionar);
                        port.adcionarQuantidade(adcionarCommand);
                    }

                } catch (Exception e) {
                    log.info("Erro no pedido: {}", pedido.getPedidoId());
                }
            }
            */

        } catch (IOException e){

        }
    }


    public void atualizarPorLinha(List<PlanilhaLinha> listaDeItens){

        log.info("Iniciando execução em: {}", LocalDateTime.now());
        log.info("Quantidade de linhas: {}", listaDeItens.size());

        Integer counter = 0;

        List<Integer> pedidosEmSugestao = port.buscarSugestoes(1L);

        log.info("Quantidade de pedidos com sugestão em andamento: {}", pedidosEmSugestao.size());


        for (PlanilhaLinha iterator : listaDeItens) {
            counter++;
            log.info("Produto: {}", counter);

            if (pedidosEmSugestao.contains(iterator.getPedido())){
                portLog.salvarLog(new LogPlanilhaLinha(null,
                        iterator.getPedido(),
                        iterator.getReferenciaOriginal(),
                        iterator.getCorOriginal(),
                        iterator.getTamanhoOriginal(),
                        0,
                        iterator.getReferenciaDestino(),
                        iterator.getCorDestino(),
                        iterator.getTamanhoDestino(),
                        null,
                        0,
                        "Nao alterado, esta em sugestao"));
                continue;
            }

            try{

                Pedido pedido = port.buscarPedidoCompleto(iterator.getPedido());

                Map<String, ItemPedido> itensResponse = pedido.items()
                        .stream()
                        .collect(Collectors.toMap(
                                i -> chave(i.referenceCode(), i.colorName(), i.sizeName()),
                                Function.identity(),
                                (a, b) -> a
                        ));

                ItemPedido itemResponse = itensResponse.get(chave(iterator.getReferenciaOriginal(),
                        iterator.getCorOriginal(), iterator.getTamanhoOriginal()));

                ItemPedido itemDestinoPedido = itensResponse.get(chave(iterator.getReferenciaDestino(),
                        iterator.getCorDestino(), iterator.getTamanhoDestino()));

                if (itemResponse == null){
                    portLog.salvarLog(new LogPlanilhaLinha(null,
                            iterator.getPedido(),
                            iterator.getReferenciaOriginal(),
                            iterator.getCorOriginal(),
                            iterator.getTamanhoOriginal(),
                            null,
                            iterator.getReferenciaDestino(),
                            iterator.getCorDestino(),
                            iterator.getTamanhoDestino(),
                            null,
                            0,
                            "Item original nao encontrado no pedido"));
                    continue;
                }
               if (iterator.getQuantidadeDestino() > itemResponse.pendingQuantity()){
                   portLog.salvarLog(new LogPlanilhaLinha(null,
                           iterator.getPedido(),
                           iterator.getReferenciaOriginal(),
                           iterator.getCorOriginal(),
                           iterator.getTamanhoOriginal(),
                           null,
                           iterator.getReferenciaDestino(),
                           iterator.getCorDestino(),
                           iterator.getTamanhoDestino(),
                           null,
                           0,
                           "Quantidade pendente do item na totvs menor da quantidade pendente fornecida"));
                   continue;
               }

                if (itemResponse.pendingQuantity() == 0 && (itemResponse.quantidadeCancelada() == null || itemResponse.quantidadeCancelada() == 0)){
                    portLog.salvarLog(new LogPlanilhaLinha(null,
                            iterator.getPedido(),
                            iterator.getReferenciaOriginal(),
                            iterator.getCorOriginal(),
                            iterator.getTamanhoOriginal(),
                            null,
                            iterator.getReferenciaDestino(),
                            iterator.getCorDestino(),
                            iterator.getTamanhoDestino(),
                            null,
                            0,
                            "Item nao esta pendente no pedido"));
                    continue;
                }


                if (itemDestinoPedido == null){

                    List<Produto> produto = port.buscarProduto(iterator.getReferenciaDestino());

                    if (produto.isEmpty()){
                        portLog.salvarLog(new LogPlanilhaLinha(null,
                                iterator.getPedido(),
                                iterator.getReferenciaOriginal(),
                                iterator.getCorOriginal(),
                                iterator.getTamanhoOriginal(),
                                itemResponse.pendingQuantity(),
                                iterator.getReferenciaDestino(),
                                iterator.getCorDestino(),
                                iterator.getTamanhoDestino(),
                                null,
                                0,
                                "Referencia destino nao encontrada no Totvs Moda"));
                        continue;
                    }

                    Map<String, Produto> mapProduto = produto.stream()
                            .collect(Collectors.toMap(r -> chave(r.ReferenceCode(), r.colorName(), r.size()),
                                    Function.identity(),
                                    (a, b) -> a));

                    Produto product = mapProduto.get(chave(iterator.getReferenciaDestino(),
                            iterator.getCorDestino(),
                            iterator.getTamanhoDestino()));

                    if (product == null){
                        portLog.salvarLog(new LogPlanilhaLinha(null,
                                iterator.getPedido(),
                                iterator.getReferenciaOriginal(),
                                iterator.getCorOriginal(),
                                iterator.getTamanhoOriginal(),
                                itemResponse.pendingQuantity(),
                                iterator.getReferenciaDestino(),
                                iterator.getCorDestino(),
                                iterator.getTamanhoDestino(),
                                null,
                                0,
                                "Product Code destino nao encontrada no Totvs Moda"));
                        continue;
                    }

                    BigDecimal price = port.buscarPreco(product.productCode());

                    if (itemResponse.pendingQuantity() > 0){

                        port.cancelarItem(new CancelarItemCommand(iterator.getPedido(),
                                List.of(new ItemCancelamentoCommand(
                                itemResponse.productCode(),
                                iterator.getQuantidadeDestino()
                        ))));

                    }

                    port.inserirItem(new IncluirItemCommand(iterator.getPedido(), List.of(new ItemIncluirComand(
                            product.productCode(),
                            iterator.getQuantidadeDestino(),
                            price
                    ))));


                    portLog.salvarLog(new LogPlanilhaLinha(null,
                            iterator.getPedido(),
                            iterator.getReferenciaOriginal(),
                            iterator.getCorOriginal(),
                            iterator.getTamanhoOriginal(),
                            itemResponse.pendingQuantity(),
                            iterator.getReferenciaDestino(),
                            iterator.getCorDestino(),
                            iterator.getTamanhoDestino(),
                            price,
                            iterator.getQuantidadeDestino(),
                            "Item novo incluido"));


                } else {
                    Integer qtd = iterator.getQuantidadeDestino() > 0 ? iterator.getQuantidadeDestino() : itemResponse.quantidadeCancelada();
                    BigDecimal price = port.buscarPreco(itemDestinoPedido.productCode());

                        if (qtd == 0){
                            throw new RuntimeException("Quantidade do item igual a zero");
                        }

                        port.adcionarQuantidade(new AdcionarCommand(iterator.getPedido(), List.of(new AdcionarItemCommand(
                                itemDestinoPedido.productCode(),
                                qtd + itemDestinoPedido.quantity()
                        ))));

                        if (itemResponse.pendingQuantity() > 0){

                            port.cancelarItem(new CancelarItemCommand(iterator.getPedido(), List.of(new ItemCancelamentoCommand(
                                    itemResponse.productCode(),
                                    iterator.getQuantidadeDestino()
                            ))));

                        }


                        portLog.salvarLog(new LogPlanilhaLinha(null,
                                iterator.getPedido(),
                                iterator.getReferenciaOriginal(),
                                iterator.getCorOriginal(),
                                iterator.getTamanhoOriginal(),
                                itemResponse.pendingQuantity(),
                                iterator.getReferenciaDestino(),
                                iterator.getCorDestino(),
                                iterator.getTamanhoDestino(),
                                price,
                                qtd,
                                "Item alterado quantidade"));

                }
            } catch (Exception e) {

                String message = e.getMessage();
                if (message.length() > 254){
                    message = message.substring(0, 254);
                }

                portLog.salvarLog(new LogPlanilhaLinha(null,
                        iterator.getPedido(),
                        iterator.getReferenciaOriginal(),
                        iterator.getCorOriginal(),
                        iterator.getTamanhoOriginal(),
                        0,
                        iterator.getReferenciaDestino(),
                        iterator.getCorDestino(),
                        iterator.getTamanhoDestino(),
                        null,
                        0,
                        message));
            }
        }

        log.info("Finalizada execução");
    }

    private static String chave(String ref, String cor, String tam) {
        return (safe(ref) + "|" + safe(cor) + "|" + safe(tam)).toLowerCase();
    }
    private static String safe(String s) { return s == null ? "" : s.trim(); }
}
