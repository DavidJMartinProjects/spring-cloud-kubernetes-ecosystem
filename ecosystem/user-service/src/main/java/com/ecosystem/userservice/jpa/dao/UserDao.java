package com.ecosystem.userservice.jpa.dao;

import com.ecosystem.userservice.domain.pojo.UserDto;
import com.ecosystem.userservice.jpa.repository.UserRepository;
import com.ecosystem.userservice.mapper.UserDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDtoMapper userMapper;

    public List<UserDto> fetchAllUsers() {
        return userMapper.from(userRepository.findAll());
    }

}
