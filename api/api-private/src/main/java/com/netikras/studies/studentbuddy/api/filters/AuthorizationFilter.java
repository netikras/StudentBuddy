package com.netikras.studies.studentbuddy.api.filters;

import com.netikras.tools.common.remote.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

//@WebFilter
public class AuthorizationFilter implements Filter {


    private ServletContext servletContext;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String loginUrl = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("STARTING FILTER");
        servletContext = filterConfig.getServletContext();
        loginUrl = servletContext.getContextPath() + "/api/user/login";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.debug("INCOMING REQUEST");
        boolean isLoggedIn;

        ThreadContext requestContext = ThreadContext.current();

        requestContext.setRequest((HttpServletRequest) request);
        requestContext.setResponse((HttpServletResponse) response);

        isLoggedIn = requestContext.isSessionValid()
                && requestContext.getUser() != null;

        logger.debug("User {} approaching by the URL: {}. loggedIn={}", requestContext.getUser(), ((HttpServletRequest) request).getRequestURI(), isLoggedIn);

        if (isLoggedIn || isAimingToLogin((HttpServletRequest) request)) {
            logger.info("Requestor's session validated. Proceeding with the request.");
            chain.doFilter(request, response);
        } else {
            logger.info("User {} is not logged in for the request. Returning 401", requestContext.getUser());
//            redirectToLogin(requestContext);
            ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.getCode(), "Not logged in");
        }

        requestContext.clear(true);

    }

    @Override
    public void destroy() {

    }


    private boolean isAimingToLogin(HttpServletRequest request) {
        return request.getRequestURI().equals(loginUrl);
    }

    public void redirectToLogin(ThreadContext context) {
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
