package com.netikras.studies.studentbuddy.api.config;

import com.netikras.tools.common.exception.FriendlyException;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import com.netikras.tools.common.properties.PropertiesAssistant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

/**
 * Created by netikras on 17.3.15.
 */
public abstract class GenericWebApplicationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    protected ServletRegistration.Dynamic dispatcher = null;
    protected ServletRegistration dispatcherServlet = null;
    protected WebApplicationContext webAppContext = null;
    protected ServletContext servletContext = null;

    private Properties initialProperties = null;


    public abstract String getContextConfigLocation();

    public abstract String[] getMappingUrls();

    public abstract String getApplicationName();


    protected void beforeStartup() {
        logger.debug("default:beforeStartup");
    }

    protected void afterStartup() {
        logger.debug("default:afterStartup");
    }


    public Properties getInitialProperties() {
        return initialProperties;
    }


    /**
     * Loads properties prefixed with application name ({@link #getApplicationName()}), e.g. myCoolApp.server.port
     * @throws FriendlyException in case of any uncopable error
     */
    protected void loadInitialProperties() throws FriendlyException {
        if (initialProperties != null) {
            throw new FriendlyUncheckedException("Initial properties can be loaded only once!");
        }

        initialProperties = PropertiesAssistant.loadInitProperties(System.getProperties(), getApplicationName());

    }

    protected void loadInitialProperties(String prefix) throws FriendlyException {
        if (initialProperties != null) {
            throw new FriendlyUncheckedException("Initial properties can be loaded only once!");
        }

        initialProperties = PropertiesAssistant.loadInitProperties(System.getProperties(), prefix);
    }

    public void onStartup(ServletContext servletContext) throws ServletException {

        logger.info("Beginning component initialization");
        logger.debug("Context config location: {}", getContextConfigLocation());

        this.servletContext = servletContext;

        beforeStartup();

        dispatcherServlet = servletContext.getServletRegistration("DispatcherServlet");


        webAppContext = getWebApplicationContext();

        dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(webAppContext));
        if (dispatcher != null) {
            dispatcher.setLoadOnStartup(1);
            addMappings(dispatcher);
        }

        afterStartup();
    }

    protected ServletRegistration.Dynamic getLoadedDynamicDispatcherRegistration() {
        return this.dispatcher;
    }

    protected ServletRegistration getLoadedDispatcherRegistration() {
        return this.dispatcherServlet;
    }

    protected WebApplicationContext getLoadedWebAppContext() {
        return this.webAppContext;
    }

    private AnnotationConfigWebApplicationContext getWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(getContextConfigLocation());
        return context;
    }

    protected ServletContext getServletContext() {
        return servletContext;
    }

    private void addMappings(ServletRegistration.Dynamic dispatcher) {
        for (String mapping : getMappingUrls()) {
            logger.debug("Adding URL mapping: [{}]", mapping);
            dispatcher.addMapping(mapping);
        }
    }


}
