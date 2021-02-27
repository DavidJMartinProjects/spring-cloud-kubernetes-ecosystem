package ecosystem.gamificationservice.domain.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttemptRequest {
    private int currentNumber;
    private int attemptCount;
    private String attemptAnswer;
}
