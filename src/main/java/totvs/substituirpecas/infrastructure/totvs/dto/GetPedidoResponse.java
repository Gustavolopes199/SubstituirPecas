package totvs.substituirpecas.infrastructure.totvs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPedidoResponse {

    private Integer branchCode;
    private Integer orderCode;
    private Integer orderId;
    private List<Items> items;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Items{
        private Integer quantity;
        private Integer pendingQuantity;
        private BigDecimal originalPrice;
        private BigDecimal grossPrice;
        private BigDecimal itemDiscount;
        private BigDecimal orderDiscount;
        private BigDecimal netPrice;
        private Integer productCode;
        private String name;
        private String referenceCode;
        private String referenceName;
        private String productSku;
        private String colorCode;
        private String colorName;
        private String sizeName;
    }

}
