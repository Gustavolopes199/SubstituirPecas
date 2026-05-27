package totvs.substituirpecas.infrastructure.totvs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueTotvsResponse {

    private LocalDateTime processingDate;

}
