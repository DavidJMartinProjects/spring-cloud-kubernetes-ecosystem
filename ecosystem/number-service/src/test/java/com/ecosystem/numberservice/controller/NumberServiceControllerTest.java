package com.ecosystem.numberservice.controller;

import com.ecosystem.numberservice.NumberServiceApplicationTests;
import com.ecosystem.numberservice.service.NumberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class NumberServiceControllerTest extends NumberServiceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void given_basePathUrl_when_getRequestOnBasePath_then_OK() throws Exception {
        // given
        String url = NumberServiceController.BASE_PATH;
        String expectedResponse = "success. number-service is online.";

        // when
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

        // when
        ResultActions response = mockMvc.perform(get(url));

        // then
        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.number", lessThan(NumberService.UPPER_BOUNDARY)));
    }

}
