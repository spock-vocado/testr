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
public class DynBuilderFactory implements IBuilderFactory {

    private static final Log log = LogFactory.getLog(DynBuilderFactory.class);

    private ConfigurableConversionService conversionService = new DefaultConversionService();
    private IObjectHandler handler = new DefaultObjectHandler();

    public ConfigurableConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConfigurableConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public IObjectHandler getHandler() {
        return handler;
    }

    public void setHandler(IObjectHandler handler) {
        this.handler = handler;
    }

    @Override
    public <T extends IBuilder<?>> T get(Class<T> builderClass) {
        Class<?> entityClass = (Class<?>) ((ParameterizedType) builderClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return (T) Proxy.newProxyInstance(
                getLoader(),
                new Class[]{builderClass},
                new BuilderInvocationHandler(new BuilderInfo(builderClass, entityClass)));
    }

    private ClassLoader getLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private class BuilderInvocationHandler implements InvocationHandler {

        private final Object v;
        private final BuilderInfo info;

        BuilderInvocationHandler(BuilderInfo info) {
            this.info = info;
            this.v = handler.preProcess(handler.newInstance(this.info), this.info);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("build")) {
                return handler.postProcess(v, info);
            }
            if (method.getDeclaringClass() == info.getBuilderClass()) {
                String name = method.getName();
                Object value = args[0];
                Field f = ReflectionUtils.findField(info.getObjectClass(), name);
                if (f == null)
                    throw new BuilderException("Could not find field '" + name + "' in class"
                            + info.getObjectClass().getName());
                f.setAccessible(true);
                Object convertedValue = convert(f.getType(), value);
                if (log.isDebugEnabled())
                    log.debug(format("Set field '%s' = %s", name, String.valueOf(convertedValue)));
                ReflectionUtils.setField(f, v, convertedValue);
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
