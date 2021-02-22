package ecosystem.gamificationservice.domain.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttemptResponse {
    private int nextNumber;
    private boolean attemptResult;
}
