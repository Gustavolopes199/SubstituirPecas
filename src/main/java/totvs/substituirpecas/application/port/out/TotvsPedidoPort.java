package totvs.substituirpecas.application.port.out;

import jakarta.persistence.criteria.CriteriaBuilder;
import totvs.substituirpecas.application.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface TotvsPedidoPort {

    Pedido buscarPedido(Long id);

    List<Produto> buscarProduto(String referenceCode);

    void inserirItem(IncluirItemCommand data);

    void cancelarItem(CancelarItemCommand data);

    BigDecimal buscarPreco(Integer productCode);

    void adcionarQuantidade(AdcionarCommand data);

    Pedido buscarPedidoCompleto(Integer id);

}
