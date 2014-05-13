package com.mobilepetroleum;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class JunitSpringTest {

    protected ClassPathXmlApplicationContext ctx;

    @Before
    public void start() throws Exception {
        try {
            ctx = new ClassPathXmlApplicationContext("spring.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        if (ctx != null) {
            ctx.close();
        }
    }

}
