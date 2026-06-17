package totvs.substituirpecas.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totvs.substituirpecas.infrastructure.persistence.entity.ExecucaoLog;

@Repository
public interface ExecucaoLogRepository extends JpaRepository<ExecucaoLog,Integer> {

}
