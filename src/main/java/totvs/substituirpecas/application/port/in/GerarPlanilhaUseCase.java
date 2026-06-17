package totvs.substituirpecas.application.port.in;

import totvs.substituirpecas.application.dto.transport.LogPlanilhaLinha;

import java.util.List;

public interface GerarPlanilhaUseCase {

    byte[] gerarPlanilhaSubstituicao(List<LogPlanilhaLinha>  planilhaLinhas);

}
