package totvs.substituirpecas.application.port.out;

import totvs.substituirpecas.application.dto.CancelarItemCommand;
import totvs.substituirpecas.application.dto.IncluirItemCommand;
import totvs.substituirpecas.application.dto.Pedido;
import totvs.substituirpecas.application.dto.Produto;

import java.math.BigDecimal;
import java.util.List;

public interface TotvsPedidoPort {

    Pedido buscarPedido(Long id);

    List<Produto> buscarProduto(String referenceCode);

    void inserirItem(IncluirItemCommand data);

    void cancelarItem(CancelarItemCommand data);

    BigDecimal buscarPreco(Integer productCode);

}
