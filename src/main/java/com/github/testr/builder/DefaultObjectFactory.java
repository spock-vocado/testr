package com.github.testr.builder;

public class DefaultObjectFactory implements IObjectFactory {

    @Override
    public Object newInstance(Class<?> objectClass) {
        Object v = BuilderUtil.newInstance(objectClass);
        System.out.println("NewInstance: " + objectClass.getName());
        return v;
    }

}
