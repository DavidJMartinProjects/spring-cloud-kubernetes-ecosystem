package ecosystem.gamificationservice.domain.pojo.request;

import ecosystem.gamificationservice.domain.pojo.enums.HiLo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttemptRequest {
    private int currentNumber;
    private int nextNumber;
    private int attemptCount;
    private HiLo hiLo;
}
