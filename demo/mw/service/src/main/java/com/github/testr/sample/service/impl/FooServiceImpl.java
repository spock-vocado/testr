package com.github.testr.sample.service.impl;

import com.github.testr.sample.service.FooService;

public class FooServiceImpl implements FooService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
}
