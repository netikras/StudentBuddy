package com.netikras.studies.studentbuddy.commons.tools.json;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Collection;

public class JSONServiceImpl implements JSONService {


    private ObjectMapper objectMapper;


    public JSONServiceImpl() {
        objectMapper = new ObjectMapper();
    }


    public String objectToJson(Object object) {
        String json = "{}";

        if (object != null) {
            try {
                json = objectMapper.writeValueAsString(object);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return json;
    }


    public <T> T jsonToObject(String json, Class<T> type) {
        T object = null;

        if (json != null && !json.isEmpty()) {
            try {
                object = objectMapper.readValue(json, type);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return object;
    }


    @Override
    public <T> T jsonToExistingObject(String json, T object) {

        if (object != null) {

            try {
                objectMapper.readerForUpdating(object).readValue(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return object;
    }


    @Override
    public <T1 extends Collection<T2>, T2> T1 jsonToObjectCollection(String json, Class<T1> collectionType, Class<T2> objectType) {
        T1 collection = null;

        try {
            collection = objectMapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(collectionType, objectType));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return collection;
    }


}