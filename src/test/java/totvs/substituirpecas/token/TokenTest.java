package totvs.substituirpecas.token;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import totvs.substituirpecas.infrastructure.totvs.auth.AuthService;


@Slf4j
@SpringBootTest
public class TokenTest {

    @Autowired
    private AuthService authService;

    @Test
    public void tokenTest() {

        log.info("tokenTest: {}", authService.buscarToken());

    }

}
