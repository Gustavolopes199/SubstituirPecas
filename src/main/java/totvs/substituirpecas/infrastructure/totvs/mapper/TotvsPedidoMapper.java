package totvs.substituirpecas.infrastructure.totvs.mapper;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.ItemPedido;
import totvs.substituirpecas.application.dto.Pedido;
import totvs.substituirpecas.infrastructure.totvs.dto.GetPedidoResponse;

@Component
public class TotvsPedidoMapper {

    public Pedido toApplication(GetPedidoResponse response){
        return new Pedido(response.getBranchCode(),
                response.getOrderCode(),
                response.getItems().stream()
                        .map( r -> new ItemPedido(r.getQuantity(),
                                r.getPendingQuantity(),
                                r.getOriginalPrice(),
                                r.getGrossPrice(),
                                r.getItemDiscount(),
                                r.getOrderDiscount(),
                                r.getNetPrice(),
                                r.getProductCode(),
                                r.getName(),
                                r.getReferenceCode(),
                                r.getReferenceName(),
                                r.getProductSku(),
                                r.getColorCode(),
                                r.getColorName(),
                                r.getSizeName()))
                        .toList()
                        );
    }


}
