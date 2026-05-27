package totvs.substituirpecas.pedido;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import totvs.substituirpecas.infrastructure.totvs.client.TotvsModaHttpClient;
import totvs.substituirpecas.infrastructure.totvs.dto.pedido.CancelarItemRequest;
import totvs.substituirpecas.infrastructure.totvs.dto.pedido.IncluirItemRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class PedidoTest {

    @Autowired
    private TotvsModaHttpClient totvsModaHttpClient;

    @Test
    public void pedidoTest(){

        Long idPedido = 16282L;

        log.info("Pedido Respose: {}", totvsModaHttpClient.buscarPedido(idPedido));

    }

    @Test
    public void incluirItemTestSemDesconto(){
        IncluirItemRequest  request = new IncluirItemRequest();
        request.setOrderCode(16282);
        request.setBranchCode(2);
        List<IncluirItemRequest.Items> items = new ArrayList<>();
        items.add(new IncluirItemRequest.Items(26365, 1, BigDecimal.valueOf(79.90), null));
        request.setItems(items);

    }

    @Test
    public void incluirItemTestComDesconto(){
        IncluirItemRequest  request = new IncluirItemRequest();
        request.setOrderCode(16282);
        request.setBranchCode(2);
        List<IncluirItemRequest.Items> items = new ArrayList<>();
        items.add(new IncluirItemRequest.Items(
                26365,
                1,
                BigDecimal.valueOf(79.90),
                BigDecimal.valueOf(10)));
        request.setItems(items);

    }

    @Test
    public void cancelarItemTest(){
        CancelarItemRequest request = new CancelarItemRequest();
        request.setOrderCode(16282);
        request.setBranchCode(2);

        List<CancelarItemRequest.Items> items = new ArrayList<>();
        items.add(new CancelarItemRequest.Items(
                26365,
                1,
                11,
                "Cancelado para substituicao"
        ));
        request.setItems(items);

    }

}
