package com.ecosystem.numberservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NumberServiceControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void given_basePathUrl_when_getRequestOnBasePath_then_OK() throws Exception {
        // given
        String url = NumberServiceController.BASE_PATH;
        String expectedResponse = "success. number-service is online.";

        // when then
        ResultActions response = mockMvc.perform(get(url));

        // then
        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is(expectedResponse)));
    }

    @Test
    public void given_randomNumberUrl_when_getRequestOnRandomNumberUrl_then_OK() throws Exception {
        // given
        String url = NumberServiceController.BASE_PATH + NumberServiceController.RANDOM_NUMBER_URL;

        // when then
        ResultActions response = mockMvc.perform(get(url));

        // then
        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", lessThan(NumberServiceController.UPPER_BOUNDARY)));
    }

}
