package com.mobilepetroleum;


import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;

import java.rmi.RemoteException;

class SpringServiceBean implements SpringService {

    public static final Gson GSON = new Gson();

    private final static ResultToStringConverter resultToStringConverter = new ResultToStringConverter();

    private ApplicationContext applicationContext;

    public String invoke(Invocable invocable) throws RemoteException {
        return invoke(invocable.getBeanName(), invocable.getMethodName(), invocable.getParameters());
    }

    public String invoke(String beanName, String methodName, MethodParameter[] parameters) {
        try {
            Object bean = applicationContext.getBean(beanName);
            Object[] params = new Object[parameters.length];
            Class<?>[] types = new Class<?>[parameters.length];

            for (int i = 0; i < parameters.length; i++) {
                MethodParameter parameter = parameters[i];
                types[i] = Classes.forName(parameter.getType());
                params[i] = createObject(parameter);
            }

            Object result = Classes.invoke(bean, methodName, types, params);
            return resultToStringConverter.toString(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Object createObject(MethodParameter parameter) {
        String value = parameter.getValue();
        String type = parameter.getType();
        return GSON.fromJson(value, Classes.forName(type));
    }

    void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
