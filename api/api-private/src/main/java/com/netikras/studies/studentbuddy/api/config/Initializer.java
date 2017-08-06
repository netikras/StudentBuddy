package com.netikras.studies.studentbuddy.api.config;

import com.netikras.studies.studentbuddy.api.filters.AuthorizationFilter;
import com.netikras.studies.studentbuddy.commons.P;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


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
                "/api/*",
//                "/user/login"

        };
    }

    @Override
    public String getApplicationName() {
        return "studbud.api";
    }

    @Override
    protected void afterStartup() {
        System.out.println("Adding filter");

        EnumSet<DispatcherType> disps = EnumSet.of(
                DispatcherType.REQUEST, DispatcherType.FORWARD);

        getServletContext().addFilter("authFilter", AuthorizationFilter.class)
                .addMappingForUrlPatterns(disps, true, getMappingUrls());
        System.out.println("Filter added");
    }
}
