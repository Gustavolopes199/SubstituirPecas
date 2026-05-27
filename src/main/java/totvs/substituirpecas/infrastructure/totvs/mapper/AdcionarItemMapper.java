package totvs.substituirpecas.infrastructure.totvs.mapper;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.AdcionarCommand;
import totvs.substituirpecas.application.dto.AdcionarItemCommand;
import totvs.substituirpecas.infrastructure.totvs.dto.pedido.AdcionarItemRequest;
import totvs.substituirpecas.infrastructure.totvs.dto.pedido.AdcionarRequest;

@Component
public class AdcionarItemMapper {

    public AdcionarRequest toRequest(AdcionarCommand command) {
        return new AdcionarRequest(2,
                command.orderCode(),
                command.items().stream()
                        .map(r -> new AdcionarItemRequest(r.productCode(), r.quantity()))
                        .toList());
    }

}
