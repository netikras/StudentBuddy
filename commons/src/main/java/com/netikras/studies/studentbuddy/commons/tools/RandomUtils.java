package com.netikras.studies.studentbuddy.commons.tools;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by netikras on 16.11.2.
 */
public final class RandomUtils {

    public static final String LOWER = "qwertyuiopasdfghjklzxcvbnm";
    public static final String UPPER = "QWERTYUIOPASDFGHJKLZXCVBNM";
    public static final String DIGIT = "0123456789";
    public static final String SPECIAL = "!@#$%^&*()_-=+[]{}\\|;:'\"/><.,~`";
    public static final String SPECIALS_EXT = "ΩŁE®Ŧ¥↑ıØÞÆ§ÐªŊĦŁ<>©‘’Nº×÷@ł€¶ŧ←↓→øþßðđŋħł»¢“”nµ─·";

    public static final String ALPHA = LOWER + UPPER;
    public static final String ALNUM = ALPHA + DIGIT;
    public static final String ALNUMSPEC = ALNUM + SPECIAL;
    public static final String ALNUMSPECEXT = ALNUMSPEC + SPECIALS_EXT;


    private static Random rand = new Random();

    private RandomUtils() {}

    public static int nextInt() {
        return rand.nextInt();
    }

    public static int nextInt(int max) {
        return rand.nextInt(max);
    }

    public static int nextInt(int min, int max) {
        return rand.nextInt(max - min) + min;
    }


    public static String nextString(int length) {
        return nextString(ALNUM, length);
    }

    public static String nextString(String dictionary, int length) {
        String retVal = "";

        for (int i = 0; i < length; i++) {
            retVal += dictionary.charAt(rand.nextInt(dictionary.length()));
        }

        return retVal;
    }

    public static String nextString(String dictionary, int length_MIN, int length_MAX) {
        return nextString(dictionary, nextInt(length_MIN, length_MAX));
    }

    public static long nextLong(long min, long max) {
        long retVal = 0;
        long gap = max - min;
        long nextLong = rand.nextLong() & 0xffffffffL;

        if (gap == 0) retVal = max;
        else retVal = min + (nextLong % gap);


        return retVal;
    }

    public static Calendar nextDate(Calendar between_MIN, Calendar between_MAX) {
        Calendar retVal = Calendar.getInstance();

        retVal.setTimeInMillis(
                nextLong(
                        between_MIN.getTimeInMillis(),
                        between_MAX.getTimeInMillis()
                )
        );

        return retVal;
    }


    public static byte[] nextBytes(int length) {
        byte[] result;

        result = new byte[length];

        rand.nextBytes(result);

        return result;
    }


}
