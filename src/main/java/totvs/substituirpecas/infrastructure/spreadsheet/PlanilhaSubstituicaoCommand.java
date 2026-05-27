package totvs.substituirpecas.infrastructure.spreadsheet;


import java.io.InputStream;

public record PlanilhaSubstituicaoCommand(String nomeArquivo, InputStream arquivo) {
}
