package com.netikras.studies.studentbuddy.api.filters;

import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.tools.common.security.ThreadContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class HttpThreadContext extends ThreadContext {


    public HttpThreadContext() {
    }

    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;

    private User user;

//    private Map<Object, Object> extras;

    private static ThreadLocal<HttpThreadContext> context = null;
    private ThreadContext globalContext = null;

    public static HttpThreadContext current() {
        if (context == null) {
            context = new ThreadLocal<>();
        }
        if (context.get() == null) {
            context.set(new HttpThreadContext());
            context.get().setGlobalContext(ThreadContext.current());
        }
        return context.get();
    }

    protected void setGlobalContext(ThreadContext global) {
        globalContext = global;
    }

    @Override
    public void clear(boolean withExtras) {
        setSession(null);
        setRequest(null);
        setResponse(null);
        if (withExtras)
            globalContext.clear(true);
    }

//    public int persistExtrasToSession(boolean overwrite) {
//        if (globalContext == null) return 0;
//        if (getSession() == null) return 0;
//        int total = 0;
//
//        for (Map.Entry<Object, Object> entry : getExtras().entrySet()) {
//            Object key = entry.getKey();
//            if (!String.class.isAssignableFrom(key.getClass())) continue;
//            if (getSession().getAttribute((String) key) != null && !overwrite) continue;
//
//            getSession().setAttribute((String) key, entry.getValue());
//            total++;
//        }
//
//        return total;
//    }

    public int persistExtraToSession(Object key, boolean overwrite) {
        if (key == null) return 0;
        if (globalContext == null) return 0;
        if (getSession() == null) return 0;
        if (!String.class.isAssignableFrom(key.getClass())) return 0;

        Object value = getValue(key);

        if (getSession().getAttribute((String) key) != null && ! overwrite) return 0;
        getSession().setAttribute((String) key, value);

        return 1;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        addValue("user", user);
        this.user = user;
    }

    public void setNewUser(User user) {
        setUser(user);
        setSession(getRequest().getSession(true));
        getSession().setAttribute("user", user);
    }

    public void destroy() {
        try {
            HttpSession session = getSession();
            clear(true);
            if (session != null) {
                session.invalidate();
            }
        } catch (Exception e) {

        }

    }

    public void removeUser() {
        if (getSession() != null) {
            removeValue("user");
            getSession().removeAttribute("user");
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
        if (request != null) {
            HttpSession httpSession = request.getSession(false);
            if (httpSession != null) {
                setSession(httpSession);
                setUser((User) session.getAttribute("user"));
            }

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

//    public Map<Object, Object> getExtras() {
//        return extras;
//    }

//    public void setExtras(Map<Object, Object> extras) {
//        this.extras = extras;
//    }

    @Override
    public Object getValue(Object key) {
        return globalContext.getValue(key);
    }

    @Override
    public void removeValue(Object key) {
        globalContext.removeValue(key);
    }

    @Override
    public void addValue(Object key, Object value) {
        globalContext.addValue(key, value);
    }

    @Override
    public String toString() {
        return "HttpThreadContext{" +
                "request=" + request +
                ", response=" + response +
                ", session=" + session +
                ", user=" + user +
                '}';
    }
}

