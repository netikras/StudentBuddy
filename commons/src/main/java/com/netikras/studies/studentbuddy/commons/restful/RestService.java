package com.netikras.studies.studentbuddy.commons.restful;


import com.netikras.studies.studentbuddy.commons.tools.AuthenticationDetail;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpRequest;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpResponse;
import com.netikras.studies.studentbuddy.commons.tools.json.JSONService;
import com.netikras.studies.studentbuddy.commons.tools.json.JSONServiceImpl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;


/**
 * Created by netikras on 16.11.21.
 */
public final class RestService {

    private static JSONService jsonService = new JSONServiceImpl();

    private RestService() { }

    @SuppressWarnings("unchecked")
    public static HttpResponse sendRequest(HttpRequest requestData) {
        return sendRequest(requestData, String.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> HttpResponse sendRequest(HttpRequest requestData, Class<T> resultBodyType) {

        HttpResponse httpResponse;

        RestTemplate restTemplate = buildRestTemplate(requestData);
        HttpEntity entity = buildHttpEntity(requestData);

        HttpMethod method = HttpMethod.resolve(requestData.getMethod().toString());
        ResponseEntity<T> responseEntity = restTemplate.exchange(requestData.buildUrl(), method, entity, resultBodyType);

        httpResponse = buildResponse(responseEntity);

        return httpResponse;
    }


    public static <C extends Collection<T>, T> HttpResponse sendRequest(HttpRequest requestData, Class<C> collectionType, Class<T> resultBodyType) {

        HttpResponse httpResponse;

        RestTemplate restTemplate = buildRestTemplate(requestData);
        HttpEntity entity = buildHttpEntity(requestData);

        HttpMethod method = HttpMethod.resolve(requestData.getMethod().toString());
        ResponseEntity<C> responseEntity = restTemplate.exchange(requestData.buildUrl(), method, entity, new ParameterizedTypeReference<C>() {});

        httpResponse = buildResponse(responseEntity);

        return httpResponse;

    }


    private static HttpEntity buildHttpEntity(HttpRequest requestData) {

        Object requestBody;


        AuthenticationDetail authenticationDetail = requestData.getAuthenticationDetail();
        if (authenticationDetail != null && requestData.getHeaders("auth") == null) {
            requestData.addHeader("auth", jsonService.objectToJson(authenticationDetail));
        }


        requestBody = jsonService.objectToJson(requestData.getObject());

        HttpEntity<String> entity = new HttpEntity<>((String) requestBody, new LinkedMultiValueMap<>(requestData.getHeaders()));

        return entity;

    }

    private static HttpResponse buildResponse(ResponseEntity responseEntity) {
        HttpResponse httpResponse = new HttpResponse();

        httpResponse.setHeaders(responseEntity.getHeaders());
        httpResponse.setStatus(responseEntity.getStatusCodeValue());
        httpResponse.setObject(responseEntity.getBody());

        return httpResponse;
    }


    private static RestTemplate buildRestTemplate(HttpRequest requestData) {

        RestTemplate restTemplate = new RestTemplate();

        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(requestData.getConnectTimeout());
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(requestData.getReadTimeout());


        return restTemplate;
    }


}
