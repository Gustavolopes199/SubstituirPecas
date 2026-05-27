package totvs.substituirpecas.application.dto;

import java.util.List;

public record CancelarItemCommand (
        Integer orderCode,
        List<ItemCancelamentoCommand> items
){
}
