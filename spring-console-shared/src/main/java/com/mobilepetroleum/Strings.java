package com.mobilepetroleum;

public class Strings {

    private Strings() {}

    public static boolean isNullOrEmpty(@Nullable String string) { return string == null || string.length() == 0; }

}
