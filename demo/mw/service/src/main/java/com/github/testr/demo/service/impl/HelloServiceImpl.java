package com.github.testr.demo.service.impl;

import com.github.testr.demo.service.HelloService;
import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
}
