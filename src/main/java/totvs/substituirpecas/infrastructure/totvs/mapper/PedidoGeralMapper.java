package totvs.substituirpecas.infrastructure.totvs.mapper;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.transport.ItemPedido;
import totvs.substituirpecas.application.dto.transport.Pedido;
import totvs.substituirpecas.infrastructure.totvs.dto.pedido.SalesOrderPageResponse;

import java.math.BigDecimal;

@Component
public class PedidoGeralMapper {

    public Pedido toPedido(SalesOrderPageResponse salesOrderPageResponse) {
        SalesOrderPageResponse.Order order =  salesOrderPageResponse.items().get(0);

        return new Pedido(
                order.orderCode(),
                order.branchCode(),
                order.customerName(),
                order.customerCpfCnpj(),
                order.items().stream()
                        .map( r -> new ItemPedido(
                                r.quantity(),
                                r.pendingQuantity(),
                                r.price(),
                                r.price(),
                                BigDecimal.ZERO,
                                BigDecimal.ZERO,
                                r.price(),
                                r.productCode(),
                                r.referenceName(),
                                r.referenceCode(),
                                r.referenceName(),
                                r.productSku(),
                                r.colorCode(),
                                r.colorName(),
                                r.sizeName(),
                                r.canceledQuantity(),
                                r.lastChangeDate()
                        ))
                        .toList()
        );

    }

}
