package com.netikras.studies.studentbuddy.api.config;

import org.springframework.web.WebApplicationInitializer;


/**
 * Created by netikras on 17.6.21.
 */
public class Initializer extends GenericWebApplicationInitializer implements WebApplicationInitializer {


    @Override
    public String getContextConfigLocation() {
        return "com.netikras.studies.studentbuddy.api.config";
    }

    @Override
    public String[] getMappingUrls() {
        return new String[] {
                "/studbud/api/*",

        };
    }

    @Override
    public String getApplicationName() {
        return "studbud.api";
    }
}
