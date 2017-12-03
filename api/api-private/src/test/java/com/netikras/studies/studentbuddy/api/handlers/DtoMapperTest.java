package com.netikras.studies.studentbuddy.api.handlers;


import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//@ComponentScan(basePackageClasses = DtoMapperTest.SpringConfig.class)
@ContextConfiguration(classes = {DtoMapperTest.SpringConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DtoMapperTest {

    @Resource
    private DtoMapper dtoMapper;


    @Test
    public void toDto() {

    }

    public void toDto1() {
    }

    public void toDtos() {
    }

    public void toDtos1() {
    }

    public void toModel() {
    }

    public void toModel1() {
    }

    public void toModels() {
    }

    public void toModels1() {
    }


    @Configuration
    public static class SpringConfig {

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public ResourceRepoProvider repoProvider() {
            return new ResourceRepoProvider();
        }

        @Bean
        public DtoMapper dtoMapper() {
            return new DtoMapper();
        }
    }
}