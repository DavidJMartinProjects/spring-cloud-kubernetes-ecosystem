package ecosystem.leaderboardservice.controller;

import ecosystem.leaderboardservice.domain.pojo.LeaderboardDto;
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

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getStatus() {
        log.info("received GET request to {} ", BASE_PATH);
        return "success.  leaderboard-service is online.";
    }

    @GetMapping(LEADERBOARD_URI)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<LeaderboardDto> getLeaderboard() {
        log.info("received GET request to {} ", BASE_PATH + LEADERBOARD_URI);
        return leaderboardService.getLeaderboard();
    }

}
