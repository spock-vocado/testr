package com.github.testr.sample.service;

import com.github.testr.sample.model.testr.User;
import com.github.testr.sample.repository.testr.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User newUser(String username, String email) {
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        return userRepository.save(u);
    }
}
