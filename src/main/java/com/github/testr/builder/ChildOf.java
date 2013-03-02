package com.github.testr.builder;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@java.lang.annotation.Target({TYPE})
@java.lang.annotation.Retention(RUNTIME)
@java.lang.annotation.Documented
public @interface ChildOf {
    Class<?> value();
}
