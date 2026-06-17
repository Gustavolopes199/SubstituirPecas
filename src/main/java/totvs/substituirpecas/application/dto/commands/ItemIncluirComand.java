package totvs.substituirpecas.application.dto.commands;

import java.math.BigDecimal;

public record ItemIncluirComand(
        Integer productCode,
        Integer quantity,
        BigDecimal price
){
}
