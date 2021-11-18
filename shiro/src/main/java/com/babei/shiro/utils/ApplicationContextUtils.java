package com.babei.shiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *@Author ba'bei
 *@Date 2021/10/20
 **/
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context= applicationContext;
    }

    //根据bean名字获取工厂中指定bean对象
    public static Object getBean(String beanName){
        return context.getBean(beanName);

    }
}
