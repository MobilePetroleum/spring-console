package com.mobilepetroleum;

import com.mobilepetroleum.test.TestRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class App {

    void start() throws Exception {
        System.out.println("sprint-test-app start");

        {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

            ((TestRunner) ctx.getBean("testRunner")).execute();

            ctx.close();
        }

        System.out.println("sprint-test-app close");
    }

}
