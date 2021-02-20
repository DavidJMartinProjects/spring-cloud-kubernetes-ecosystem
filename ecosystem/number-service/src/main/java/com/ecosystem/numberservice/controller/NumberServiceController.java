package com.ecosystem.numberservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping(NumberServiceController.BASE_PATH)
public class NumberServiceController {

    public static final String BASE_PATH = "/number-service";
    public static final String RANDOM_NUMBER_URL = "/random";
    public static final int UPPER_BOUNDARY = 100;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getGreeting() {
        log.info("received GET request to {}.", BASE_PATH);
        return "success. number-service is online.";
    }

    @GetMapping(RANDOM_NUMBER_URL)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public int getRandomNumber() {
        log.info("received GET request to {}.", RANDOM_NUMBER_URL);
        return new Random().nextInt(UPPER_BOUNDARY);
    }

}
