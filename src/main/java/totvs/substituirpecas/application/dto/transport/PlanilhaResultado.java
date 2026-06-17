package totvs.substituirpecas.application.dto.transport;

public record PlanilhaResultado(
        Integer pedido,
        String nomeCLiente,
        String cnpjCliente,
        String referenciaOriginal,
        String tamanhoOriginal,
        String corOriginal,
        String referenciaDestino,
        String tamanhoDestino,
        String corDestino
) {
}
