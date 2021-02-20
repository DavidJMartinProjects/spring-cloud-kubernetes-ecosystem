package com.ecosystem.userservice.controller;

import com.ecosystem.userservice.domain.pojo.UserDto;
import com.ecosystem.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_PATH)
public class UserController {

    public static final String BASE_PATH = "/users-service";
    public static final String USERS_URL = "/users";

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getGreeting() {
        log.info("received GET request to {}.", BASE_PATH);
        return "success. users-service is online.";
    }

    @GetMapping(USERS_URL)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        log.info("received GET request to {}.", BASE_PATH + USERS_URL);
        return userService.fetchAllUsers();
    }

}
