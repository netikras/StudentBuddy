package com.netikras.studies.studentbuddy.commons.tools.http;


import com.netikras.studies.studentbuddy.commons.model.RemoteEndpoint;
import com.netikras.studies.studentbuddy.commons.tools.AuthenticationDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by netikras on 16.11.21.
 */
public class HttpRequest<T> {

    public enum HttpMethods {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE,
        HEAD,
        OPTIONS,
        TRACE
    }

    public enum Protocol {
        HTTP("http", 80),
        HTTPS("https", 443);

        private String value;
        private int port;

        Protocol(String value, int port) {
            this.value = value;
            this.port = port;
        }

        public String getvalue() {
            return this.value;
        }

        public int getPort() {
            return this.port;
        }
    }


    private Map<String, List<String>> headers;
    private Map<String, String> params;

    private T object;

    private HttpMethods method;

    private Protocol protocol;
    private String address;
    private int port;
    private String url;

    private String fullUrl;

    private int connectTimeout = 10 * 1000; // default -- 10 seconds
    private int readTimeout = 10 * 1000; // default -- 10 seconds

    private AuthenticationDetail authenticationDetail = null;
    private Map<String, String> variables;

    public HttpRequest() {
        this.headers = new HashMap<>();
        this.params = new HashMap<>();
        this.variables = new HashMap<>();
        this.method = HttpMethods.GET;
        this.url = "";
        this.object = null;
        this.protocol = Protocol.HTTP;
        this.port = 80;
        this.fullUrl = "";
        this.address = "localhost";
        addHeader("Content-Type", "application/json");
        addHeader("charset", "UTF-8");
    }

    public HttpRequest(String url, HttpMethods method) {
        this();
        setUrl(url);
        setMethod(method);
    }

    public HttpRequest(RemoteEndpoint endpoint) {
        this();
        digestEndpointConfiguration(endpoint);
    }

    public HttpRequest digestEndpointConfiguration(RemoteEndpoint endpoint) {
        if (endpoint == null) return this;
        setParams(endpoint.getParams());
        setHeaders(endpoint.getHeaders());
        setFullUrl(endpoint.getUrlString());

        return this;
    }


    public HttpRequest addHeader(String name, String value) {
        List<String> headersList = getHeaders(name);
        if (headersList == null) {
            headersList = new ArrayList<>();
            addHeadersList(name, headersList);
        }
        headersList.add(value);
        return this;
    }

    private void addHeadersList(String name, List<String> headersList) {
        getHeaders().put(name, headersList);
    }

    public List<String> getHeaders(String name) {
        return getHeaders().get(name);
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }


    public HttpRequest addParam(String name, String value) {
        String allowedParamRegex = "^[a-zA-Z0-9_-]*$";
        String disallowedParamRegex = "^.*(&|=).*$";

        //if (name.matches(allowedParamRegex) && value.matches(allowedParamRegex)) {
        if (name.matches(disallowedParamRegex) || value.matches(disallowedParamRegex)) {
            throw new IllegalArgumentException(String.format("Illegal parameters: name[%s], value[%s]", name, value));
        } else {
            params.put(name, value);
        }

        return this;
    }


    public Map<String, String> getParams() {
        return this.params;
    }

    public HttpRequest setDataObject(T object) {
        this.object = object;
        return this;
    }


    public T getObject() {
        return object;
    }

    public HttpRequest setObject(T object) {
        this.object = object;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequest setUrl(String url) {
        this.url = url;
        setVariables(getUrlProperties());
        return this;
    }

    public HttpMethods getMethod() {
        return method;
    }

    public HttpRequest setMethod(HttpMethods method) {
        this.method = method;
        return this;
    }


    public Protocol getProtocol() {
        return protocol;
    }

    public HttpRequest setProtocol(Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public HttpRequest setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPort() {
        return port;
    }

    public HttpRequest setPort(int port) {
        this.port = port;
        return this;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public HttpRequest setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;

        String protocolSeparator = "://";

        String item;
        int pos1;
        int pos2;

        pos1 = 0;
        pos2 = fullUrl.indexOf(protocolSeparator);
        item = fullUrl.substring(pos1, pos2); // protocol
        setProtocol(Protocol.valueOf(item.toUpperCase()));

        pos1 = pos2 + protocolSeparator.length();
        pos2 = fullUrl.indexOf("/", pos1);
        item = fullUrl.substring(pos1, pos2);

        String domain = item;
        int portPos = item.indexOf(":");
        if (portPos > 1) {
            domain = item.substring(0, portPos);
            String portStr = item.substring(portPos + 1);

            setPort(Integer.parseInt(portStr));
        }

        setAddress(domain);

        pos1 = pos2;
        pos2 = fullUrl.indexOf("?");
        if (pos2 < 0) {
            item = fullUrl.substring(pos1);
            setUrl(item);
        } else {
            item = fullUrl.substring(pos1, pos2);
            setUrl(item);
            item = fullUrl.substring(pos2 + 1);
            String[] params = item.split("&");

            if (params.length > 0) {
                for (String paramPair : params) {
                    if (paramPair != null && paramPair.length() > 0) {
                        String[] keyVal = paramPair.split("=");
                        if (keyVal.length > 2) {
                            throw new IllegalArgumentException("Incorrect URL param-value pair: " + paramPair);
                        }
                        if (keyVal.length == 2) {
                            addParam(keyVal[0], keyVal[1]);
                            continue;
                        }
                        if (keyVal.length == 1) {
                            addParam(keyVal[0], "");
                        }
                    }
                }
            }
        }

        setVariables(getUrlProperties());

        return this;
    }

    public AuthenticationDetail getAuthenticationDetail() {
        return authenticationDetail;
    }

    public void setAuthenticationDetail(AuthenticationDetail authenticationDetail) {
        this.authenticationDetail = authenticationDetail;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public void setUrlProperty(String placeholder, String value) {
        String url;

        Map<String, String> params = getVariables();
        if (params == null) {
            params = new HashMap<>();
            setVariables(params);
        }

        params.put(placeholder, value);
    }

    public String applyUrlProperties(String target) {
        Map<String, String> variables = getVariables();
        String targetValue = target;

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            if (entry == null) continue;
            if (entry.getKey() == null || entry.getKey().isEmpty()) continue;
            if (entry.getValue() == null || entry.getValue().isEmpty()) continue;

            targetValue = applyUrlProperty(target, entry.getKey(), entry.getValue());
        }

        return targetValue;
    }

    public List<String> getMissingVariables() {
        List<String> missingVars = new ArrayList<>(2);

        for (Map.Entry<String, String> entry : getVariables().entrySet()) {
            if (entry == null) continue;
            if (entry.getKey() == null || entry.getKey().isEmpty()) continue;
            if (entry.getValue() == null) missingVars.add(entry.getKey());
        }
        return missingVars;
    }

    public String applyUrlProperty(String target, String placeholder, String value) {
        String url;

        url = target;
        if (url != null) {
            url = url.replaceAll("\\{" + placeholder + "\\}", value);
        }

        return url;
    }

    public void applyUrlProperty(String placeholder, String value) {
        String url;

        url = getUrl();
        setUrl(applyUrlProperty(url, placeholder, value));

        url = getFullUrl();
        setFullUrl(applyUrlProperty(url, placeholder, value));
    }

    public String buildUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        String result;

        if (getFullUrl() == null || getFullUrl().isEmpty()) {
            stringBuilder.append(getProtocol().value).append("://")
                    .append(address)
                    .append(":").append(port)
                    .append(getUrl())
            ;
        } else {
            result = getFullUrl();
            int paramsPos = result.indexOf("?");
            if (paramsPos > 0) {
                result = result.substring(0, paramsPos);
            }
            stringBuilder.append(result);
        }

        int i = 0;
        if (getParams().size() > 0) {
            stringBuilder.append("?");
            // NOTE! HashMap is unordered hence param-value pairs order might be mixed
            for (String param : getParams().keySet()) {
                stringBuilder
                        .append(param).append("=").append(getParams().get(param))
                        .append(++i < getParams().size() ? "&" : "");
            }
        }

        result = stringBuilder.toString();
        result = applyUrlProperties(result);

        return result;
    }


    public String encode(String encoding) {
        return buildUrl();
    }

    public String encode() {
        return encode("UTF-8");
    }

    public Map<String, String> getUrlProperties() {
        Map<String, String> properties = new HashMap<>();

        String url;

        url = getFullUrl();
        if (url == null || url.isEmpty()) {
            url = getUrl();
        }

        if (url == null || url.isEmpty()) {
            return properties;
        }


        int pos1;
        int pos2;
        String substr;
        String value;

        substr = url;

        while (substr.length() > 0) {
            pos1 = substr.indexOf("{");
            if (pos1 > -1) {
                substr = substr.substring(pos1 + 1);
                pos2 = substr.indexOf("}");
                if (pos2 < 1) {
                    throw new IndexOutOfBoundsException(
                            String.format("Missing closing bracket for PathVariable as pos: %d. Substring: %s", pos2, substr));
                }

                value = substr.substring(0, pos2);

                properties.put(value, null);
            } else {
                break;
            }

        }

        return properties;
    }


    @Override
    public String toString() {
        return "HttpRequest{" +
                "headers=" + headers +
                ", params=" + params +
                ", object=" + object +
                ", method=" + method +
                ", protocol=" + protocol +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                ", fullUrl='" + fullUrl + '\'' +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", authenticationDetail=" + authenticationDetail +
                '}';
    }
}
