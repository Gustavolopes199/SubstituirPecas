package totvs.substituirpecas.application.port.out;


import totvs.substituirpecas.application.dto.transport.LogPlanilhaLinha;

import java.util.List;

public interface LogPlanilhaRepositoryPort {

    void salvarLog(LogPlanilhaLinha log);

    List<LogPlanilhaLinha> listarLog();

}
