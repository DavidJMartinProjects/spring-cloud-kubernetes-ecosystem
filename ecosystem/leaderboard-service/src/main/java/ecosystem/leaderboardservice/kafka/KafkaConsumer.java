package ecosystem.leaderboardservice.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaConsumer {

    @Getter @Setter
    private String payload = null;

    @Getter
    private final CountDownLatch latch = new CountDownLatch(1);

    private static final String TOPIC = "leaderboard";

    @KafkaListener(topics = TOPIC)
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        log.info("received payload='{}'", consumerRecord.toString());
        setPayload(consumerRecord.toString());
        latch.countDown();
    }

}