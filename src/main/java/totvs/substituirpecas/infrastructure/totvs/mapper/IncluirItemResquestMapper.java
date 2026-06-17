package totvs.substituirpecas.infrastructure.totvs.mapper;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.commands.IncluirItemCommand;
import totvs.substituirpecas.infrastructure.totvs.dto.pedido.IncluirItemRequest;

@Component
public class IncluirItemResquestMapper {

    public IncluirItemRequest toRequest(IncluirItemCommand incluirItemCommand) {
        return new IncluirItemRequest(2,
                 incluirItemCommand.idPedido(),
                incluirItemCommand.items().stream()
                        .map( r -> new IncluirItemRequest.Items(
                                r.productCode(),
                                r.quantity(),
                                r.price(),
                                null
                        ))
                        .toList());
    }
}
