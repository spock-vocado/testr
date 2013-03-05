package com.github.testr.sample.service;

public class FooServiceImpl implements FooService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
}
