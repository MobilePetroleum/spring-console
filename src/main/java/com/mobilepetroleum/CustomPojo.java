package com.mobilepetroleum;

@SuppressWarnings("UnusedDeclaration")
class CustomPojo {
    private String string;
    private int anInt;

    public CustomPojo() { }

    public CustomPojo(String string, int anInt) {
        this.string = string;
        this.anInt = anInt;
    }

    public String getString() { return string; }

    public void setString(String string) { this.string = string; }

    public int getAnInt() { return anInt; }

    public void setAnInt(int anInt) { this.anInt = anInt; }

    @Override
    public String toString() { return "CustomPojo{" + "string='" + string + '\'' + ", anInt=" + anInt + '}'; }
}