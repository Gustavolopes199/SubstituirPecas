package totvs.substituirpecas.infrastructure.spreadsheet;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.PedidoAgrupado;
import totvs.substituirpecas.application.dto.PlanilhaLinha;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PlanilhaSubstituicaoGroup {


    public List<PedidoAgrupado> execute(List<PlanilhaLinha> planilhaLinha){
        return planilhaLinha.stream()
                .collect(Collectors.groupingBy(PlanilhaLinha::getPedido))
                .entrySet()
                .stream()
                .map(entry -> {
                    PedidoAgrupado ped = new PedidoAgrupado();

                    ped.setPedidoId(entry.getKey());

                    List<PedidoAgrupado.ItemSubstituir> items = new ArrayList<>();

                    for (PlanilhaLinha linha : entry.getValue()) {
                        PedidoAgrupado.ItemSubstituir item = new PedidoAgrupado.ItemSubstituir();

                        item.setReferenciaOriginal(linha.getReferenciaOriginal());
                        item.setTamanho(linha.getTamanhoOriginal());
                        item.setCor(linha.getCorOriginal());
                        item.setReferenciaSubstituicao(linha.getReferenciaDestino());

                        items.add(item);
                    }

                    ped.setItems(items);

                    return ped;

                })
                .toList();
    }


}
