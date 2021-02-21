package ecosystem.gamificationservice.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttemptRequest {

    private int currentNumber;
    private int nextNumber;
    private int attemptCount;
    private boolean isHigher;

    public AttemptRequest(int currentNumber, int randomNumber) {
        this.currentNumber = currentNumber;
        this.nextNumber = randomNumber;
        this.isHigher = this.currentNumber > this.nextNumber;
    }

}
