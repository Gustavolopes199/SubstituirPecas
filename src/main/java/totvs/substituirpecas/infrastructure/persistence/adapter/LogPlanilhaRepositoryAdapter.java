package totvs.substituirpecas.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.transport.LogPlanilhaLinha;
import totvs.substituirpecas.application.port.out.LogPlanilhaRepositoryPort;
import totvs.substituirpecas.infrastructure.persistence.mapper.LogPlanilhaMapper;
import totvs.substituirpecas.infrastructure.persistence.repository.LogPlanilhaLinhaRepository;

import java.util.List;

@Component
public class LogPlanilhaRepositoryAdapter implements LogPlanilhaRepositoryPort {

    private final LogPlanilhaLinhaRepository repo;

    public LogPlanilhaRepositoryAdapter(LogPlanilhaLinhaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void salvarLog(LogPlanilhaLinha log) {
         repo.save(LogPlanilhaMapper.toEntity(log));
    }

    @Override
    public List<LogPlanilhaLinha> listarLog() {
        return repo.findAll().stream()
                .map(r -> LogPlanilhaMapper.toDto(r))
                .toList();
    }

}
