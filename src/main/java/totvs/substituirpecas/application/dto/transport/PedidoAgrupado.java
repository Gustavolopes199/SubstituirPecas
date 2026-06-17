package totvs.substituirpecas.application.dto.transport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoAgrupado {

    private Integer pedidoId;
    private List<ItemSubstituir> items;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemSubstituir{

        private String referenciaOriginal;
        private String referenciaSubstituicao;
        private String tamanho;
        private String cor;
        private String corDestino;
        private String tamanhoDestino;

    }

}
