package totvs.substituirpecas.application.dto.commands;

import java.util.List;

public record AdcionarCommand(Integer orderCode, List<AdcionarItemCommand> items) {
}
