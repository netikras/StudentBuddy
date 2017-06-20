package com.netikras.studies.studentbuddy.commons.tools.json;

import java.util.Collection;

public interface JSONService {

    String objectToJson(Object object);

    <T> T jsonToObject(String json, Class<T> type);

    <T> T jsonToExistingObject(String json, T object);

    <T1 extends Collection<T2>, T2> T1 jsonToObjectCollection(String json, Class<T1> collectionType, Class<T2> objectType);

}