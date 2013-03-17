package com.github.testr.demo.service;

import com.github.testr.demo.dal.entity.User;

import java.util.List;

public interface UserService {

    User newUser(String username, String email);

    List<User> findUsers();

}
