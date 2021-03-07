package ecosystem.leaderboardservice.service;

import ecosystem.leaderboardservice.db.dao.LeaderboardDao;
import ecosystem.leaderboardservice.domain.pojo.RankingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardDao leaderboardDao;

    public List<RankingDto> getLeaderboard() {
        return leaderboardDao.findAll();
    }

    public RankingDto postRanking(RankingDto rankingDto) {
        return leaderboardDao.save(rankingDto);
    }

}
