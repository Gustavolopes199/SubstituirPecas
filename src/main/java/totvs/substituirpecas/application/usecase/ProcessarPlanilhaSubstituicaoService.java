package totvs.substituirpecas.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import totvs.substituirpecas.application.dto.*;
import totvs.substituirpecas.application.port.in.PlanilhaSubstituicaoUseCase;
import totvs.substituirpecas.application.port.out.TotvsPedidoPort;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoCommand;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoGroup;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    public ProcessarPlanilhaSubstituicaoService(PlanilhaSubstituicaoReader reader,
                                                PlanilhaSubstituicaoGroup group,
                                                TotvsPedidoPort port) {
        this.reader = reader;
        this.group = group;
        this.port = port;
    }

    @Override
    public void executar(PlanilhaSubstituicaoCommand command) {

        try(InputStream in = command.arquivo()) {

            List<PlanilhaLinha> linhas = reader.execute(in);

            List<PedidoAgrupado> pedidos = group.execute(linhas);

            for (PedidoAgrupado pedido : pedidos) {
                Pedido resp = port.buscarPedido((long) pedido.getPedidoId());

                Map<String, ItemPedido> itensResponse = resp.items()
                        .stream()
                        .collect(Collectors.toMap(
                                i -> chave(i.referenceCode(), i.colorName(), i.sizeName()),
                                Function.identity(),
                                (a, b) -> a
                        ));
                List<ItemIncluirComand> itensIncluir = new ArrayList<>();
                List<ItemCancelamentoCommand> itensCancel = new ArrayList<>();

                for (PedidoAgrupado.ItemSubstituir alvo : pedido.getItems()) {
                    String k = chave(alvo.getReferenciaOriginal(), alvo.getCor(), alvo.getTamanho());
                    ItemPedido item = itensResponse.get(k);

                    if (item == null) {
                        continue;
                    }

                    List<Produto> produtoList = port.buscarProduto(alvo.getReferenciaSubstituicao());

                    for (Produto produto : produtoList) {
                        if (alvo.getCor().equalsIgnoreCase(produto.colorName()) && alvo.getTamanho().equalsIgnoreCase(produto.size())) {
                            itensIncluir.add(new ItemIncluirComand(
                                    produto.productCode(),
                                    item.pendingQuantity(),
                                    item.originalPrice()
                            ));
                            itensCancel.add(new ItemCancelamentoCommand(
                                    item.productCode(), item.pendingQuantity()
                            ));
                        }
                    }

                }
                if (itensIncluir.isEmpty()){
                    continue;
                }
                IncluirItemCommand incluirItemCommand = new IncluirItemCommand(pedido.getPedidoId(), itensIncluir);
                CancelarItemCommand cancelarItemCommand = new CancelarItemCommand(pedido.getPedidoId(), itensCancel);
                port.inserirItem(incluirItemCommand);
                port.cancelarItem(cancelarItemCommand);

            }

        } catch (IOException e){

        }
    }


    private static String chave(String ref, String cor, String tam) {
        return (safe(ref) + "|" + safe(cor) + "|" + safe(tam)).toLowerCase();
    }
    private static String safe(String s) { return s == null ? "" : s.trim(); }
}
