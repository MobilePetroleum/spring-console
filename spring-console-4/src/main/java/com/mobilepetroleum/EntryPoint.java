package com.mobilepetroleum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.Driver;

class EntryPoint {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        Driver bean = applicationContext.getBean(Driver.class);

        System.out.println(bean);


    }
}
