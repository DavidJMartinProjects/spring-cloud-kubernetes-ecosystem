package ecosystem.gamificationservice.kafka;

import ecosystem.gamificationservice.kafka.event.CheckFinalScoreEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaFacade {

    @Autowired
    private final KafkaTemplate<String, CheckFinalScoreEvent> kafkaTemplate;

    @Autowired
    public KafkaFacade(KafkaTemplate<String, CheckFinalScoreEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, CheckFinalScoreEvent checkFinalScoreEvent) {
        kafkaTemplate.send(topic, checkFinalScoreEvent);
    }

}
