package com.mobilepetroleum;


import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

class SpringServiceBean implements SpringService {

    public static final Gson GSON = new Gson();
    private static final Restrictions restrictions = new Restrictions();

    private final static ResultToStringConverter resultToStringConverter = new ResultToStringConverter();

    private ApplicationContext applicationContext;

    public String invoke(Invocable invocable) throws RemoteException {
        return invoke(invocable.getBeanName(), invocable.getMethodName(), invocable.getParameters());
    }

    public String invoke(String beanName, String methodName, MethodParameter[] parameters) {
        try {
            if (!applicationContext.containsBean(beanName)) {
                throw beanOrMethodNotFoundOrAllowed(beanName);
            }
            Class type = applicationContext.getType(beanName);
            Object bean = applicationContext.getBean(beanName);
            Object[] params = new Object[parameters.length];
            Class<?>[] types = new Class<?>[parameters.length];

            for (int i = 0; i < parameters.length; i++) {
                MethodParameter parameter = parameters[i];
                types[i] = Classes.forName(parameter.getType());
                params[i] = createObject(parameter);
            }

            Optional<Method> method = Classes.findMethod(type, methodName, types);

            if (!method.isPresent()) {
                throw beanOrMethodNotFoundOrAllowed(beanName);
            }

            if (!restrictions.allowed(type, method.get())) {
                throw beanOrMethodNotFoundOrAllowed(beanName);
            }
            Object result = Classes.invoke(bean, method.get(), params);
            return resultToStringConverter.toString(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private RuntimeException beanOrMethodNotFoundOrAllowed(String beanName) {return new RuntimeException(String.format("No bean named '%s' or method is accessible or found", beanName));}

    private Object createObject(MethodParameter parameter) {
        String value = parameter.getValue();
        String type = parameter.getType();
        return GSON.fromJson(value, Classes.forName(type));
    }

    void setApplicationContext(ApplicationContext applicationContext) { this.applicationContext = applicationContext; }

    public static String[] getAllowedPatterns() { return restrictions.getWhitelist(); }

    public static void setAllowedPatterns(String[] allowedPatterns) { restrictions.setWhitelist(allowedPatterns); }
}
