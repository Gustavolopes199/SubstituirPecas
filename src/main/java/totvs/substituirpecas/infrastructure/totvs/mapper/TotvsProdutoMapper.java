package totvs.substituirpecas.infrastructure.totvs.mapper;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.Produto;
import totvs.substituirpecas.infrastructure.totvs.dto.produto.ProdutoResponse;

import java.util.List;

@Component
public class TotvsProdutoMapper {

    public List<Produto> toApplication(ProdutoResponse produtoResponse) {
        List<Produto> produtos = produtoResponse.getItems().stream()
                .map( r -> new Produto(r.getProductCode(),
                        r.getSize(),
                        r.getColorName(),
                        r.getReferenceCode()))
                .toList();

        return produtos;
    }

}
