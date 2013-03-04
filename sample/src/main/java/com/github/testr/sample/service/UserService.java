package com.github.testr.sample.service;

import com.github.testr.sample.model.testr.User;

public interface UserService {

    User newUser(String username, String email);

}
