package com.mobilepetroleum;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

class ForcedProxy implements MethodBeforeAdvice {

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Proxied! methodName = " + method.getName());
    }

}
