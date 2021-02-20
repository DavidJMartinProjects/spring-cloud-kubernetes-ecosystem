package com.ecosystem.numberservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(NumberServiceController.BASE_PATH)
public class NumberServiceController {

    public static final String BASE_PATH = "/number-service";

    @GetMapping
    @ResponseBody
    public String getGreeting() {
        log.info("received GET request to {}.", BASE_PATH);
        return "success. number-service is online.";
    }

}
