package totvs.substituirpecas.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanilhaLinha {

    private Integer pedido;
    private String referenciaOriginal;
    private String referenciaDestino;
    private String tamanhoOriginal;
    private String corOriginal;

}
