package totvs.substituirpecas.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totvs.substituirpecas.infrastructure.persistence.entity.SubstituicaoLogEntity;

@Repository
public interface SubstituicaoLogRepository extends JpaRepository<SubstituicaoLogEntity, Long> {
}
