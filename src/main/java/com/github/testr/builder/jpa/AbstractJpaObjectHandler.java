package com.github.testr.builder.jpa;

import com.github.testr.builder.BuilderException;
import com.github.testr.builder.BuilderInfo;
import com.github.testr.builder.ChildOf;
import com.github.testr.builder.DefaultObjectHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Stack;

public abstract class AbstractJpaObjectHandler extends DefaultObjectHandler {

    protected static final Log log = LogFactory.getLog(AbstractJpaObjectHandler.class);

    private final ThreadLocal<Stack<Object>> stackTL = new ThreadLocal<Stack<Object>>() {
        @Override
        protected Stack<Object> initialValue() {
            return new Stack<>();
        }
    };

    @Override
    public Object preProcess(Object o, BuilderInfo info) {
        stackTL.get().push(o);
        return o;
    }

    @Override
    public Object postProcess(Object o, BuilderInfo info) {
        Stack<Object> stack = stackTL.get();
        stack.pop();

        ChildOf childOf = info.getBuilderClass().getAnnotation(ChildOf.class);
        if (childOf != null) {
            if (stack.isEmpty())
                throw new BuilderException("Incorrect API usage: current object must be defined in " +
                        "the context of an instance of " + childOf.value().getName());
            if (childOf.value().isAssignableFrom(stack.peek().getClass())) {
                log.debug("Child object " + o);
            } else {
                throw new BuilderException("Incorrect API usage: current object must be defined in " +
                        "the context of an instance of " + childOf.value().getName());
            }
        }
        return persist(o);
    }

    protected abstract Object persist(Object o);

}

