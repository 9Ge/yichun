package com.enercomn.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class S7PLCUtils {

    /** Constant <code>BINARY=1</code> */
    public static final int BINARY = 1;

    /** Constant <code>TWO_BYTE_INT_UNSIGNED=2</code> */
    public static final int TWO_BYTE_INT_UNSIGNED = 2;
    /** Constant <code>TWO_BYTE_INT_SIGNED=3</code> */
    public static final int TWO_BYTE_INT_SIGNED = 3;
    /** Constant <code>TWO_BYTE_INT_UNSIGNED_SWAPPED=22</code> */
    public static final int TWO_BYTE_INT_UNSIGNED_SWAPPED = 22;
    /** Constant <code>TWO_BYTE_INT_SIGNED_SWAPPED=23</code> */
    public static final int TWO_BYTE_INT_SIGNED_SWAPPED = 23;

    /** Constant <code>FOUR_BYTE_INT_UNSIGNED=4</code> */
    public static final int FOUR_BYTE_INT_UNSIGNED = 4;
    /** Constant <code>FOUR_BYTE_INT_SIGNED=5</code> */
    public static final int FOUR_BYTE_INT_SIGNED = 5;
    /** Constant <code>FOUR_BYTE_INT_UNSIGNED_SWAPPED=6</code> */
    public static final int FOUR_BYTE_INT_UNSIGNED_SWAPPED = 6;
    /** Constant <code>FOUR_BYTE_INT_SIGNED_SWAPPED=7</code> */
    public static final int FOUR_BYTE_INT_SIGNED_SWAPPED = 7;
    /* 0xAABBCCDD is transmitted as 0xDDCCBBAA */
    /** Constant <code>FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED=24</code> */
    public static final int FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED = 24;
    /** Constant <code>FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED=25</code> */
    public static final int FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED = 25;

    /** Constant <code>FOUR_BYTE_FLOAT=8</code> */
    public static final int FOUR_BYTE_FLOAT = 8;
    /** Constant <code>FOUR_BYTE_FLOAT_SWAPPED=9</code> */
    public static final int FOUR_BYTE_FLOAT_SWAPPED = 9;
    /** Constant <code>FOUR_BYTE_FLOAT_SWAPPED_INVERTED=21</code> */
    public static final int FOUR_BYTE_FLOAT_SWAPPED_INVERTED = 21;

    /** Constant <code>EIGHT_BYTE_INT_UNSIGNED=10</code> */
    public static final int EIGHT_BYTE_INT_UNSIGNED = 10;
    /** Constant <code>EIGHT_BYTE_INT_SIGNED=11</code> */
    public static final int EIGHT_BYTE_INT_SIGNED = 11;
    /** Constant <code>EIGHT_BYTE_INT_UNSIGNED_SWAPPED=12</code> */
    public static final int EIGHT_BYTE_INT_UNSIGNED_SWAPPED = 12;
    /** Constant <code>EIGHT_BYTE_INT_SIGNED_SWAPPED=13</code> */
    public static final int EIGHT_BYTE_INT_SIGNED_SWAPPED = 13;
    /** Constant <code>EIGHT_BYTE_FLOAT=14</code> */
    public static final int EIGHT_BYTE_FLOAT = 14;
    /** Constant <code>EIGHT_BYTE_FLOAT_SWAPPED=15</code> */
    public static final int EIGHT_BYTE_FLOAT_SWAPPED = 15;

    /** Constant <code>TWO_BYTE_BCD=16</code> */
    public static final int TWO_BYTE_BCD = 16;
    /** Constant <code>FOUR_BYTE_BCD=17</code> */
    public static final int FOUR_BYTE_BCD = 17;
    /** Constant <code>FOUR_BYTE_BCD_SWAPPED=20</code> */
    public static final int FOUR_BYTE_BCD_SWAPPED = 20;

    /** Constant <code>CHAR=18</code> */
    public static final int CHAR = 18;
    /** Constant <code>VARCHAR=19</code> */
    public static final int VARCHAR = 19;

    //MOD10K two, three and four register types
    /** Constant <code>FOUR_BYTE_MOD_10K=26</code> */
    public static final int FOUR_BYTE_MOD_10K = 26;
    /** Constant <code>SIX_BYTE_MOD_10K=27</code> */
    public static final int SIX_BYTE_MOD_10K = 27;
    /** Constant <code>EIGHT_BYTE_MOD_10K=28</code> */
    public static final int EIGHT_BYTE_MOD_10K = 28;
    /** Constant <code>FOUR_BYTE_MOD_10K_SWAPPED=29</code> */
    public static final int FOUR_BYTE_MOD_10K_SWAPPED = 29;
    /** Constant <code>SIX_BYTE_MOD_10K_SWAPPED=30</code> */
    public static final int SIX_BYTE_MOD_10K_SWAPPED = 30;
    /** Constant <code>EIGHT_BYTE_MOD_10K_SWAPPED=31</code> */
    public static final int EIGHT_BYTE_MOD_10K_SWAPPED = 31;

    //One byte unsigned integer types
    /** Constant <code>ONE_BYTE_INT_UNSIGNED_LOWER=32</code> */
    public static final int ONE_BYTE_INT_UNSIGNED_LOWER = 32;
    /** Constant <code>ONE_BYTE_INT_UNSIGNED_UPPER=33</code> */
    public static final int ONE_BYTE_INT_UNSIGNED_UPPER = 33;

    private static RoundingMode roundingMode = RoundingMode.HALF_UP;


    public static Boolean bytesToValuBooleanOffset(byte[] data, int offset) {
        return new Boolean((((data[offset / 8] & 0xff) >> (offset % 8)) & 0x1) == 1);
    }

    public static Number bytesToValueRealOffset(int dataType,byte[] data, int offset) {
        offset *= 2;

        // 2 bytes
        if (dataType == TWO_BYTE_INT_UNSIGNED)
            return new Integer(((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff));

        if (dataType == TWO_BYTE_INT_SIGNED)
            return new Short((short) (((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff)));

        if (dataType == TWO_BYTE_INT_UNSIGNED_SWAPPED)
            return new Integer(((data[offset + 1] & 0xff) << 8) | (data[offset] & 0xff));

        if (dataType == TWO_BYTE_INT_SIGNED_SWAPPED)
            return new Short((short) (((data[offset + 1] & 0xff) << 8) | (data[offset] & 0xff)));

        if (dataType == TWO_BYTE_BCD) {
            StringBuilder sb = new StringBuilder();
            appendBCD(sb, data[offset]);
            appendBCD(sb, data[offset + 1]);
            return Short.parseShort(sb.toString());
        }

        // 1 byte
        if (dataType == ONE_BYTE_INT_UNSIGNED_LOWER)
            return new Integer(data[offset+1] & 0xff);
        if (dataType == ONE_BYTE_INT_UNSIGNED_UPPER)
            return new Integer(data[offset] & 0xff);

        // 4 bytes
        if (dataType == FOUR_BYTE_INT_UNSIGNED)
            return new Long(((long) ((data[offset] & 0xff)) << 24) | ((long) ((data[offset + 1] & 0xff)) << 16)
                    | ((long) ((data[offset + 2] & 0xff)) << 8) | ((data[offset + 3] & 0xff)));

        if (dataType == FOUR_BYTE_INT_SIGNED)
            return new Integer(((data[offset] & 0xff) << 24) | ((data[offset + 1] & 0xff) << 16)
                    | ((data[offset + 2] & 0xff) << 8) | (data[offset + 3] & 0xff));

        if (dataType == FOUR_BYTE_INT_UNSIGNED_SWAPPED)
            return new Long(((long) ((data[offset + 2] & 0xff)) << 24) | ((long) ((data[offset + 3] & 0xff)) << 16)
                    | ((long) ((data[offset] & 0xff)) << 8) | ((data[offset + 1] & 0xff)));

        if (dataType == FOUR_BYTE_INT_SIGNED_SWAPPED)
            return new Integer(((data[offset + 2] & 0xff) << 24) | ((data[offset + 3] & 0xff) << 16)
                    | ((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff));

        if (dataType == FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED)
            return new Long(((long) ((data[offset + 3] & 0xff)) << 24) | (((data[offset + 2] & 0xff) << 16))
                    | ((long) ((data[offset + 1] & 0xff)) << 8) | (data[offset] & 0xff));

        if (dataType == FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED)
            return new Integer(((data[offset + 3] & 0xff) << 24) | ((data[offset + 2] & 0xff) << 16)
                    | ((data[offset + 1] & 0xff) << 8) | ((data[offset] & 0xff)));

        if (dataType == FOUR_BYTE_FLOAT)
            return Float.intBitsToFloat(((data[offset] & 0xff) << 24) | ((data[offset + 1] & 0xff) << 16)
                    | ((data[offset + 2] & 0xff) << 8) | (data[offset + 3] & 0xff));

        if (dataType == FOUR_BYTE_FLOAT_SWAPPED)
            return Float.intBitsToFloat(((data[offset + 2] & 0xff) << 24) | ((data[offset + 3] & 0xff) << 16)
                    | ((data[offset] & 0xff) << 8) | (data[offset + 1] & 0xff));

        if (dataType == FOUR_BYTE_BCD) {
            StringBuilder sb = new StringBuilder();
            appendBCD(sb, data[offset]);
            appendBCD(sb, data[offset + 1]);
            appendBCD(sb, data[offset + 2]);
            appendBCD(sb, data[offset + 3]);
            return Integer.parseInt(sb.toString());
        }

        if (dataType == FOUR_BYTE_BCD_SWAPPED) {
            StringBuilder sb = new StringBuilder();
            appendBCD(sb, data[offset + 2]);
            appendBCD(sb, data[offset + 3]);
            appendBCD(sb, data[offset]);
            appendBCD(sb, data[offset + 1]);
            return Integer.parseInt(sb.toString());
        }

        //MOD10K types
        if (dataType == FOUR_BYTE_MOD_10K_SWAPPED)
            return   BigInteger.valueOf((((data[offset + 2] & 0xff) << 8) + (data[offset + 3] & 0xff))).multiply(BigInteger.valueOf(10000L))
                    .add(BigInteger.valueOf((((data[offset    ] & 0xff) << 8) + (data[offset + 1] & 0xff))));
        if (dataType == FOUR_BYTE_MOD_10K)
            return   BigInteger.valueOf((((data[offset    ] & 0xff) << 8) + (data[offset + 1] & 0xff))).multiply(BigInteger.valueOf(10000L))
                    .add(BigInteger.valueOf((((data[offset + 2] & 0xff) << 8) + (data[offset + 3] & 0xff))));
        if (dataType == SIX_BYTE_MOD_10K_SWAPPED)
            return   BigInteger.valueOf((((data[offset + 4] & 0xff) << 8) + (data[offset + 5] & 0xff))).multiply(BigInteger.valueOf(100000000L))
                    .add(BigInteger.valueOf((((data[offset + 2] & 0xff) << 8) + (data[offset + 3] & 0xff))).multiply(BigInteger.valueOf(10000L)))
                    .add(BigInteger.valueOf((((data[offset    ] & 0xff) << 8) + (data[offset + 1] & 0xff))));
        if (dataType == SIX_BYTE_MOD_10K)
            return   BigInteger.valueOf((((data[offset    ] & 0xff) << 8) + (data[offset + 1] & 0xff))).multiply(BigInteger.valueOf(100000000L))
                    .add(BigInteger.valueOf((((data[offset + 2] & 0xff) << 8) + (data[offset + 3] & 0xff))).multiply(BigInteger.valueOf(10000L)))
                    .add(BigInteger.valueOf((((data[offset + 4] & 0xff) << 8) + (data[offset + 5] & 0xff))));
        if (dataType == EIGHT_BYTE_MOD_10K_SWAPPED)
            return   BigInteger.valueOf((((data[offset + 6] & 0xff) << 8) + (data[offset + 7] & 0xff))).multiply(BigInteger.valueOf(1000000000000L))
                    .add(BigInteger.valueOf((((data[offset + 4] & 0xff) << 8) + (data[offset + 5] & 0xff))).multiply(BigInteger.valueOf(100000000L)))
                    .add(BigInteger.valueOf((((data[offset + 2] & 0xff) << 8) + (data[offset + 3] & 0xff))).multiply(BigInteger.valueOf(10000L)))
                    .add(BigInteger.valueOf((((data[offset    ] & 0xff) << 8) + (data[offset + 1] & 0xff))));
        if (dataType == EIGHT_BYTE_MOD_10K)
            return   BigInteger.valueOf((((data[offset    ] & 0xff) << 8) + (data[offset + 1] & 0xff))).multiply(BigInteger.valueOf(1000000000000L))
                    .add(BigInteger.valueOf((((data[offset + 2] & 0xff) << 8) + (data[offset + 3] & 0xff))).multiply(BigInteger.valueOf(100000000L)))
                    .add(BigInteger.valueOf((((data[offset + 4] & 0xff) << 8) + (data[offset + 5] & 0xff))).multiply(BigInteger.valueOf(10000L)))
                    .add(BigInteger.valueOf((((data[offset + 6] & 0xff) << 8) + (data[offset + 7] & 0xff))));

        // 8 bytes
        if (dataType == EIGHT_BYTE_INT_UNSIGNED) {
            byte[] b9 = new byte[9];
            System.arraycopy(data, offset, b9, 1, 8);
            return new BigInteger(b9);
        }

        if (dataType == EIGHT_BYTE_INT_SIGNED)
            return new Long(((long) ((data[offset] & 0xff)) << 56) | ((long) ((data[offset + 1] & 0xff)) << 48)
                    | ((long) ((data[offset + 2] & 0xff)) << 40) | ((long) ((data[offset + 3] & 0xff)) << 32)
                    | ((long) ((data[offset + 4] & 0xff)) << 24) | ((long) ((data[offset + 5] & 0xff)) << 16)
                    | ((long) ((data[offset + 6] & 0xff)) << 8) | ((data[offset + 7] & 0xff)));

        if (dataType == EIGHT_BYTE_INT_UNSIGNED_SWAPPED) {
            byte[] b9 = new byte[9];
            b9[1] = data[offset + 6];
            b9[2] = data[offset + 7];
            b9[3] = data[offset + 4];
            b9[4] = data[offset + 5];
            b9[5] = data[offset + 2];
            b9[6] = data[offset + 3];
            b9[7] = data[offset];
            b9[8] = data[offset + 1];
            return new BigInteger(b9);
        }

        if (dataType == EIGHT_BYTE_INT_SIGNED_SWAPPED)
            return new Long(((long) ((data[offset + 6] & 0xff)) << 56) | ((long) ((data[offset + 7] & 0xff)) << 48)
                    | ((long) ((data[offset + 4] & 0xff)) << 40) | ((long) ((data[offset + 5] & 0xff)) << 32)
                    | ((long) ((data[offset + 2] & 0xff)) << 24) | ((long) ((data[offset + 3] & 0xff)) << 16)
                    | ((long) ((data[offset] & 0xff)) << 8) | ((data[offset + 1] & 0xff)));

        if (dataType == EIGHT_BYTE_FLOAT)
            return Double.longBitsToDouble(((long) ((data[offset] & 0xff)) << 56)
                    | ((long) ((data[offset + 1] & 0xff)) << 48) | ((long) ((data[offset + 2] & 0xff)) << 40)
                    | ((long) ((data[offset + 3] & 0xff)) << 32) | ((long) ((data[offset + 4] & 0xff)) << 24)
                    | ((long) ((data[offset + 5] & 0xff)) << 16) | ((long) ((data[offset + 6] & 0xff)) << 8)
                    | ((data[offset + 7] & 0xff)));

        if (dataType == EIGHT_BYTE_FLOAT_SWAPPED)
            return Double.longBitsToDouble(((long) ((data[offset + 6] & 0xff)) << 56)
                    | ((long) ((data[offset + 7] & 0xff)) << 48) | ((long) ((data[offset + 4] & 0xff)) << 40)
                    | ((long) ((data[offset + 5] & 0xff)) << 32) | ((long) ((data[offset + 2] & 0xff)) << 24)
                    | ((long) ((data[offset + 3] & 0xff)) << 16) | ((long) ((data[offset] & 0xff)) << 8)
                    | ((data[offset + 1] & 0xff)));

        throw new RuntimeException("Unsupported data type: " + dataType);
    }


    private static void appendBCD(StringBuilder sb, byte b) {
        sb.append(bcdNibbleToInt(b, true));
        sb.append(bcdNibbleToInt(b, false));
    }

    private static int bcdNibbleToInt(byte b, boolean high) {
        int n;
        if (high)
            n = (b >> 4) & 0xf;
        else
            n = b & 0xf;
        if (n > 9)
            n = 0;
        return n;
    }

    public static short[] valueToShorts(int dataType,Number value) {
        // 2 bytes
        if (dataType == TWO_BYTE_INT_UNSIGNED || dataType == TWO_BYTE_INT_SIGNED)
            return new short[] { toShort(value) };

        if (dataType == TWO_BYTE_INT_SIGNED_SWAPPED || dataType == TWO_BYTE_INT_UNSIGNED_SWAPPED) {
            short sval = toShort(value);
            //0x1100
            return new short[] { (short) (((sval & 0xFF00) >> 8) | ((sval & 0x00FF) << 8)) };
        }

        if (dataType == TWO_BYTE_BCD) {
            short s = toShort(value);
            return new short[] { (short) ((((s / 1000) % 10) << 12) | (((s / 100) % 10) << 8) | (((s / 10) % 10) << 4) | (s % 10)) };
        }

        if (dataType == ONE_BYTE_INT_UNSIGNED_LOWER) {
            return new short[] { (short)(toShort(value) & 0x00FF) };
        }
        if (dataType == ONE_BYTE_INT_UNSIGNED_UPPER) {
            return new short[] { (short)((toShort(value) << 8) & 0xFF00) };
        }

        // 4 bytes
        if (dataType == FOUR_BYTE_INT_UNSIGNED || dataType == FOUR_BYTE_INT_SIGNED) {
            int i = toInt(value);
            return new short[] { (short) (i >> 16), (short) i };
        }

        if (dataType == FOUR_BYTE_INT_UNSIGNED_SWAPPED || dataType == FOUR_BYTE_INT_SIGNED_SWAPPED) {
            int i = toInt(value);
            return new short[] { (short) i, (short) (i >> 16) };
        }

        if (dataType == FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED
                || dataType == FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED) {
            int i = toInt(value);
            short topWord = (short) (((i & 0xFF) << 8) | ((i >> 8) & 0xFF));
            short bottomWord = (short) (((i >> 24) & 0x000000FF) | ((i >> 8) & 0x0000FF00));
            return new short[] { topWord, bottomWord };
        }

        if (dataType == FOUR_BYTE_FLOAT) {
            int i = Float.floatToIntBits(value.floatValue());
            return new short[] { (short) (i >> 16), (short) i };
        }

        if (dataType == FOUR_BYTE_FLOAT_SWAPPED) {
            int i = Float.floatToIntBits(value.floatValue());
            return new short[] { (short) i, (short) (i >> 16) };
        }

        if (dataType == FOUR_BYTE_BCD) {
            int i = toInt(value);
            return new short[] {
                    (short) ((((i / 10000000) % 10) << 12) | (((i / 1000000) % 10) << 8) | (((i / 100000) % 10) << 4) | ((i / 10000) % 10)),
                    (short) ((((i / 1000) % 10) << 12) | (((i / 100) % 10) << 8) | (((i / 10) % 10) << 4) | (i % 10)) };
        }

        // MOD10K
        if (dataType == FOUR_BYTE_MOD_10K) {
            long l = value.longValue();
            return new short[] { (short) ((l/10000)%10000), (short) (l%10000) };
        }
        if (dataType == FOUR_BYTE_MOD_10K_SWAPPED) {
            long l = value.longValue();
            return new short[] { (short) (l%10000), (short) ((l/10000)%10000)};
        }
        if (dataType == SIX_BYTE_MOD_10K) {
            long l = value.longValue();
            return new short[] { (short) ((l/100000000L)%10000), (short) ((l/10000)%10000), (short) (l%10000) };
        }
        if (dataType == SIX_BYTE_MOD_10K_SWAPPED) {
            long l = value.longValue();
            return new short[] { (short) (l%10000), (short) ((l/10000)%10000), (short)((l/100000000L)%10000)};
        }
        if (dataType == EIGHT_BYTE_MOD_10K) {
            long l = value.longValue();
            return new short[] { (short)((l/1000000000000L)%10000), (short) ((l/100000000L)%10000), (short) ((l/10000)%10000), (short) (l%10000) };
        }
        if (dataType == EIGHT_BYTE_MOD_10K_SWAPPED) {
            long l = value.longValue();
            return new short[] { (short) (l%10000), (short) ((l/10000)%10000), (short)((l/100000000L)%10000), (short)((l/1000000000000L)%10000)};
        }

        // 8 bytes
        if (dataType == EIGHT_BYTE_INT_UNSIGNED || dataType == EIGHT_BYTE_INT_SIGNED) {
            long l = value.longValue();
            return new short[] { (short) (l >> 48), (short) (l >> 32), (short) (l >> 16), (short) l };
        }

        if (dataType == EIGHT_BYTE_INT_UNSIGNED_SWAPPED || dataType == EIGHT_BYTE_INT_SIGNED_SWAPPED) {
            long l = value.longValue();
            return new short[] { (short) l, (short) (l >> 16), (short) (l >> 32), (short) (l >> 48) };
        }

        if (dataType == EIGHT_BYTE_FLOAT) {
            long l = Double.doubleToLongBits(value.doubleValue());
            return new short[] { (short) (l >> 48), (short) (l >> 32), (short) (l >> 16), (short) l };
        }

        if (dataType == EIGHT_BYTE_FLOAT_SWAPPED) {
            long l = Double.doubleToLongBits(value.doubleValue());
            return new short[] { (short) l, (short) (l >> 16), (short) (l >> 32), (short) (l >> 48) };
        }

        throw new RuntimeException("Unsupported data type: " + dataType);
    }

    private static short toShort(Number value) {
        return (short) toInt(value);
    }

    public static int toInt(Number value) {
        if (value instanceof Double)
            return new BigDecimal(value.doubleValue()).setScale(0, roundingMode).intValue();
        if (value instanceof Float)
            return new BigDecimal(value.floatValue()).setScale(0, roundingMode).intValue();
        if (value instanceof BigDecimal)
            return ((BigDecimal) value).setScale(0, roundingMode).intValue();
        return value.intValue();
    }
    public static byte[] convertToBytes(short[] sdata) {
        int byteCount = sdata.length * 2;
        byte[] data = new byte[byteCount];

        for(int i = 0; i < sdata.length; ++i) {
            data[i * 2] = (byte)(255 & sdata[i] >> 8);
            data[i * 2 + 1] = (byte)(255 & sdata[i]);
        }

        return data;
    }


    protected byte[] convertToBytes(boolean[] bdata) {
        int byteCount = (bdata.length + 7) / 8;
        byte[] data = new byte[byteCount];

        for(int i = 0; i < bdata.length; ++i) {
            data[i / 8] = (byte)(data[i / 8] | (bdata[i] ? 1 : 0) << i % 8);
        }

        return data;
    }


    public void setBit(int andMask,int orMask,int bit, boolean value) {
        if (bit < 0 || bit > 15)
            throw new RuntimeException("Bit must be between 0 and 15 inclusive");

        // Set the bit in the andMask to 0 to allow writing.
        andMask = andMask & ~(1 << bit);

        // Set the bit in the orMask to write the correct value.
        if (value)
            orMask = orMask | 1 << bit;
        else
            orMask = orMask & ~(1 << bit);
    }

    public static byte[] insert(Boolean value, byte[] buffer, int byteOffset, int bitOffset) {

        //thx to @mfriedemann (https://github.com/mfriedemann)
        if (value) {
            buffer[byteOffset] |= (0x01 << bitOffset);
        } else {
            buffer[byteOffset] &= ~(0x01 << bitOffset);
        }
        return buffer;
    }

}
