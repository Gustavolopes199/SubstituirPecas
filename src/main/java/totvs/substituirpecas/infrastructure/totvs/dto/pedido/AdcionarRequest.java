package totvs.substituirpecas.infrastructure.totvs.dto.pedido;

import java.util.List;

public record AdcionarRequest(Integer branchCode, Integer orderCode, List<AdcionarItemRequest> items) {
}
