package totvs.substituirpecas.infrastructure.totvs.dto.pedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncluirItemRequest {

    private Integer branchCode;
    private Integer orderCode;
    private List<Items> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Items{

        private Integer productCode;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal discountValue;

    }

}
