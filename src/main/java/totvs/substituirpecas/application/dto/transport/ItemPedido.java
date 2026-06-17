package totvs.substituirpecas.application.dto.transport;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        String sizeName,
        Integer quantidadeCancelada,
        LocalDateTime lastChangeDate
) {
}
