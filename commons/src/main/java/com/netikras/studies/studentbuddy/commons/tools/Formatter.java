package com.netikras.studies.studentbuddy.commons.tools;

import java.util.Date;

public class Formatter {

    public static Date toDate(long timestamp) {
        long currentEra = 1000 * 1000 * 1000; // 1 000 000 000

        if (timestamp < 0) return null;

        while (timestamp < currentEra) {
            timestamp *= 10;
        }

        return new Date(timestamp);

    }

}
