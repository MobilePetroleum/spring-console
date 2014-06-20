package com.mobilepetroleum;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

class SpringServiceBean implements SpringService {

    private static final Gson GSON = new Gson();
    private static final ResultToStringConverter resultToStringConverter = new ResultToStringConverter();

    private final Restrictions restrictions = new Restrictions();

    private ApplicationContext applicationContext;

    // @Override
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

            if (parameters.length > 0 && parameters[0].getValue() != null && Strings.isNullOrEmpty(parameters[0].getType())) {
                Method method = findMethod(beanName, type, methodName, parameters);
                types = method.getParameterTypes();
                for (int i = 0; i < parameters.length; i++) {
                    MethodParameter parameter = parameters[i];
                    params[i] = createObject(parameter.getValue(), types[i]);
                }
            } else {
                for (int i = 0; i < parameters.length; i++) {
                    MethodParameter parameter = parameters[i];
                    types[i] = Classes.forName(parameter.getType());
                    params[i] = createObject(parameter.getValue(), parameter.getType());
                }
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

    private Method findMethod(String beanName, Class type, String methodName, MethodParameter[] parameters) {
        Method[] declaredMethods = type.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (methodName.equals(declaredMethod.getName())) {
                if (declaredMethod.getParameterTypes().length == parameters.length) {
                    return declaredMethod;
                }
            }
        }

        if (Object.class.equals(type)) {
            throw Exceptions.runtime("Could not find method %s::%s", beanName, methodName);
        }
        return findMethod(beanName, type.getSuperclass(), methodName, parameters);
    }

    private RuntimeException beanOrMethodNotFoundOrAllowed(String beanName) {return new RuntimeException(String.format("No bean named '%s' or method is accessible or found", beanName));}

    private Object createObject(String value, String type) {
        return GSON.fromJson(value, Classes.forName(type));
    }

    private Object createObject(String value, Class<?> clazz) {
        return GSON.fromJson(value, clazz);
    }

    void setApplicationContext(ApplicationContext applicationContext) { this.applicationContext = applicationContext; }

    public String[] getAllowedPatterns() { return restrictions.getWhitelist(); }

    public void setAllowedPatterns(String[] allowedPatterns) { restrictions.setWhitelist(allowedPatterns); }
}
