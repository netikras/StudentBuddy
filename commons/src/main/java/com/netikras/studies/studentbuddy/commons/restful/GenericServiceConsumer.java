package com.netikras.studies.studentbuddy.commons.restful;



import com.netikras.studies.studentbuddy.commons.model.RemoteEndpoint;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpCtlFlags;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpRequest;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpResponse;
import com.netikras.studies.studentbuddy.commons.tools.json.JSONService;
import com.netikras.studies.studentbuddy.commons.tools.json.JSONServiceImpl;

import java.util.Collection;

/**
 * Created by netikras on 17.4.4.
 */
public abstract class GenericServiceConsumer {

    private JSONService jsonService = new JSONServiceImpl();


    protected abstract RemoteEndpoint getRemoteEndpoint(String category);


    protected HttpResponse sendRequest(HttpRequest request) {
        return sendRequest(request, String.class);
    }

    protected <RespT> HttpResponse sendRequest(HttpRequest request, Class<RespT> responseBodyType) {
        return RestService.sendRequest(request, responseBodyType);
    }

    protected <CollectT extends Collection<RespT>, RespT> HttpResponse sendRequest(HttpRequest request, Class<CollectT> collectionType, Class<RespT> responseBodyType) {
        return RestService.sendRequest(request, collectionType, responseBodyType);
    }

    protected <R, C extends Collection<R>> C unmarshallResultCollection(HttpResponse response, Class<C> collectionType, Class<R> resultType) {
        C result = null;

        if (response != null && response.getObject() != null) {
            if (HttpCtlFlags.getCtlValue(response, HttpCtlFlags.RAW_VALUE) == null) {
                result = jsonService.jsonToObjectCollection(response.getObject(String.class), collectionType, resultType);
            }
        }

        return result;
    }

}
