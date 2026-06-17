package totvs.substituirpecas.application.dto.commands;

import java.util.List;

public record IncluirItemCommand(
        Integer idPedido,
        List<ItemIncluirComand> items
) {
}
