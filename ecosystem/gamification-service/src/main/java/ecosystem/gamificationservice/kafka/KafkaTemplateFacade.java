package ecosystem.gamificationservice.kafka;

import ecosystem.gamificationservice.kafka.event.CheckFinalScoreEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class KafkaTemplateFacade {

    @Autowired
    private final KafkaTemplate<String, CheckFinalScoreEvent> kafkaTemplate;

    @Autowired
    public KafkaTemplateFacade(KafkaTemplate<String, CheckFinalScoreEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, CheckFinalScoreEvent checkFinalScoreEvent) {
        ListenableFuture<SendResult<String, CheckFinalScoreEvent>> future =
            kafkaTemplate.send(topic, checkFinalScoreEvent);

        future.addCallback(new ListenableFutureCallback<SendResult<String, CheckFinalScoreEvent>>() {
            @Override
            public void onSuccess(SendResult<String, CheckFinalScoreEvent> result) {
                log.info("kafka sent the following message to the leaderboard topic: [" + checkFinalScoreEvent + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("kafka was unable to send message=["+ checkFinalScoreEvent +"] due to : " + ex.getMessage());
            }
        });
    }

}
