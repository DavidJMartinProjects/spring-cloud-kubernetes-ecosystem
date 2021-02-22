package com.ecosystem.numberservice.service;

import com.ecosystem.numberservice.domain.pojo.RandomNumber;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NumberService {

    public static final int UPPER_BOUNDARY = 100;

    public RandomNumber getRandomNumber() {
        return RandomNumber.builder()
            .number(new Random().nextInt(UPPER_BOUNDARY))
            .build();
    }

}