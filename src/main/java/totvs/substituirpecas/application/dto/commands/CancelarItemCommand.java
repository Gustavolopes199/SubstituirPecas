package totvs.substituirpecas.application.dto.commands;

import java.util.List;

public record CancelarItemCommand (
        Integer orderCode,
        List<ItemCancelamentoCommand> items
){
}
