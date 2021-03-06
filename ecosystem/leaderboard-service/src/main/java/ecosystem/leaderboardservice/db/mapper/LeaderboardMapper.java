package ecosystem.leaderboardservice.db.mapper;

import ecosystem.leaderboardservice.db.entity.LeaderboardEntity;
import ecosystem.leaderboardservice.domain.pojo.LeaderboardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaderboardMapper {

    @Mapping(source = "leaderboardEntities.id", target = "LeaderboardDto.id")
    @Mapping(source = "leaderboardEntities.name", target = "LeaderboardDto.name")
    @Mapping(source = "leaderboardEntities.country", target = "LeaderboardDto.country")
    @Mapping(source = "leaderboardEntities.score", target = "LeaderboardDto.score")
    @Mapping(source = "leaderboardEntities.rank", target = "LeaderboardDto.rank")
    List<LeaderboardDto> from(List<LeaderboardEntity> leaderboardEntities);

}


