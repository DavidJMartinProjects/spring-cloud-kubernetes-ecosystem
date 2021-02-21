package com.ecosystem.numberservice.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NumberService {

    public static final int UPPER_BOUNDARY = 100;

    public int getRandomNumber() {
        return new Random().nextInt(UPPER_BOUNDARY);
    }

}