package totvs.substituirpecas.application.dto;

import java.util.List;

public record AdcionarCommand(Integer orderCode, List<AdcionarItemCommand> items) {
}
