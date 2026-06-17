package totvs.substituirpecas.application.dto.transport;

import java.math.BigDecimal;

public record LogPlanilhaLinha(
        Long id,
        Integer pedido,
        String referenciaOriginal,
        String corOriginal,
        String tamanhoOriginal,
        Integer quantidadePendente,
        String referenciaDestino,
        String corDestino,
        String tamanhoDestino,
        BigDecimal preco,
        Integer quantidadeAlterada,
        String status
) {
}
