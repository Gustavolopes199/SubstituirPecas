package totvs.substituirpecas.application.port.in;

import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoCommand;

public interface PlanilhaSubstituicaoUseCase {

    void executar(PlanilhaSubstituicaoCommand command);
}
