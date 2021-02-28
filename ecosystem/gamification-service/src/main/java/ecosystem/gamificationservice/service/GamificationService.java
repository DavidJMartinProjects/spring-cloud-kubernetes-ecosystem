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
    private RestTemplateFacade restTemplateFacade;

    public AttemptResponse assessAttemptRequest(AttemptRequest attemptRequest) throws JsonProcessingException {
        Attempt attempt =
            Attempt.builder()
                .attemptRequest(attemptRequest)
                .nextNumber(getNextNumber())
                .build();

        return buildAttemptResponse(attempt);
    }

    private AttemptResponse buildAttemptResponse(Attempt attempt) {
        return AttemptResponse.builder()
            .nextNumber(attempt.getNextNumber())
            .isPreviousAttemptCorrect(
                determineAttemptResult(attempt)
            )
            .build();
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

    private boolean determineAttemptResult(Attempt attempt) {
        return getUserAnswer(attempt).equals(getActualAnswer(attempt));
    }

    private HiLo getUserAnswer(Attempt attempt) {
        return HiLo.valueOf(attempt.getAttemptRequest().getAttemptAnswer().toUpperCase());
    }

    private HiLo getActualAnswer(Attempt attempt) {
        return attempt.getAttemptRequest().getCurrentNumber() > attempt.getNextNumber() ? HiLo.LOWER : HiLo.HIGHER;
    }

    public ResponseEntity<String> pingNumberService() {
        return restTemplateFacade.getForEntity(NUMBER_SERVICE_URL);
    }

}
