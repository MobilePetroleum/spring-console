package com.mobilepetroleum;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;


public class Main {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("sprint-test-app start");

        {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

            CountDownLatch countDownLatch = (CountDownLatch) ctx.getBean("countDownLatch");

            countDownLatch.await();

            ctx.close();
        }

        System.out.println("sprint-test-app close");
    }

}
