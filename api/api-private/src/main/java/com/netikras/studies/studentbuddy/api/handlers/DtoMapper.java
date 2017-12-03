package com.netikras.studies.studentbuddy.api.handlers;

import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

import static com.netikras.studies.studentbuddy.core.data.sys.SysProp.DTO_RETURN_DEPTH;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class DtoMapper {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private ResourceRepoProvider repoProvider;
    @Resource
    private SystemService systemService;

    private int getDepth() {
        return systemService.getSettingValue(DTO_RETURN_DEPTH);
    }

    public Object toDto(Object model) {
        return toDto(model, getDepth());
    }

    @Transactional
    protected Object toDto(Object model, int depth) {
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


    public Collection<?> toDtos(Collection<?> models) {
        return toDtos(models, getDepth());
    }

    @Transactional
    public Collection<?> toDtos(Collection<?> models, int depth) {
        if (isNullOrEmpty(models)) {
            return new ArrayList<>(0);
        }

        try {
            Class dtoType = null;
            for (Object model : models) {
                if (model != null) {
                    dtoType = repoProvider.getDtoTypeForModel(model.getClass());
                    break;
                }
            }
            if (dtoType == null) {
                throw new IllegalStateException("Models Collection is filled with nulls");
            }
            if (depth > -1) {
                return modelMapper.transformAll(models, dtoType, new MappingSettings().setDepthMax(depth));
            }
            return modelMapper.transformAll(models, dtoType);

        } catch (IllegalStateException ise) {
            Collection<?> dtos = new ArrayList<>(models.size());
            for (int i = 0; i < models.size(); i++) {
                dtos.add(null);
            }
            return dtos;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ArrayList<>(0);
    }

    public Object toModel(Object dto) {
        return toModel(dto, getDepth());
    }

    public Object toModel(Object dto, int depth) {
        if (dto == null) {
            return null;
        }
        try {
            Class modelType = repoProvider.getModelTypeForDto(dto.getClass());
            if (depth > -1) {
                return modelMapper.apply(modelType.newInstance(), dto, new MappingSettings().setDepthMax(depth));
            }
            return modelMapper.apply(modelType.newInstance(), dto);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Collection<?> toModels(Collection<?> dtos) {
        return toModels(dtos, getDepth());
    }

    public Collection<?> toModels(Collection<?> dtos, int depth) {
        if (isNullOrEmpty(dtos)) {
            return new ArrayList<>(0);
        }

        try {
            Class modelType = null;
            for (Object dto : dtos) {
                if (dto != null) {
                    modelType = repoProvider.getModelTypeForDto(dto.getClass());
                    break;
                }
            }
            if (modelType == null) {
                throw new IllegalStateException("DTOs Collection is filled with nulls");
            }

            if (depth > -1) {
                return modelMapper.applyAll(dtos, modelType, new MappingSettings().setDepthMax(depth));
            }
            return modelMapper.applyAll(dtos, modelType);

        } catch (IllegalStateException ise) {
            Collection<?> models = new ArrayList<>(dtos.size());
            for (int i = 0; i < dtos.size(); i++) {
                models.add(null);
            }
            return models;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>(0);
    }

}
