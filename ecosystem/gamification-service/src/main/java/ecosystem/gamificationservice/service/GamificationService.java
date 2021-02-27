package ecosystem.gamificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.response.RandomNumberResponse;
import ecosystem.gamificationservice.rest.RestTemplateFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GamificationService {

    public static final String NUMBER_SERVICE_URL = "http://number-service:9091/number-service/random";

    @Autowired
    RestTemplateFacade restTemplateFacade;

    @Autowired
    ObjectMapper objectMapper;

    public void accessAttempt(AttemptRequest attemptRequest) {
//        attemptRequest =
//            new AttemptRequest(attemptRequest.getCurrentNumber(), restTemplateFacade.getRandomNumber());
//
//        return new AttemptResponse(attemptRequest.getNextNumber(), attemptRequest.isHigher());
    }

//    public RandomNumberResponse getRandomNumber() {
//        responseEntity = restTemplateFacade.getForRandomNumber(randomNumberServiceUrl, restTemplate);
//        log.info("responseEntity: " + responseEntity.getBody());
//        return objectMapper.convertValue(responseEntity.getBody(), RandomNumberResponse.class);
//    }

    public ResponseEntity<String> pingNumberService() {
        return restTemplateFacade.getForEntity(NUMBER_SERVICE_URL);
    }
}
