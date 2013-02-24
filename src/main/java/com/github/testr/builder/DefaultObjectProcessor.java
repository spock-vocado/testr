package com.github.testr.builder;

import com.github.testr.builder.IObjectProcessor;

public class DefaultObjectProcessor implements IObjectProcessor {

    @Override
    public Object process(Object v) {
        System.out.println("Processing: " + v);
        return v;
    }

}
