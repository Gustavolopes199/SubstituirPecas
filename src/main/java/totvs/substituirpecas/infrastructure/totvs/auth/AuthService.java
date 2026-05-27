package totvs.substituirpecas.infrastructure.totvs.auth;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import totvs.substituirpecas.infrastructure.totvs.dto.TokenResponse;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Value("${totvs.api.baseUrl}")
    private String baseUrl;

    @Value("${totvs.auth.clientId}")
    private String clientId;

    @Value("${totvs.auth.clientSecret}")
    private String clientSecret;

    @Value("${totvs.auth.username}")
    private String username;

    @Value("${totvs.auth.password}")
    private String password;

    @Value("${totvs.auth.grant-type}")
    private String grant_type;

    private volatile String token;
    private volatile LocalDateTime expires_at;


    private final static Long EXPIRE_TIME = 6L;

    private boolean expirado(LocalDateTime expires_at){
        if (expires_at == null) return false;
        if (LocalDateTime.now().isAfter(expires_at)) return false;

        return true;
    }

    public String buscarToken(){
        if (expirado(expires_at)) return token;

        TokenResponse response = fetchToken(buscarAuth());

        token = response.getAccess_token();
        expires_at = LocalDateTime.now().plusHours(EXPIRE_TIME);

        return token;
    }


    private MultiValueMap<String, String> buscarAuth(){

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("grant_type", grant_type);
        form.add("username", username);
        form.add("password", password);

        return form;
    }

    private TokenResponse fetchToken(MultiValueMap<String, String> auth){

        WebClient webClient = WebClient.builder()
                .build();

        return webClient.post()
                .uri(baseUrl + "/api/totvsmoda/authorization/v2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(auth)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .blockOptional()
                .orElseThrow( () -> new IllegalStateException("Falha ao buscar token totvs"));

    }

}
