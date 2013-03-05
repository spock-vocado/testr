package com.github.testr.sample.service;

import com.github.testr.sample.dal.entity.User;

public interface UserService {

    User newUser(String username, String email);

}
