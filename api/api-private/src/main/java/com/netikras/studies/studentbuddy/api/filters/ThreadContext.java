package com.netikras.studies.studentbuddy.api.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ThreadContext {


    public ThreadContext() {
        extras = new HashMap<>();
    }

    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;

    private Map<Object, Object> extras;


    public static ThreadContext current() {
        ThreadLocal<ThreadContext> context = new ThreadLocal<>();
        ThreadContext threadContext = context.get();
        if (threadContext == null) {
            threadContext = new ThreadContext();
            context.set(threadContext);
        }
        return threadContext;
    }

    public void clear(boolean withExtras) {
        setSession(null);
        setRequest(null);
        setResponse(null);
        if (withExtras)
            getExtras().clear();
    }

    public boolean isSessionValid() {
        return getSession() != null
                && (getInactiveInterval() / 1000) < getSession().getMaxInactiveInterval()
                ;
    }

    public long getInactiveInterval() {
        if (getSession() != null) {
            return System.currentTimeMillis() - getSession().getLastAccessedTime();
        }
        return Long.MAX_VALUE;
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
        if (request != null) {
            setSession(request.getSession(false));
        }
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public Map<Object, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<Object, Object> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "ThreadContext{" +
                "request=" + request +
                ", response=" + response +
                ", session=" + session +
                ", extras=" + extras +
                '}';
    }
}

