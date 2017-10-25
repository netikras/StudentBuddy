package com.netikras.studies.studentbuddy.api.handlers;

import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class DtoMapper {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private ResourceRepoProvider repoProvider;

    @Transactional
    public Object toDto(Object model, int depth) {
        if (model == null) {
            return null;
        }
        try {
            Class dtoType = repoProvider.getDtoTypeForModel(model.getClass());
            if (depth > -1) {
                return modelMapper.transform(model, dtoType.newInstance(), new MappingSettings().setDepthMax(depth));
            }
            return modelMapper.transform(model, dtoType.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Object toModel(Object dto, int depth) {
        if (dto == null) {
            return null;
        }
        try {
            Class modelType = repoProvider.getModelTypeForDto(dto.getClass());
            if (depth > -1) {
                return modelMapper.apply(modelType.newInstance(), dto, new MappingSettings().setDepthMax(depth));
            } else {
                return modelMapper.apply(modelType.newInstance(), dto);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
