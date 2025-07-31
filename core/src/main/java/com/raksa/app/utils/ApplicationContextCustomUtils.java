package com.raksa.app.utils;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextCustomUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        ApplicationContextCustomUtils.applicationContext = applicationContext;
    }

    public static <T> T getBeanName(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

}
