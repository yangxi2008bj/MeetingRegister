package com.lachesis.windranger.springmvc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware{
	 
    private static ApplicationContext applicationContext;
 
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
 
    public  void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
    	SpringUtil.applicationContext = applicationContext;
    }
     
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {  
        return (T) applicationContext.getBean(name);  
    }
}