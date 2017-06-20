package com.netikras.studies.studentbuddy.commons.tools.http;

import java.util.List;

/**
 * Created by netikras on 17.3.14.
 */
public class HttpCtlFlags {

    public static final String RAW_VALUE = "HTTPCTL_raw_value";


    @SuppressWarnings("Duplicates")
    public static String getCtlValue(HttpResponse response, String ctlFlag) {
        List<String> headersList;
        String value = null;

        if (ctlFlag != null && ! ctlFlag.isEmpty()) {
            if (response != null) {
                headersList = response.getHeaders(ctlFlag);
                if (headersList != null) {
                    if (headersList.size() > 0) {
                        value = headersList.get(0);
                    } else {
                        value = "";
                    }
                }
            }
        }

        return value;
    }

    @SuppressWarnings("Duplicates")
    public static String getCtlValue(HttpRequest request, String ctlFlag) {
        List<String> headersList;
        String value = null;

        if (ctlFlag != null && ! ctlFlag.isEmpty()) {
            if (request != null) {
                headersList = request.getHeaders(ctlFlag);
                if (headersList != null) {
                    if (headersList.size() > 0) {
                        value = headersList.get(0);
                    } else {
                        value = "";
                    }
                }
            }
        }

        return value;
    }


    public static void setCtlValue(HttpResponse response, String ctlFlag, String value) {
        if (response != null) {
            if (ctlFlag != null && !ctlFlag.isEmpty()) {
                response.addHeader(ctlFlag, value);
            }
        }
    }

    public static void setCtlValue(HttpRequest request, String ctlFlag, String value) {
        if (request != null) {
            if (ctlFlag != null && !ctlFlag.isEmpty()) {
                request.addHeader(ctlFlag, value);
            }
        }
    }



}
