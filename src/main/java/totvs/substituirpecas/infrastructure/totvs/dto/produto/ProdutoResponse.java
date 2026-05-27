package totvs.substituirpecas.infrastructure.totvs.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {

    private Integer count;
    private Integer totalPages;
    private Integer totalItems;
    private Boolean hasNext;
    private List<Produtos> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Produtos{
        private Integer productCode;
        private String size;
        private String colorName;
        private String ReferenceCode;
    }

}
