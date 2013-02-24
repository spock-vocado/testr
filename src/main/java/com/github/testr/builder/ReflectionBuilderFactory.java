package com.github.testr.builder;

import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Date;

@SuppressWarnings("unchecked")
public class ReflectionBuilderFactory implements IBuilderFactory {

    private ConfigurableConversionService conversionService = new DefaultConversionService();
    private IObjectFactory objFactory = new DefaultObjectFactory();
    private IObjectProcessor objProcessor = new DefaultObjectProcessor();

    public ConfigurableConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConfigurableConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public IObjectFactory getObjFactory() {
        return objFactory;
    }

    public void setObjFactory(IObjectFactory objFactory) {
        this.objFactory = objFactory;
    }

    public IObjectProcessor getObjProcessor() {
        return objProcessor;
    }

    public void setObjProcessor(IObjectProcessor objProcessor) {
        this.objProcessor = objProcessor;
    }

    @Override
    public <T extends IBuilder<?>> T create(Class<T> builderClass) {
        Class<?> entityClass = (Class<?>) ((ParameterizedType) builderClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return (T) Proxy.newProxyInstance(
                getLoader(),
                new Class[]{builderClass},
                new BuilderInvocationHandler(entityClass, builderClass));
    }

    private ClassLoader getLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private class BuilderInvocationHandler implements InvocationHandler {

        private final Object v;
        private final Class<? extends IBuilder<?>> builderClass;
        private final Class<?> objectClass;

        BuilderInvocationHandler(Class<?> objectClass, Class<? extends IBuilder<?>> builderClass) {
            this.builderClass = builderClass;
            this.objectClass = objectClass;
            this.v = objFactory.newInstance(objectClass);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("build")) {
                return objProcessor.process(v);
            }
            if (method.getDeclaringClass() == builderClass) {
                String name = method.getName();
                Object value = args[0];
                Field f = ReflectionUtils.findField(objectClass, name);
                if (f == null) {
                    throw new IllegalArgumentException("Could not find field '" + name + "' in class" + objectClass.getName());
                }
                f.setAccessible(true);
                Object convertedValue = convert(f.getType(), value);
                System.out.format("set %s = %s\n", name, String.valueOf(convertedValue));
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
            //throw new IllegalArgumentException("Cannot convert from " + object.getClass().getName() + " to " + targetClass.getName());
        }
    }


}
