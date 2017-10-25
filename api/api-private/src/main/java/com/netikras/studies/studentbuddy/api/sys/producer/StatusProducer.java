package com.netikras.studies.studentbuddy.api.sys.producer;

import com.netikras.studies.studentbuddy.api.sys.generated.StatusApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StatusProducer extends StatusApiProducer {

    @Resource
    private ResourceRepoProvider repoProvider;

    @Override
    protected String onGetStringMessageEcho(String message) {
        return message;
    }

    @Override
    protected Object onGetObjectDtoStructure(String resource) {
        Class type = repoProvider.getDtoTypeForResource(resource);
        Object dto = null;
        try {
            dto = type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    protected List<String> onGetListDtoResources() {
        List<String> names = new ArrayList<>(com.netikras.studies.studentbuddy.core.data.meta.Resource.values().length);
        for (com.netikras.studies.studentbuddy.core.data.meta.Resource resource : com.netikras.studies.studentbuddy.core.data.meta.Resource.values()) {
            if (resource.name().startsWith("_")) {
                continue;
            }
            names.add(resource.name());
        }
        return names;
    }
}
