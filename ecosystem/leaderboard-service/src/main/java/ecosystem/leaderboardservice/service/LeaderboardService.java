package ecosystem.leaderboardservice.service;

import ecosystem.leaderboardservice.db.dao.LeaderboardDao;
import ecosystem.leaderboardservice.domain.pojo.LeaderboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardDao leaderboardDao;

    public List<LeaderboardDto> getLeaderboard() {
        return leaderboardDao.findAll();
    }

}
