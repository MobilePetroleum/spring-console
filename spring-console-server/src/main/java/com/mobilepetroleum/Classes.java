package com.mobilepetroleum;

import org.springframework.util.ClassUtils;

public class Classes {

    public static Class<?> forName(String name) {
        try {
            return ClassUtils.forName(name);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    static Object invoke(Object o, String methodName, Class<?>[] types, Object[] params) {
        try {
            return o.getClass().getDeclaredMethod(methodName, types).invoke(o, params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
