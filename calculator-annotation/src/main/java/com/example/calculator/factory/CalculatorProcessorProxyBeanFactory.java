package com.example.calculator.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

public class CalculatorProcessorProxyBeanFactory<T> implements FactoryBean<T>, ApplicationContextAware {
    private Class<T> type;

    public CalculatorProcessorProxyBeanFactory(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getObject() {
        CalculatorProcessorProxy handler  = new CalculatorProcessorProxy();
        Object newRepo = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, handler);
        return (T) newRepo;
    }

    @Override
    public Class<T> getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }
}
