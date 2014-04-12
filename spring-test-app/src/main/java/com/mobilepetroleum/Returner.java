package com.mobilepetroleum;

@SuppressWarnings("UnusedDeclaration")
public class Returner {
    long returnLong() { return 999L; }

    long returnBigLong() { return 999L; }

    short returnShort() { return (short) 999; }

    Short returnBigShort() { return (short) 999; }

    byte returnByte() { return (byte) 128; }

    Byte returnBugByte() { return (byte) 128; }

    float returnFloat() { return 999.9f; }

    Float returnBigFloat() { return 999.9f; }

    double returnDouble() { return 999.9d; }

    Double returnBigDouble() { return 999.9d; }

    char returnChar() { return 'a'; }

    Character returnBigChar() { return 'a'; }

    CustomPojo returnCustomObject() { return new CustomPojo("s999s", 999); }
}
