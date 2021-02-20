package com.ecosystem.userservice.domain;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String firstname;
    private String lastname;
    private int score;
    private int rank;

}
