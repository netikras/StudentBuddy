package com.netikras.studies.studentbuddy.core.data.api.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ResourceRepoProviderTest.Config.class})
public class ResourceRepoProviderTest {

    @Resource
    private ResourceRepoProvider provider;

    @Test
    public void getDtoTypeForResource() throws Exception {
        Object dto = provider.getDtoTypeForResource(com.netikras.studies.studentbuddy.core.data.meta.Resource.SCHOOL_DEPARTMENT.name()).newInstance();
        System.out.println(dto);
    }

    @Test
    public void getDtoTypeForModel() throws Exception {
    }

    @Test
    public void getModelTypeForDto() throws Exception {
    }

    @Test
    public void getTypeForResource() throws Exception {
    }

    @Test
    public void getRepoForResource() throws Exception {
    }

    @Test
    public void getRepoForResource1() throws Exception {
    }

    @Test
    public void getRepoForModel() throws Exception {
    }

    @Test
    public void getRepoForModel1() throws Exception {
    }



    @Configuration
    public static class Config {
        @Bean
        public ResourceRepoProvider repoProvider() {
            return new ResourceRepoProvider();
        }
    }

}
