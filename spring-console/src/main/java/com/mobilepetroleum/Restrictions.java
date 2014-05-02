package com.mobilepetroleum;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.regex.Pattern;

class Restrictions {

    private String[] whitelist = new String[]{};
    private Pattern[] patterns = new Pattern[]{Pattern.compile(".*")};

    public boolean allowed(Object bean, Method method) {
        String path = buildFullPath(bean, method);
        for (Pattern pattern : patterns) {
            if (allowed(pattern, path)) {
                return true;
            }
        }
        return false;
    }

    private boolean allowed(Pattern pattern, String path) {
        return pattern.matcher(path).matches();
    }

    private String buildFullPath(Object bean, Method method) {
        int modifiers = method.getModifiers();

        String modifier = "package-local";
        if (Modifier.isPrivate(modifiers)) {
            modifier = "private";
        } else if (Modifier.isProtected(modifiers)) {
            modifier = "protected";
        } else if (Modifier.isPublic(modifiers)) {
            modifier = "public";
        }

        Class<?> aClass = bean.getClass();
        String packageName = aClass.getPackage().getName();
        String className = aClass.getSimpleName();
        String methodName = method.getName();

        return String.format("%s:%s:%s:%s", modifier, packageName, className, methodName);
    }

    public String[] getWhitelist() { return whitelist; }

    public void setWhitelist(String[] whitelist) {
        this.whitelist = whitelist;

        patterns = new Pattern[whitelist.length];
        for (int i = 0; i < whitelist.length; i++) {
            patterns[i] = Pattern.compile(whitelist[i]);
        }

    }
}
