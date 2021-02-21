package com.ecosystem.userservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void given_basePathUrl_when_getRequestOnBasePath_then_OK() throws Exception {
        // given
        String url = UserController.BASE_PATH;
        String expectedResponse = "success. users-service is online.";

        // when then
        ResultActions response = mockMvc.perform(get(url));

        // then
        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is(expectedResponse)));
    }

    @Test
    public void given_usersUrl_when_getRequestOnUsersPath_then_OK() throws Exception {
        // given
        String url = UserController.BASE_PATH + UserController.USERS_URL;

        // when then
        ResultActions response = mockMvc.perform(get(url));

        // then
        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstname", is("Dave")))
            .andExpect(jsonPath("$[0].lastname", is("Dangote")))
            .andExpect(jsonPath("$[0].score", is(10)))
            .andExpect(jsonPath("$[0].rank", is(1)));
    }

}