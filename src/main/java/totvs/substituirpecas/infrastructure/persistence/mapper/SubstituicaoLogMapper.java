package totvs.substituirpecas.infrastructure.persistence.mapper;

import totvs.substituirpecas.application.dto.SalvarLogCommand;
import totvs.substituirpecas.infrastructure.persistence.entity.SubstituicaoLogEntity;

public final class SubstituicaoLogMapper {

    private SubstituicaoLogMapper() {}

    public static SubstituicaoLogEntity toEntity(SalvarLogCommand log) {
        return new SubstituicaoLogEntity(log.id(),
                log.idPedido(),
                log.qtdSubstituida(),
                log.qtdCancelada(),
                log.qtdIncluida(),
                log.criadoem());
    }

}
