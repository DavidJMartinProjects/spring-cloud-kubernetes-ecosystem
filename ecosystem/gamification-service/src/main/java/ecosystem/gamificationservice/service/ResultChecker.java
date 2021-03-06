package ecosystem.gamificationservice.service;

import ecosystem.gamificationservice.domain.enums.HiLo;
import ecosystem.gamificationservice.domain.pojo.Attempt;
import org.springframework.stereotype.Component;

@Component
public class ResultChecker {

    public boolean checkResult(Attempt attempt) {
        HiLo userAnswer = HiLo.valueOf(attempt.getAttemptRequest().getAttemptAnswer().toUpperCase());
        HiLo actualAnswer = attempt.getAttemptRequest().getCurrentNumber() > attempt.getNextNumber() ? HiLo.LOWER : HiLo.HIGHER;
        return userAnswer.equals(actualAnswer);
    }

}
