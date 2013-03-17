package com.github.testr.demo.service;

import com.github.testr.demo.dal.entity.User;

public interface UserService {

    User newUser(String username, String email);

}
