package ecosystem.gamificationservice.domain.pojo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RandomNumber {
    private int number;
}
