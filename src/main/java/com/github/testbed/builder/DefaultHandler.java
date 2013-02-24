package com.github.testbed.builder;

public class DefaultHandler implements IHandler {

    @Override
    public Object newInstance(Class<?> objectClass) {
        Object v = BuilderUtil.newInstance(objectClass);
        System.out.println("NewInstance: " + objectClass.getName());
        return v;
    }

    @Override
    public Object postProcess(Object v) {
        System.out.println("PostCreate: " + v);
        return v;
    }

}
