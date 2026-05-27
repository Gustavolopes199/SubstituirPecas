package totvs.substituirpecas.infrastructure.persistence.repository;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.SalvarLogCommand;
import totvs.substituirpecas.application.port.out.SubstituicaoLogRepositoryPort;
import totvs.substituirpecas.infrastructure.persistence.mapper.SubstituicaoLogMapper;

@Component
public class SubstituicaoLogRepositoryAdapter implements SubstituicaoLogRepositoryPort {


    private final SubstituicaoLogRepository repo;

    public SubstituicaoLogRepositoryAdapter(SubstituicaoLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public SalvarLogCommand salvarLog(SalvarLogCommand salvarLogCommand) {
       var entity = SubstituicaoLogMapper.toEntity(salvarLogCommand);
       repo.save(entity);
       return salvarLogCommand;
    }
}
