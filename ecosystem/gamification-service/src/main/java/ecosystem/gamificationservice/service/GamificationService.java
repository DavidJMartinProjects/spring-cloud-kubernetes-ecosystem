package ecosystem.gamificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ecosystem.gamificationservice.domain.pojo.Attempt;
import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.response.AttemptResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GamificationService {

    @Autowired
    private NumberService numberService;

    @Autowired
    private ResultChecker resultChecker;

    public AttemptResponse assessAttemptRequest(AttemptRequest attemptRequest) throws JsonProcessingException {
        Attempt attempt =
            Attempt.builder()
                .attemptRequest(attemptRequest)
                .nextNumber(numberService.getNextNumber())
                .build();

        return buildAttemptResponse(attempt);
    }

    private AttemptResponse buildAttemptResponse(Attempt attempt) {
        return AttemptResponse.builder()
            .nextNumber(attempt.getNextNumber())
            .isPreviousAttemptCorrect(resultChecker.checkResult(attempt))
            .build();
    }

}
