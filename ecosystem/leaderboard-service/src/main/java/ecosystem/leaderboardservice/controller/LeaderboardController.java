package ecosystem.leaderboardservice.controller;

import ecosystem.leaderboardservice.domain.pojo.RankingDto;
import ecosystem.leaderboardservice.service.LeaderboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(LeaderboardController.BASE_PATH)
public class LeaderboardController {

    public static final String BASE_PATH = "/leaderboard-service";
    public static final String LEADERBOARD_URI = "/leaderboard";
    public static final String RANK_URI = LEADERBOARD_URI + "/rank";

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getStatus() {
        log.info("received GET request to {} ", BASE_PATH);
        return "success. leaderboard-service is online.";
    }

    @GetMapping(LEADERBOARD_URI)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<RankingDto> getLeaderboard() {
        log.info("received GET request to {} ", BASE_PATH + LEADERBOARD_URI);
        return leaderboardService.getLeaderboard();
    }

    @PostMapping(RANK_URI)
    @ResponseStatus(HttpStatus.CREATED)
    public RankingDto postRanking(@RequestBody RankingDto rankingDto) {
        log.info("received POST request to {} ", BASE_PATH + RANK_URI);
        return leaderboardService.postRanking(rankingDto);
    }

}
