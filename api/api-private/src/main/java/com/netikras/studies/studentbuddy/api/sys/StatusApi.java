package com.netikras.studies.studentbuddy.api.sys;

import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;
import static com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type.URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/status")
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class StatusApi {


    @RestEndpoint(url = "/test/echo/{message}", method = HttpMethod.GET, dtoType = String.class, action = "getMessageEcho")
    public abstract String getMessageEcho(@MethodParam(type = URL, name = "message") String message);

    @RestEndpoint(url = "/test/dto/structure/{resource}", method = HttpMethod.GET, action = "getDtoStructure")
    public abstract Object getDtoStructure(@MethodParam(type = URL, name = "resource") String resource);

    @RestEndpoint(url = "/test/dto/structure", method = HttpMethod.GET, action = "getDtoResources")
    public abstract List<String> getDtoResources();


}
