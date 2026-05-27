package totvs.substituirpecas.application.dto;

import java.util.List;

public record Pedido(
        Integer branchCode,
        Integer orderCode,
        List<ItemPedido> items
) {
}
