package totvs.substituirpecas.application.port.out;

import totvs.substituirpecas.application.dto.transport.SalvarLogCommand;

public interface SubstituicaoLogRepositoryPort {

    SalvarLogCommand salvarLog(SalvarLogCommand salvarLogCommand);
}
