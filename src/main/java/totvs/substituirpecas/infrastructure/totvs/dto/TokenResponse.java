package totvs.substituirpecas.infrastructure.totvs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private Integer expires_in;

}
