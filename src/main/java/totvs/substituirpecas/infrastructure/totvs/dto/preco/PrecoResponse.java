package totvs.substituirpecas.infrastructure.totvs.dto.preco;


import java.util.List;

public record PrecoResponse(
        Integer count,
        Integer totalPages,
        Boolean hasNext,
        List<PrecoItemResponse> items
) {
}
