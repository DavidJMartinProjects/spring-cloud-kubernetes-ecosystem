package ecosystem.leaderboardservice.db.mapper;

import ecosystem.leaderboardservice.db.entity.RankingEntity;
import ecosystem.leaderboardservice.domain.pojo.RankingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RankingMapper {

    @Mapping(source = "rankingEntities.id", target = "RankingDto.id")
    @Mapping(source = "rankingEntities.name", target = "RankingDto.name")
    @Mapping(source = "rankingEntities.country", target = "RankingDto.country")
    @Mapping(source = "rankingEntities.score", target = "RankingDto.score")
    @Mapping(source = "rankingEntities.rank", target = "RankingDto.rank")
    List<RankingDto> fromEntityListToDtoList(List<RankingEntity> rankingEntities);

    @Mapping(source = "rankingEntity.id", target = "id")
    @Mapping(source = "rankingEntity.name", target = "name")
    @Mapping(source = "rankingEntity.country", target = "country")
    @Mapping(source = "rankingEntity.score", target = "score")
    @Mapping(source = "rankingEntity.rank", target = "rank")
    RankingDto fromEntityToDto(RankingEntity rankingEntity);

    @Mapping(source = "rankingDto.id", target = "id")
    @Mapping(source = "rankingDto.name", target = "name")
    @Mapping(source = "rankingDto.country", target = "country")
    @Mapping(source = "rankingDto.score", target = "score")
    @Mapping(source = "rankingDto.rank", target = "rank")
    RankingEntity fromDtoToEntity(RankingDto rankingDto);

}


