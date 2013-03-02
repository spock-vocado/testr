package com.github.testr.builder.jpa;

import com.github.testr.builder.BuilderContext;
import com.github.testr.builder.BuilderException;
import com.github.testr.builder.ChildOf;
import com.github.testr.builder.DefaultBuilderHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Stack;

public abstract class AbstractJpaBuilderHandler extends DefaultBuilderHandler {

    protected static final Log log = LogFactory.getLog(AbstractJpaBuilderHandler.class);

    private final ThreadLocal<Stack<Object>> stackTL = new ThreadLocal<Stack<Object>>() {
        @Override
        protected Stack<Object> initialValue() {
            return new Stack<>();
        }
    };

    @Override
    public Object preProcess(Object o, BuilderContext context) {
        stackTL.get().push(o);
        return o;
    }

    @Override
    public Object postProcess(Object o, BuilderContext context) {
        Stack<Object> stack = stackTL.get();
        stack.pop();
        ChildOf childOf = context.getBuilderClass().getAnnotation(ChildOf.class);
        if (childOf != null) {
            if (stack.isEmpty() || !childOf.value().isAssignableFrom(stack.peek().getClass()))
                throw new BuilderException("Incorrect API usage: current object must be defined in " +
                        "the context of an instance of " + childOf.value().getName());
            log.debug("Child object " + o);
        }
        return persist(o);
    }

    protected abstract Object persist(Object o);

}

