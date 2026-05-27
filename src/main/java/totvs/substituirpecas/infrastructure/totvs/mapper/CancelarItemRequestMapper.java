package totvs.substituirpecas.infrastructure.totvs.mapper;


import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.CancelarItemCommand;
import totvs.substituirpecas.infrastructure.totvs.dto.CancelarItemRequest;

@Component
public class CancelarItemRequestMapper {

    public CancelarItemRequest toRequest(CancelarItemCommand data) {
        return new CancelarItemRequest(
                2,
                data.orderCode(),
                data.items().stream()
                        .map( r -> new CancelarItemRequest.Items(
                                r.productCode(),
                                r.cancelQuantity(),
                                11,
                                "Cancelado via integração para substituição"
                        ))
                        .toList()
        );
    }

}
