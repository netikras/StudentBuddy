package com.netikras.studies.studentbuddy.commons.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by netikras on 16.12.27.
 */
public class GlobalFormatters {

    public static final String DATETIMEMILLIS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final SimpleDateFormat simpleDateTimeMillisFormatter = new SimpleDateFormat(DATETIMEMILLIS_FORMAT);
    public static final SimpleDateFormat simpleDateTimeFormatter = new SimpleDateFormat(DATETIME_FORMAT);
    public static final SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DATE_FORMAT);

    public static final Date DATE_ZERO = new Date(0);
    public static final Date DATE_MAX = new Date(Integer.MAX_VALUE);

    public static final String DATE_ZERO_STRING = dateToString(DATE_ZERO);
    public static final String DATE_MAX_STRING = dateToString(DATE_MAX);


    /**
     * Formats a java.util.Date object to String using the following format:<br/>
     * {@value #DATETIMEMILLIS_FORMAT}<br/>
     */
    public static final String dateToString(Date date) {
        return dateToString(date, simpleDateTimeMillisFormatter);
    }

    /**
     * Formats a java.util.Date object to String using custom format
     */
    public static final String dateToString(Date date, String pattern) {
        return dateToString(date, new SimpleDateFormat(pattern));
    }

    /**
     * Formats a java.util.Date object to String using custom formatter
     */
    public static final String dateToString(Date date, DateFormat formatter) {
        return formatter.format(date);
    }

    /**
     * Attempts to parse string to date. Will attempt 3 times using the following formats:<br/>
     *
     * {@value #DATETIMEMILLIS_FORMAT}<br/>
     * {@value #DATETIME_FORMAT}<br/>
     * {@value #DATE_FORMAT}<br/>
     * @throws ParseException
     */
    public static final Date stringToDate(String formattedDateString) throws ParseException {
        Date date;
        try {
            date = simpleDateTimeMillisFormatter.parse(formattedDateString);
        } catch (ParseException e) {
            try {
                date = simpleDateTimeFormatter.parse(formattedDateString);
            } catch (ParseException e2) {
                date = simpleDateFormatter.parse(formattedDateString);
            }
        }

        return date;
    }

}
