package totvs.substituirpecas.application.dto.transport;

import java.util.List;

public record Pedido(
        Integer branchCode,
        Integer orderCode,
        String clientName,
        String cnpj,
        List<ItemPedido> items
) {
}
