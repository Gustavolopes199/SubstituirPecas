package totvs.substituirpecas.application.dto;

import java.time.OffsetDateTime;

public record SalvarLogCommand(Long id, Integer idPedido, Integer qtdSubstituida, Integer qtdCancelada, Integer qtdIncluida, OffsetDateTime criadoem){
    public static SalvarLogCommand novo(Integer idPedido, Integer qtdSubstituida, Integer qtdCancelada, Integer qtdIncluida){
        return new SalvarLogCommand(null, idPedido, qtdSubstituida, qtdCancelada, qtdIncluida, null);
    }
}
