package totvs.substituirpecas.application.dto.transport;

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
    private Integer quantidade;
    private String corOriginal;
    private String tamanhoDestino;
    private String corDestino;
    private Integer quantidadeDestino;

}
