package ecosystem.leaderboardservice.service;

import ecosystem.leaderboardservice.domain.pojo.LeaderboardEntry;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LeaderboardService {

    public List<LeaderboardEntry> getLeaderboard() {
        return Collections.singletonList(
            new LeaderboardEntry().builder()
                .name("dummy-name")
                .rank("dummy-rank")
                .build()
        );
    }

}
