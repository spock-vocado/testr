package com.github.testr.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Date;

import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class BuilderFactory {

    private static final Log log = LogFactory.getLog(BuilderFactory.class);

    private ConfigurableConversionService conversionService = new DefaultConversionService();
    private IBuilderHandler handler;

    public BuilderFactory(IBuilderHandler handler) {
        this.handler = handler;
    }

    public BuilderFactory() {
        this(new DefaultBuilderHandler());
    }

    public ConfigurableConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConfigurableConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public IBuilderHandler getHandler() {
        return handler;
    }

    public void setHandler(IBuilderHandler handler) {
        this.handler = handler;
    }

    public <T extends IBuilder<?>> T create(Class<T> builderClass) {
        Class<?> entityClass = (Class<?>) ((ParameterizedType) builderClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return (T) Proxy.newProxyInstance(
                getLoader(),
                new Class[]{builderClass},
                new BuilderInvocationHandler(new BuilderContext(builderClass, entityClass, this)));
    }

    private ClassLoader getLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private class BuilderInvocationHandler implements InvocationHandler {

        private final BuilderContext ctx;

        BuilderInvocationHandler(BuilderContext ctx) {
            this.ctx = ctx;
            handler.newInstance(ctx);
            handler.preProcess(ctx);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("build")) {
                handler.postProcess(ctx);
                return ctx.getValue();
            }
            if (method.getDeclaringClass() == ctx.getBuilderClass()) {
                String name = method.getName();
                Object value = args[0];
                Field f = ReflectionUtils.findField(ctx.getObjectClass(), name);
                if (f == null)
                    throw new BuilderException("Could not find field '" + name + "' in class "
                            + ctx.getObjectClass().getName());
                f.setAccessible(true);
                Object convertedValue = convert(f.getType(), value);
                if (log.isDebugEnabled())
                    log.debug(format("Set field '%s' = %s", name, String.valueOf(convertedValue)));
                ReflectionUtils.setField(f, ctx.getValue(), convertedValue);
                return proxy;
            }
            return proxy;
        }

        private Object convert(Class<?> targetClass, Object object) {
            if (object == null || targetClass.isInstance(object)) {
                return object;
            }
            if (object instanceof String) {
                if (Date.class.isAssignableFrom(targetClass)) {
                    return BuilderUtil.parseIsoDate((String) object);
                }
            }
            if (object instanceof IBuilder) {
                return ((IBuilder) object).build();
            }
            return conversionService.convert(object, targetClass);
        }
    }


}
