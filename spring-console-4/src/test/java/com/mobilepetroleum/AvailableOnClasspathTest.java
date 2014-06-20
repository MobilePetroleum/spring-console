package com.mobilepetroleum;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static org.fest.assertions.api.Assertions.assertThat;

public class AvailableOnClasspathTest {

    private AbstractApplicationContext context;

    @Before
    public void before() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @After
    public void after() {
        context.close();
    }

    @Test
    public void shouldLoadSpringConsole() {
        // when
        boolean springConsoleExist = context.containsBean("springConsole");
        // then
        assertThat(springConsoleExist).isTrue();
    }

    @Test
    public void shouldNotLoadPostgresDriver() {
        // when
        boolean postgresDriverExist = context.containsBean("postgresDriver");
        // then
        assertThat(postgresDriverExist).isFalse();
    }

}
