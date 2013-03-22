package com.github.testr.builder.jpa;

import com.github.testr.builder.BuilderContext;
import com.github.testr.builder.BuilderException;
import com.github.testr.builder.DefaultBuilderHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import java.util.Stack;

public class JpaBuilderHandler extends DefaultBuilderHandler {

    protected static final Log log = LogFactory.getLog(JpaBuilderHandler.class);


    private Stack<BuilderContext> stack = new Stack<>();
    private EntityManager entityManager;

    public JpaBuilderHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void preProcess(BuilderContext context) {
        super.preProcess(context);
        stack.push(context);
    }

    @Override
    public void postProcess(BuilderContext context) {
        super.postProcess(context);
        final BuilderContext current = stack.pop();
        final ChildOf childOf = context.getBuilderClass().getAnnotation(ChildOf.class);
        boolean doPersist = true;
        if (childOf != null) {
            if (stack.isEmpty() || !childOf.entityClass().isAssignableFrom(stack.peek().getObjectClass())) {
                throw new BuilderException("Incorrect API usage: current object must be defined in " +
                        "the context of an instance of " + childOf.entityClass().getName());
            }
            log.debug("Child object " + current.getValue());
            log.debug("backRef = " + childOf.parentProperty());
            final BuilderContext parent = stack.peek();
            stack.peek().addPostAction(new Runnable() {
                @Override
                public void run() {
                    ReflectionTestUtils.setField(current.getValue(), childOf.parentProperty(), parent.getValue());
                }
            });
            doPersist = false;
        }
        current.flushPostActions();
        if (doPersist)
            persist(current.getValue());
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected Object persist(Object o) {
        EntityManager em = getEntityManager();
        if (stack.isEmpty()) {
            em.persist(o);
            em.flush();
            em.clear();
            log.debug("Persisted+flushed: " + o);
        } else {
            em.persist(o);
            log.debug("Persisted " + o);
        }
        return o;
    }

}

