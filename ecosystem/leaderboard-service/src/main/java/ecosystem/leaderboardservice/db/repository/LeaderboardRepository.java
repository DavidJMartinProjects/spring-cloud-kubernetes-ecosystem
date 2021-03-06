package ecosystem.leaderboardservice.db.repository;

import ecosystem.leaderboardservice.db.entity.LeaderboardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends CrudRepository<LeaderboardEntity, Long> {
    List<LeaderboardEntity> findAll();
}
