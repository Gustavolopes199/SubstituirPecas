package totvs.substituirpecas.produto;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import totvs.substituirpecas.infrastructure.totvs.client.TotvsModaHttpClient;

@Slf4j
@SpringBootTest
public class ProdutosTest {

    @Autowired
    TotvsModaHttpClient  client;

    @Test
    public void buscarProduto(){
        String ref = "191380";

        log.info("Buscando produto {}", client.buscarProduto(ref));
    }

}
