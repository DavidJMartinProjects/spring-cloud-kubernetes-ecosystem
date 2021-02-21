package ecosystem.gamificationservice.controller;

import ecosystem.gamificationservice.domain.pojo.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.AttemptResponse;
import ecosystem.gamificationservice.service.GamificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;

@Slf4j
@RestController
@RequestMapping(GamificationController.BASE_PATH)
public class GamificationController {

    public static final String BASE_PATH = "/gamification-service";
    public static final String RESULT_URL = "/result";
    public static final String TEST_URL = "/test";

    @Autowired
    private GamificationService gamificationService;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getGreeting() {
        log.info("received GET request to {}.", BASE_PATH);
        return "success. gamification service is online.";
    }

    @ResponseBody
    @PostMapping(RESULT_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public AttemptResponse getResult(@RequestBody AttemptRequest attemptRequest) {
        log.info("received GET request to {}.", BASE_PATH + RESULT_URL);
        return gamificationService.accessAttempt(attemptRequest);
    }

    @ResponseBody
    @GetMapping(TEST_URL)
    @ResponseStatus(HttpStatus.OK)
    public int getResult() {
        log.info("received GET request to {}.", BASE_PATH + TEST_URL);
        return gamificationService.getNumber();
    }

}
