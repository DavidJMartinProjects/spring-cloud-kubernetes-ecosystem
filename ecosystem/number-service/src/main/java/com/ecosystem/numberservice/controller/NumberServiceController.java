package com.ecosystem.numberservice.controller;

import com.ecosystem.numberservice.domain.pojo.RandomNumber;
import com.ecosystem.numberservice.service.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(NumberServiceController.BASE_PATH)
public class NumberServiceController {

    @Autowired
    NumberService numberService;

    public static final String BASE_PATH = "/number-service";
    public static final String RANDOM_NUMBER_URL = "/random";

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
    public RandomNumber getRandomNumber() {
        log.info("received GET request to {}.", BASE_PATH + RANDOM_NUMBER_URL);
        return numberService.getRandomNumber();
    }

}
