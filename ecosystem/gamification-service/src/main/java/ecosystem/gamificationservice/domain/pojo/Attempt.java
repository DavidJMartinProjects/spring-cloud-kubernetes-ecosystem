package ecosystem.gamificationservice.domain.pojo;

import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attempt {
    private AttemptRequest attemptRequest;
    private int nextNumber;
}
