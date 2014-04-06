package com.mobilepetroleum;


import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;

class SpringServiceBean implements SpringService {

    private ApplicationContext applicationContext;

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

            Object result = invoke(bean, methodName, types, params);
            return new Gson().toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object createObject(MethodParameter parameter) {
        String value = parameter.getValue();
        String type = parameter.getType();
        return new Gson().fromJson(value, Classes.forName(type));
    }

    private Object invoke(Object o, String methodName, Class<?>[] types, Object[] params) {
        try {
            return o.getClass().getDeclaredMethod(methodName, types).invoke(o, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
