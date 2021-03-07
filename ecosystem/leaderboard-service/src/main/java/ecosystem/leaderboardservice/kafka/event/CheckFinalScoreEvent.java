package ecosystem.leaderboardservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckFinalScoreEvent {
    private String name;
    private String country;
    private int score;
}
