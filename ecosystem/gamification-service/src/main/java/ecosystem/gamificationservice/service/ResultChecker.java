package ecosystem.gamificationservice.service;

import ecosystem.gamificationservice.domain.enums.HiLo;
import ecosystem.gamificationservice.domain.pojo.Attempt;
import org.springframework.stereotype.Component;

@Component
public class ResultChecker {

    public boolean checkResult(Attempt attempt) {
        return getUserAnswer(attempt).equals(getActualAnswer(attempt));
    }

    private HiLo getUserAnswer(Attempt attempt) {
        return HiLo.valueOf(attempt.getAttemptRequest().getAttemptAnswer().toUpperCase());
    }

    private HiLo getActualAnswer(Attempt attempt) {
        return attempt.getAttemptRequest().getCurrentNumber() > attempt.getNextNumber() ? HiLo.LOWER : HiLo.HIGHER;
    }

}
