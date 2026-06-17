package totvs.substituirpecas.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import totvs.substituirpecas.infrastructure.persistence.entity.LogPlanilhaLinhaEntity;

public interface LogPlanilhaLinhaRepository extends JpaRepository<LogPlanilhaLinhaEntity, Long> {
}
