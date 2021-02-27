package ecosystem.gamificationservice.controller;

import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.response.AttemptResponse;
import ecosystem.gamificationservice.service.GamificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
@RequestMapping(GamificationController.BASE_PATH)
public class GamificationController {

    public static final String BASE_PATH = "/gamification-service";
    public static final String SUBMIT_URL = "/submit";
    public static final String PING_URL = "/ping";

    @Autowired
    private GamificationService gamificationService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(SUBMIT_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public AttemptResponse getResult(@RequestBody AttemptRequest attemptRequest) {
        log.info("received GET request to {}.", BASE_PATH + SUBMIT_URL);
        return new AttemptResponse();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getGreeting() {
        log.info("received GET request to {}.", BASE_PATH);
        return "success. gamification service is online.";
    }

    @GetMapping(PING_URL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> pingNumberService() {
        log.info("received GET request to {}.", BASE_PATH + PING_URL);
        return gamificationService.pingNumberService();
    }

}
