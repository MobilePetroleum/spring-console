package com.mobilepetroleum;

public class Exceptions {

    private Exceptions() {}

    public static RuntimeException runtime(Throwable cause, String message, Object... args) {
        return new RuntimeException(String.format(message, args), cause);
    }

    public static RuntimeException runtime(String message, Object... args) {
        return new RuntimeException(String.format(message, args));
    }

}
