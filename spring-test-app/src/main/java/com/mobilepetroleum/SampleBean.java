package com.mobilepetroleum;


public class SampleBean {

    public void callLong(long l) { System.out.println("callLong: " + l); }

    public void callBigLong(Long l) { System.out.println("callBigLong: " + l); }

    public void callShort(short l) { System.out.println("callShort: " + l); }

    public void callBigShort(Short s) { System.out.println("callBigShort: " + s); }

    public void callByte(byte b) { System.out.println("callByte: " + b); }

    public void callBigByte(Byte b) { System.out.println("callBigByte: " + b); }

    public void callFloat(float f) { System.out.println("callFloat: " + f); }

    public void callBigFloat(Float f) { System.out.println("callBigFloat: " + f); }

    public void callDouble(double d) { System.out.println("callDouble: " + d); }

    public void callBigDouble(Double d) { System.out.println("callBigDouble: " + d); }

    public void callBoolean(boolean b) { System.out.println("callBoolean: " + b); }

    public void callBigBoolean(Boolean b) { System.out.println("callBigBoolean: " + b); }

    public void callChar(char c) { System.out.println("callChar: " + c); }

    public void callBigChar(Character c) { System.out.println("callBigChar: " + c); }

    public void callCustomObject(CustomPojo customPojo) { System.out.println("CustomPojo: " + customPojo); }

    public void callCustomObject(int a, long b, CustomPojo customPojo) {
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("CustomPojo: " + customPojo);
    }
}
