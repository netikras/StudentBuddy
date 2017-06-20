package com.netikras.studies.studentbuddy.commons.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by netikras on 16.10.10.
 */
public final class Converter {

    private Converter() {}

    public static String throwableToString(Throwable throwable) {
        String result = "";

        if (throwable != null) {
            StringBuilder resultBuilder = new StringBuilder();

            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);

            throwable.printStackTrace(printWriter);

            resultBuilder.append(Throwable.class.toString());
            resultBuilder.append(writer.toString());


            result = resultBuilder.toString();
        }

        return result;
    }


}
