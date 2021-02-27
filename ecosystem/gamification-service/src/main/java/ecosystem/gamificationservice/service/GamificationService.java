package ecosystem.gamificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecosystem.gamificationservice.domain.enums.HiLo;
import ecosystem.gamificationservice.domain.pojo.Attempt;
import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.response.AttemptResponse;
import ecosystem.gamificationservice.domain.pojo.response.RandomNumber;
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

    public AttemptResponse assessAttemptRequest(AttemptRequest attemptRequest) throws JsonProcessingException {
        Attempt attempt =
            Attempt.builder()
                .attemptRequest(attemptRequest)
                .nextNumber(getNextNumber())
                .build();

        return buildAttemptResponse(attempt);
    }

    private int getNextNumber() throws JsonProcessingException {
        String randomNumberResponse = restTemplateFacade.getForEntity(NUMBER_SERVICE_URL).getBody();
        try {
            return new ObjectMapper().readValue(randomNumberResponse, RandomNumber.class).getNumber();
        } catch (JsonProcessingException ex) {
            log.info("encountered error parsing response from url: {}, {}", NUMBER_SERVICE_URL, ex.getMessage());
            throw ex;
        }
    }

    private AttemptResponse buildAttemptResponse(Attempt attempt) {
        return AttemptResponse.builder()
            .nextNumber(attempt.getNextNumber())
            .isPreviousAttemptCorrect(
                getAttemptResult(attempt)
            )
            .build();
    }

    private boolean getAttemptResult(Attempt attempt) {
        HiLo actualAnswer = getActualAnswer(attempt.getAttemptRequest(), attempt.getNextNumber());
        HiLo userAnswer = HiLo.valueOf(attempt.getAttemptRequest().getAttemptAnswer());
        return userAnswer.equals(actualAnswer);
    }

    private HiLo getActualAnswer(AttemptRequest attemptRequest, int nextNumber) {
        return attemptRequest.getCurrentNumber() > nextNumber ? HiLo.HIGER : HiLo.LOWER;
    }

    public ResponseEntity<String> pingNumberService() {
        return restTemplateFacade.getForEntity(NUMBER_SERVICE_URL);
    }

}
