package ecosystem.leaderboardservice.db.dao;

import ecosystem.leaderboardservice.db.mapper.LeaderboardMapper;
import ecosystem.leaderboardservice.db.repository.LeaderboardRepository;
import ecosystem.leaderboardservice.domain.pojo.LeaderboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardDao {

    @Autowired
    private LeaderboardMapper leaderboardMapper;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    public List<LeaderboardDto> findAll() {
        return leaderboardMapper.from(leaderboardRepository.findAll());
    }

}
