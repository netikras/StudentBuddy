package com.netikras.studies.studentbuddy.commons.tools.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by netikras on 17.6.21.
 */
public final class IoUtils {

    private IoUtils() {}

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {

        int available = 0;

        byte[] buffer = new byte[1024];

        while ((available = inputStream.available()) > 0) {
            int received = inputStream.read(buffer);
            if (received > 0) {
                outputStream.write(buffer, 0, received);
            }
        }

    }


}
