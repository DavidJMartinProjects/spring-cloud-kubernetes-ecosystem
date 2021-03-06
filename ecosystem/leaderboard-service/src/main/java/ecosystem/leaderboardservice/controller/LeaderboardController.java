package ecosystem.leaderboardservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LeaderboardController.BASE_PATH)
public class LeaderboardController {

    public static final String BASE_PATH = "/leaderboard-service";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getStatus() {
        return "success.  leaderboard-service is online.";
    }

}
