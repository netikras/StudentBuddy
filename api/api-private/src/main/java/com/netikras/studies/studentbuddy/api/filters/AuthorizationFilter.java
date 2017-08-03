package com.netikras.studies.studentbuddy.api.filters;

import com.netikras.tools.common.remote.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class AuthorizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        boolean isLoggedIn;

        ThreadContext requestContext = ThreadContext.current();

        requestContext.setRequest((HttpServletRequest) request);
        requestContext.setResponse((HttpServletResponse) response);

        isLoggedIn = requestContext.isSessionValid();

        if (isLoggedIn || isAimingToLogin((HttpServletRequest) request)) {
            chain.doFilter(request, response);
        } else {
            redirectToLogin(requestContext);
        }

        requestContext.clear(true);

    }

    @Override
    public void destroy() {

    }



    private boolean isAimingToLogin(HttpServletRequest request) {
        return request.getRequestURI().contains("/login.html");
    }

    public void redirectToLogin(ThreadContext context) {
        HttpServletResponse response = context.getResponse();
        try {
            response.sendRedirect("/login.html");
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
