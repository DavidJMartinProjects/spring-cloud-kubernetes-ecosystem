package ecosystem.leaderboardservice.controller;

import ecosystem.leaderboardservice.LeaderboardServiceApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class LeaderboardControllerTests extends LeaderboardServiceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void given_basePathUrl_when_getRequestOnBasePath_then_OK() throws Exception {
        // given
        String url = LeaderboardController.BASE_PATH;
        String expectedResponse = "success. leaderboard-service is online.";

        // when
        ResultActions response = mockMvc.perform(get(url));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(expectedResponse)));
    }

    @Test
    public void given_usersUrl_when_getRequestOnUsersPath_then_OK() throws Exception {
        // given
        String url = LeaderboardController.BASE_PATH + LeaderboardController.LEADERBOARD_URI;

        // when
        ResultActions response = mockMvc.perform(get(url));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Dave")))
                .andExpect(jsonPath("$[0].country", is("Ireland")))
                .andExpect(jsonPath("$[0].score", is(10)))
                .andExpect(jsonPath("$[0].rank", is(1)));
    }

}