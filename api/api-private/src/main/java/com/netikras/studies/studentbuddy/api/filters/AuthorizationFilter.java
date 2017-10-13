package com.netikras.studies.studentbuddy.api.filters;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.sys.SysProp;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("authFilterImpl")
@Order(5)
public class AuthorizationFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String loginUrl = null;

    @Resource
    private SystemService systemService;

    @Resource
    private ServletContext servletContext;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("STARTING FILTER");
        servletContext = filterConfig.getServletContext();
        loginUrl = servletContext.getContextPath() + "/api/user/login";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.debug("INCOMING REQUEST");
        boolean loggedIn;

        if (systemService.getSettingValue(SysProp.SESSION_SUSPEND)) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot proceed")
                    .setMessage2("All requests have been suspended by administrator")
                    .setStatusCode(HttpStatus.NO_RESPONSE)
                    ;
        }

        HttpThreadContext requestContext = HttpThreadContext.current();

        requestContext.setRequest((HttpServletRequest) request);
        requestContext.setResponse((HttpServletResponse) response);

        loggedIn = isLoggedIn();

        logger.debug("User {} approaching by the URL: {}. loggedIn={}", requestContext.getUser(), ((HttpServletRequest) request).getRequestURI(), loggedIn);

        if (isAimingToLogin((HttpServletRequest) request)) {
            requestContext.destroy();

            User user = requestContext.getUser();

            requestContext.setUser(systemService.getGuestUser());
            requestContext.setRequest((HttpServletRequest) request);
            requestContext.setResponse((HttpServletResponse) response);

            logger.info("Requester is attempting to login. Proceeding with the request.");
            chain.doFilter(request, response);

            if (requestContext.getUser() != null) {
                requestContext.getSession().setMaxInactiveInterval(systemService.getSettingValue(SysProp.SESSION_TIMEOUT));
            } else {
                if (systemService.getSettingValue(SysProp.FAILED_LOGIN_KEEP_USER)) {
                    requestContext.setUser(user);
                }
            }
        } else if (loggedIn) {
            logger.info("Requester's session validated. Proceeding with the request.");
            requestContext.getSession().setMaxInactiveInterval(systemService.getSettingValue(SysProp.SESSION_TIMEOUT));
            chain.doFilter(request, response);
        } else {

            requestContext.setUser(systemService.getGuestUser());

            if (requestContext.getUser() != null) {
                chain.doFilter(request, response);
            } else { // guest account is disabled
                logger.info("User {} is not logged in for the request. Returning 401", requestContext.getUser());
//            redirectToLogin(requestContext);
                ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.getCode(), "Not logged in");
            }
        }

        requestContext.clear(true);

    }

    @Override
    public void destroy() {

    }

    private boolean isLoggedIn() {
        HttpThreadContext requestContext = HttpThreadContext.current();
        return requestContext.isSessionValid()
                && requestContext.getUser() != null
                && !"guest".equals(requestContext.getUser().getName());
    }

    private boolean isAimingToLogin(HttpServletRequest request) {
        return request.getRequestURI().equals(loginUrl);
    }

    public void redirectToLogin(HttpThreadContext context) {
        HttpServletResponse response = context.getResponse();
        try {
            response.sendRedirect(loginUrl);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.getCode(), "Failed to redirect to login page");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
