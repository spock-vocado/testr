package com.github.testr.sample.service;

import com.github.testr.sample.dal.entity.User;

import java.util.List;

public interface UserService {

    User newUser(String username, String email);

    List<User> findUsers();

}
