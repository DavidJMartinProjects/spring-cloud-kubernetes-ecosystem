package ecosystem.gamificationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.response.AttemptResponse;
import ecosystem.gamificationservice.service.GamificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(GamificationController.BASE_PATH)
public class GamificationController {

    public static final String BASE_PATH = "/gamification-service";
    public static final String SUBMIT_URL = "/submit";

    @Autowired
    private GamificationService gamificationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getStatus() {
        log.info("received GET request to {}.", BASE_PATH);
        return "success. gamification service is online.";
    }

    @PostMapping(SUBMIT_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public AttemptResponse getResult(@RequestBody AttemptRequest attemptRequest) throws JsonProcessingException {
        log.info("received POST request to {} with request body: {}", BASE_PATH + SUBMIT_URL, attemptRequest);
        return gamificationService.assessAttemptRequest(attemptRequest);
    }

}
