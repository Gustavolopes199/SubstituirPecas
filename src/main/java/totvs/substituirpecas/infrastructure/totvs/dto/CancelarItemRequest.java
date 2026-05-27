package totvs.substituirpecas.infrastructure.totvs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelarItemRequest {

    private Integer branchCode;
    private Integer orderCode;
    private List<Items> items;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items{

        private Integer productCode;
        private Integer cancelQuantity;
        private Integer reasonCancellationCode;
        private String reasonCancellationDescription;

    }

}
