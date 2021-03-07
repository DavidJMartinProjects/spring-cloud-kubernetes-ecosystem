package ecosystem.leaderboardservice.db.repository;

import ecosystem.leaderboardservice.db.entity.RankingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends CrudRepository<RankingEntity, Long> {
    List<RankingEntity> findAll();
}
