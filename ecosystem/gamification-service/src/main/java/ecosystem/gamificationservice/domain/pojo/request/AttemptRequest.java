package ecosystem.gamificationservice.domain.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttemptRequest {
    private int currentNumber;
    private int attemptCount;
    private String attemptAnswer;
}
