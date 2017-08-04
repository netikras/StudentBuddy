package com.netikras.studies.studentbuddy.api.config;

import com.netikras.studies.studentbuddy.api.filters.AuthorizationFilter;
import com.netikras.studies.studentbuddy.commons.P;
import org.springframework.web.WebApplicationInitializer;


/**
 * Created by netikras on 17.6.21.
 */
public class Initializer extends GenericWebApplicationInitializer implements WebApplicationInitializer {


    public Initializer() {
        System.out.println("INITIALIZING!!!");
    }

    @Override
    public String getContextConfigLocation() {

        return P.BASE_PACKAGE + ".api.config";
    }

    @Override
    public String[] getMappingUrls() {
        System.out.println("Providing mapping URLs");
        return new String[] {
                "/studbud/api/*",

        };
    }

    @Override
    public String getApplicationName() {
        return "studbud.api";
    }

    @Override
    protected void afterStartup() {
        getServletContext().addFilter("authFilter", AuthorizationFilter.class);
    }
}
