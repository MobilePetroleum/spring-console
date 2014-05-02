package com.mobilepetroleum;

import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Classes {

    static Class<?> forName(String name) {
        try {
            return ClassUtils.forName(name, Classes.class.getClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    static Optional<Method> findMethod(Object object, String methodName, Class<?>[] types) {
        return findMethod(object, object.getClass(), methodName, types);
    }

    static Optional<Method> findMethod(Object object, String methodName) {
        return findMethod(object, object.getClass(), methodName, new Class[]{});
    }

    static Object invoke(Object object, Method method, Object[] params) {
        method.setAccessible(true);
        try {
            return method.invoke(object, params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Optional<Method> findMethod(Object object, Class<?> clazz, String methodName, Class<?>[] types) {
        try {
            return Optional.of(clazz.getDeclaredMethod(methodName, types));
        } catch (NoSuchMethodException e) {
            if (Object.class.equals(clazz)) {
                return Optional.<Method>empty();
            } else {
                return findMethod(object, clazz.getSuperclass(), methodName, types);
            }
        }
    }

    static Object invoke(Object object, String methodName, Class<?>[] types, Object[] params) {
        return invoke(object, object.getClass(), methodName, types, params);
    }

    private static Object invoke(Object object, Class<?> clazz, String methodName, Class<?>[] types, Object[] params) {
        try {

            Method declaredMethod;

            try {
                declaredMethod = clazz.getDeclaredMethod(methodName, types);
            } catch (NoSuchMethodException e) {
                if (!clazz.equals(Object.class)) {
                    return invoke(object, clazz.getSuperclass(), methodName, types, params);
                } else {
                    throw new RuntimeException(String.format("Cannot find %s:%s", object.getClass().getName(), methodName));
                }
            }

            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(object, params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
