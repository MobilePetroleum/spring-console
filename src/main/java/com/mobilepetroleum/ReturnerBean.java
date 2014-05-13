package com.mobilepetroleum;

@SuppressWarnings("UnusedDeclaration")
class ReturnerBean {
    private long returnLong() { return 999L; }

    private long returnBigLong() { return 999L; }

    private short returnShort() { return (short) 999; }

    private Short returnBigShort() { return (short) 999; }

    private byte returnByte() { return (byte) 128; }

    Byte returnBigByte() { return (byte) 128; }

    private float returnFloat() { return 999.9f; }

    private Float returnBigFloat() { return 999.9f; }

    private double returnDouble() { return 999.9d; }

    private Double returnBigDouble() { return 999.9d; }

    private char returnChar() { return 'a'; }

    private Character returnBigChar() { return 'a'; }

    private CustomPojo returnCustomObject() { return new CustomPojo("s999s", 999); }
}
