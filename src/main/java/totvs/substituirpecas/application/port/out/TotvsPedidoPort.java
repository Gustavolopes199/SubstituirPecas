package totvs.substituirpecas.application.port.out;

import totvs.substituirpecas.application.dto.commands.AdcionarCommand;
import totvs.substituirpecas.application.dto.commands.CancelarItemCommand;
import totvs.substituirpecas.application.dto.commands.IncluirItemCommand;
import totvs.substituirpecas.application.dto.transport.Pedido;
import totvs.substituirpecas.application.dto.transport.Produto;

import java.math.BigDecimal;
import java.util.List;

public interface TotvsPedidoPort {

    Pedido buscarPedido(Integer id);

    List<Produto> buscarProduto(String referenceCode);

    void inserirItem(IncluirItemCommand data);

    void cancelarItem(CancelarItemCommand data);

    BigDecimal buscarPreco(Integer productCode);

    void adcionarQuantidade(AdcionarCommand data);

    Pedido buscarPedidoCompleto(Integer id);

    List<Integer> buscarSugestoes(Long dias);

}
