package com.mobilepetroleum;


public enum PrimitiveClass {

    INT(int.class),
    BIG_INT(Integer.class),
    LONG(long.class),
    BIG_LONG(Long.class),
    SHORT(short.class),
    BIG_SHORT(Short.class),
    BYTE(byte.class),
    BIG_BYTE(Byte.class),
    FLOAT(float.class),
    BIG_FLOAT(Float.class),
    DOUBLE(double.class),
    BIG_DOUBLE(Double.class),
    BOOLEAN(boolean.class),
    BIG_BOOLEAN(Boolean.class),
    CHAR(char.class),
    BIG_CHAR(Character.class),
    STRING(String.class),
    NON(void.class);

    private String className;
    private Class<?> clazz;

    private PrimitiveClass(Class<?> clazz) {
        this.clazz = clazz;
        this.className = clazz.getName();
    }

    public static Class<?> gmgetClass(String className) {

        for (PrimitiveClass primitiveClass : values()) {
            if (primitiveClass.className.equals(className)) {
                return primitiveClass.clazz;
            }
        }

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
