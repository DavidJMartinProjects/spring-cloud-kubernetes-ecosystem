package ecosystem.gamificationservice.service;

import ecosystem.gamificationservice.domain.pojo.AttemptRequest;
import ecosystem.gamificationservice.domain.pojo.AttemptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamificationService {

    @Autowired
    RestTemplateFacade restTemplateFacade;

    public AttemptResponse accessAttempt(AttemptRequest attemptRequest) {
        attemptRequest =
            new AttemptRequest(attemptRequest.getCurrentNumber(), restTemplateFacade.getRandomNumber());

        return new AttemptResponse(attemptRequest.getNextNumber(), attemptRequest.isHigher());
    }

    public int getNumber() {
        return restTemplateFacade.getRandomNumber();
    }
}
