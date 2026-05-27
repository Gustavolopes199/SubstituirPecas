package totvs.substituirpecas.application.dto;

import java.math.BigDecimal;

public record ItemPedido(
        Integer quantity,
        Integer pendingQuantity,
        BigDecimal originalPrice,
        BigDecimal grossPrice,
        BigDecimal itemDiscount,
        BigDecimal orderDiscount,
        BigDecimal netPrice,
        Integer productCode,
        String name,
        String referenceCode,
        String referenceName,
        String productSku,
        String colorCode,
        String colorName,
        String sizeName
) {
}
