package totvs.substituirpecas.application.dto;

import java.util.List;

public record IncluirItemCommand(
        Integer idPedido,
        List<ItemIncluirComand> items
) {
}
