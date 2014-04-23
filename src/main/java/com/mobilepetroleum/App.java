package com.mobilepetroleum;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

class App {

    void start() throws Exception {
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
