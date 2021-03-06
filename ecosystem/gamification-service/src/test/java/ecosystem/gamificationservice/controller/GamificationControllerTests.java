package ecosystem.gamificationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecosystem.gamificationservice.GamificationServiceApplicationTests;
import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.service.facade.RestTemplateFacade;
import ecosystem.gamificationservice.kafka.event.CheckFinalScoreEvent;
import ecosystem.gamificationservice.service.GamificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static ecosystem.gamificationservice.kafka.config.KafkaTopicConfig.LEADERBOARD_TOPIC;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@AutoConfigureMockMvc
@EmbeddedKafka(controlledShutdown = true, topics = LEADERBOARD_TOPIC)
public class GamificationControllerTests extends GamificationServiceApplicationTests {

    public static final int NEXT_NUMBER = 45;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplateFacade restTemplateFacade;

    @InjectMocks
    private GamificationService gamificationService;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void given_validAttemptRequest_when_postRequestOnSubmit_then_expectedResponseReturned() throws Exception {
        Consumer<String, CheckFinalScoreEvent> consumerServiceTest = setupKafkaConsumerListener();

        // given
        String attemptRequest = buildAttemptRequest(50, "higher");

        // when
        when(restTemplateFacade.getForEntity(any())).thenReturn(buildNumberServiceResponseEntity(NEXT_NUMBER));

        // then verify api response
        mockMvc.perform(post(GamificationController.BASE_PATH + GamificationController.SUBMIT_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(attemptRequest))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.nextNumber").value(NEXT_NUMBER))
            .andExpect(jsonPath("$.previousAttemptCorrect").value(false))
            .andReturn();

        // then verify kafka producer event is sent
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, LEADERBOARD_TOPIC);
        ConsumerRecord<String, CheckFinalScoreEvent> consumerRecordOfExampleDTO =
            KafkaTestUtils.getSingleRecord(consumerServiceTest, LEADERBOARD_TOPIC);
        CheckFinalScoreEvent valueReceived = consumerRecordOfExampleDTO.value();
        consumerServiceTest.close();

        log.info("valueReceived: " + valueReceived.toString());
    }

    private Consumer<String, CheckFinalScoreEvent> setupKafkaConsumerListener() {
        // setup kafka consumer listener
        Map<String, Object> consumerProps =
            KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, CheckFinalScoreEvent> cf =
            new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(),
                new JsonDeserializer<>(CheckFinalScoreEvent.class, false));
        return cf.createConsumer();
    }

    private String buildAttemptRequest(int currentNumber, String attemptAnswer) throws JsonProcessingException {
        AttemptRequest attemptRequest = AttemptRequest.builder()
            .attemptCount(1)
            .currentNumber(currentNumber)
            .attemptAnswer(attemptAnswer)
            .build();

        return new ObjectMapper().writeValueAsString(attemptRequest);
    }

    private ResponseEntity<String> buildNumberServiceResponseEntity(int nextNumber) {
        return new ResponseEntity<String>("{\"number\":"+nextNumber+"}", HttpStatus.OK);
    }

}

