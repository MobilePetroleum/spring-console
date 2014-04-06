package com.mobilepetroleum;

import java.util.Arrays;

public class SampleArrayBean {
    public void callLong(long[] l) { System.out.println("callLong[]: " + Arrays.toString(l)); }

    public void callBigLong(Long[] l) { System.out.println("callBigLong[]: " + Arrays.toString(l)); }

    public void callShort(short[] l) { System.out.println("callShort[]: " + Arrays.toString(l)); }

    public void callBigShort(Short[] s) { System.out.println("callBigShort[]: " + Arrays.toString(s)); }

    public void callByte(byte[] b) { System.out.println("callByte[]: " + Arrays.toString(b)); }

    public void callBigByte(Byte[] b) { System.out.println("callBigByte[]: " + Arrays.toString(b)); }

    public void callFloat(float[] f) { System.out.println("callFloat[]: " + Arrays.toString(f)); }

    public void callBigFloat(Float[] f) { System.out.println("callBigFloat[]: " + Arrays.toString(f)); }

    public void callDouble(double[] d) { System.out.println("callDouble[]: " + Arrays.toString(d)); }

    public void callBigDouble(Double[] d) { System.out.println("callBigDouble[]: " + Arrays.toString(d)); }

    public void callBoolean(boolean[] b) { System.out.println("callBoolean[]: " + Arrays.toString(b)); }

    public void callBigBoolean(Boolean[] b) { System.out.println("callBigBoolean[]: " + Arrays.toString(b)); }

    public void callChar(char[] c) { System.out.println("callChar[]: " + Arrays.toString(c)); }

    public void callBigChar(Character[] c) { System.out.println("callBigChar[]: " + Arrays.toString(c)); }
}