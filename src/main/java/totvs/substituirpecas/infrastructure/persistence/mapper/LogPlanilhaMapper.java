package totvs.substituirpecas.infrastructure.persistence.mapper;


import totvs.substituirpecas.application.dto.transport.LogPlanilhaLinha;
import totvs.substituirpecas.infrastructure.persistence.entity.LogPlanilhaLinhaEntity;

import java.time.LocalDateTime;

public final class LogPlanilhaMapper {

    public static LogPlanilhaLinhaEntity toEntity(LogPlanilhaLinha logPlanilhaLinha) {
        return new LogPlanilhaLinhaEntity(
                null,
                logPlanilhaLinha.pedido(),
                logPlanilhaLinha.referenciaOriginal(),
                logPlanilhaLinha.corOriginal(),
                logPlanilhaLinha.tamanhoOriginal(),
                logPlanilhaLinha.quantidadePendente(),
                logPlanilhaLinha.referenciaDestino(),
                logPlanilhaLinha.corDestino(),
                logPlanilhaLinha.tamanhoDestino(),
                logPlanilhaLinha.preco(),
                logPlanilhaLinha.quantidadeAlterada(),
                logPlanilhaLinha.status(),
                LocalDateTime.now()
        );
    }

    public static LogPlanilhaLinha toDto(LogPlanilhaLinhaEntity entity) {
        return new LogPlanilhaLinha(
                entity.getId(),
                entity.getPedido(),
                entity.getReferenciaOriginal(),
                entity.getCorOriginal(),
                entity.getTamanhoOriginal(),
                entity.getQuantidadePendente(),
                entity.getReferenciaDestino(),
                entity.getCorDestino(),
                entity.getTamanhoDestino(),
                entity.getPreco(),
                entity.getQuantidadeAlterada(),
                entity.getStatus()
        );
    }

}
