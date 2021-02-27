package ecosystem.gamificationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecosystem.gamificationservice.controller.GamificationController;
import ecosystem.gamificationservice.domain.pojo.request.AttemptRequest;
import ecosystem.gamificationservice.rest.RestTemplateFacade;
import ecosystem.gamificationservice.service.GamificationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GamificationControllerTests {

    public static final int NEXT_NUMBER = 55;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RestTemplateFacade restTemplateFacade;

    @InjectMocks
    GamificationService gamificationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void given_validAttemptRequest_when_postRequestOnSubmit_then_expectedResponseReturned() throws Exception {

        //given
        String attemptRequest = buildAttemptRequest(50, "higher");


        //when
        when(restTemplateFacade.getForEntity(any())).thenReturn(buildNumberServiceResponseEntity(NEXT_NUMBER));

        //then
        mockMvc.perform(post(GamificationController.BASE_PATH + GamificationController.SUBMIT_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(attemptRequest))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.nextNumber").value(NEXT_NUMBER))
            .andExpect(jsonPath("$.previousAttemptCorrect").value(true))
            .andReturn();

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

