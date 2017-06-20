package com.netikras.studies.studentbuddy.commons.tools;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by netikras on 17.3.5.
 */
public class Misc {


    public static URL[] getClasspathUrls() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        return urls;
    }

}
