package com.mobilepetroleum;

public class CustomPojo {
    private String string;
    private int anInt;

    public String getString() { return string; }

    public void setString(String string) { this.string = string; }

    public int getAnInt() { return anInt; }

    public void setAnInt(int anInt) { this.anInt = anInt; }

    @Override
    public String toString() { return "CustomPojo{" + "string='" + string + '\'' + ", anInt=" + anInt + '}'; }
}