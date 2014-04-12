package com.mobilepetroleum;

import java.io.Serializable;

public class Invocable implements Serializable {
    
    private final String beanName;
    private final String methodName;
    private final MethodParameter[] parameters;

    public Invocable(String beanName, String methodName, MethodParameter[] parameters) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getBeanName() { return beanName; }

    public String getMethodName() { return methodName; }

    public MethodParameter[] getParameters() { return parameters; }

}
