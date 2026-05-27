package totvs.substituirpecas.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import totvs.substituirpecas.infrastructure.totvs.auth.AuthService;

@Configuration
public class WebClientConfig {

    @Value("${totvs.api.baseUrl}")
    private String baseUrl;

    @Bean
    public WebClient totvsClient(AuthService authService){

        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(bearerAuth(authService))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs( cfg -> cfg.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();

    }

    ExchangeFilterFunction bearerAuth(AuthService authService){
        return ((request, next) -> {
            String token = authService.buscarToken();

            ClientRequest comAuth = ClientRequest.from(request)
                    .headers(h -> h.setBearerAuth(token))
                    .build();

            return next.exchange(comAuth);
        });
    }


}
