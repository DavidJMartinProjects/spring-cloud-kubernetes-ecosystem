package ecosystem.gamificationservice.domain.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttemptResponse {
    private int nextNumber;
    private boolean isPreviousAttemptCorrect;
}
