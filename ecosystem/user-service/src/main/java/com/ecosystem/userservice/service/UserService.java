package com.ecosystem.userservice.service;

import com.ecosystem.userservice.domain.UserDto;
import com.ecosystem.userservice.jpa.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<UserDto> fetchAllUsers() {
        return userDao.fetchAllUsers();
    }

}
