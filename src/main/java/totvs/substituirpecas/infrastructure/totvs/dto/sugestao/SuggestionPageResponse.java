package totvs.substituirpecas.infrastructure.totvs.dto.sugestao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SuggestionPageResponse(
        Integer count,
        Integer totalPages,
        Boolean hasNext,
        Long totalItems,
        List<Suggestion> items
) {
    // =========================================================
    // SUGGESTION
    // =========================================================
    public record Suggestion(
            Integer branchCode,
            Long suggestionCode,
            Integer statusSuggestion,
            LocalDateTime suggestionDate,
            LocalDateTime maxChangeFilterDate,
            List<Order> orders
    ) {}
    // =========================================================
    // ORDER (vinculada à sugestão)
    // =========================================================
    public record Order(
            Integer orderBranchCode,
            Integer orderCode,
            BigDecimal quantity,
            BigDecimal suggestedQuantity,
            BigDecimal pendingQuantity,
            BigDecimal value,
            BigDecimal suggestedValue,
            BigDecimal pendingValue,
            List<OrderItem> orderItems
    ) {}
    // =========================================================
    // ORDER ITEM (vazio até saber o formato real)
    // =========================================================
    public record OrderItem(
            // preencher quando aparecer um payload com orderItems != null
    ) {}
}