package com.mobilepetroleum;

import org.springframework.util.ClassUtils;

public class Classes {

    public static Class<?> forName(String name) {
        try {
            return ClassUtils.forName(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
