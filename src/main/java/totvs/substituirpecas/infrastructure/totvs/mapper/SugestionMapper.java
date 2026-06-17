package totvs.substituirpecas.infrastructure.totvs.mapper;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.infrastructure.totvs.dto.sugestao.SuggestionPageResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SugestionMapper {


    public List<Integer> toListPedidos(SuggestionPageResponse suggestionPageResponse){

        List<Integer> list = new ArrayList<>();

        for (SuggestionPageResponse.Suggestion item : suggestionPageResponse.items()) {
            list.addAll(item.orders().stream()
                    .map(r -> r.orderCode())
                    .toList());
        }

        return list;
    }


}
