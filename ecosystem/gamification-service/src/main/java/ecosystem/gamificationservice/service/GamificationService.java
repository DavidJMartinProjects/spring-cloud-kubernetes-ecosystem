package ecosystem.gamificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ecosystem.gamificationservice.domain.pojo.Attempt;
import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.response.AttemptResponse;
import ecosystem.gamificationservice.kafka.KafkaTemplateFacade;
import ecosystem.gamificationservice.kafka.event.CheckFinalScoreEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ecosystem.gamificationservice.kafka.config.KafkaTopicConfig.LEADERBOARD_TOPIC;

@Slf4j
@Service
public class GamificationService {

    @Autowired
    private NumberService numberService;

    @Autowired
    private ResultChecker resultChecker;

    @Autowired
    KafkaTemplateFacade kafkaTemplateFacade;

    public AttemptResponse assessAttemptRequest(AttemptRequest attemptRequest) throws JsonProcessingException {
        Attempt attempt =
            Attempt.builder()
                .attemptRequest(attemptRequest)
                .nextNumber(numberService.getNextNumber())
                .build();

        return buildAttemptResponse(attempt);
    }

    private AttemptResponse buildAttemptResponse(Attempt attempt) {
        boolean isAttemptCorrect = resultChecker.checkResult(attempt);

        if(!isAttemptCorrect) {
            sendFinalScoreForRanking(attempt);
        }

        return AttemptResponse.builder()
            .nextNumber(attempt.getNextNumber())
            .isPreviousAttemptCorrect(isAttemptCorrect)
            .build();
    }

    public void sendFinalScoreForRanking(Attempt attempt) {
        CheckFinalScoreEvent finalScoreEvent =
            CheckFinalScoreEvent.builder()
                .name("test-name")
                .country("test-country")
                .score(attempt.getNextNumber())
                .build();
        kafkaTemplateFacade.sendMessage(LEADERBOARD_TOPIC, finalScoreEvent);
    }

}
