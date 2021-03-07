package ecosystem.leaderboardservice.db.dao;

import ecosystem.leaderboardservice.db.entity.RankingEntity;
import ecosystem.leaderboardservice.db.mapper.RankingMapper;
import ecosystem.leaderboardservice.db.repository.LeaderboardRepository;
import ecosystem.leaderboardservice.domain.pojo.RankingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardDao {

    @Autowired
    private RankingMapper rankingMapper;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    public List<RankingDto> findAll() {
        return rankingMapper.fromEntityListToDtoList(leaderboardRepository.findAll());
    }

    public RankingDto save(RankingDto rankingDto) {
        RankingEntity rankingEntity = leaderboardRepository.save(rankingMapper.fromDtoToEntity(rankingDto));
        return rankingMapper.fromEntityToDto(rankingEntity);
    }
}
