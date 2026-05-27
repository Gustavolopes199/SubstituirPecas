package totvs.substituirpecas.infrastructure.totvs.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import totvs.substituirpecas.application.dto.CancelarItemCommand;
import totvs.substituirpecas.application.dto.IncluirItemCommand;
import totvs.substituirpecas.application.dto.Pedido;
import totvs.substituirpecas.application.dto.Produto;
import totvs.substituirpecas.application.port.out.TotvsPedidoPort;
import totvs.substituirpecas.infrastructure.totvs.dto.*;
import totvs.substituirpecas.infrastructure.totvs.mapper.CancelarItemRequestMapper;
import totvs.substituirpecas.infrastructure.totvs.mapper.IncluirItemResquestMapper;
import totvs.substituirpecas.infrastructure.totvs.mapper.TotvsPedidoMapper;
import totvs.substituirpecas.infrastructure.totvs.mapper.TotvsProdutoMapper;

import java.util.List;

@Slf4j
@Component
public class TotvsModaHttpClient implements TotvsPedidoPort {


    private final WebClient webClient;
    private final TotvsPedidoMapper mapper;
    private final TotvsProdutoMapper produtoMapper;
    private final IncluirItemResquestMapper resquestMapper;
    private final CancelarItemRequestMapper cancelarItemRequestMapper;

    public TotvsModaHttpClient(WebClient webClient,
                               TotvsPedidoMapper mapper,
                               TotvsProdutoMapper produtoMapper,
                               IncluirItemResquestMapper resquestMapper,
                               CancelarItemRequestMapper cancelarItemRequestMapper) {
        this.webClient = webClient;
        this.mapper = mapper;
        this.produtoMapper = produtoMapper;
        this.resquestMapper = resquestMapper;
        this.cancelarItemRequestMapper = cancelarItemRequestMapper;
    }

    @Override
    public Pedido buscarPedido(Long pedido){
        try {
            GetPedidoResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/api/totvsmoda/sales-order/v2/pending-items")
                            .queryParam("branchCode", 2)
                            .queryParam("orderCode", pedido)
                            .build())
                    .retrieve()
                    .bodyToMono(GetPedidoResponse.class)
                    .block();

            return mapper.toApplication(response);
        } catch (WebClientResponseException e){
            throw new RuntimeException(e.getResponseBodyAsString());
        }
    }

    @Override
    public void inserirItem(IncluirItemCommand data){

        IncluirItemRequest request = resquestMapper.toRequest(data);

        try {
            QueueTotvsResponse response = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/api/totvsmoda/sales-order/v2/items")
                            .build())
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(QueueTotvsResponse.class)
                    .block();

        } catch (WebClientResponseException e){
            log.info("Response: {}", e.getResponseBodyAsString());
        }
    }

    @Override
    public void cancelarItem(CancelarItemCommand data){

        CancelarItemRequest request = cancelarItemRequestMapper.toRequest(data);

        try{
            QueueTotvsResponse response = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/api/totvsmoda/sales-order/v2/cancel-items")
                            .build())
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(QueueTotvsResponse.class)
                    .block();

        } catch (WebClientResponseException e){
            log.info("Response: {}", e.getResponseBodyAsString());
        }
    }

    @Override
    public List<Produto> buscarProduto(String referencia){

        String json = """
                {
                    "filter": {
                        "referenceCodeList": ["%s"]
                    },
                    "option": {
                        "branchInfoCode": 1
                    }
                }
                """.formatted(referencia);

        try {
            ProdutoResponse response = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/api/totvsmoda/product/v2/products/search")
                            .build())
                    .bodyValue(json)
                    .retrieve()
                    .bodyToMono(ProdutoResponse.class)
                    .block();

            return produtoMapper.toApplication(response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
