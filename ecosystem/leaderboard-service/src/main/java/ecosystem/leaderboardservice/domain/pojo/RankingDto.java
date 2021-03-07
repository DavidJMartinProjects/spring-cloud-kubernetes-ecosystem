package ecosystem.leaderboardservice.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingDto {
    private long id;
    private String name;
    private String country;
    private int score;
    private int rank;
}
