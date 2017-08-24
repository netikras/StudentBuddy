package com.netikras.studies.studentbuddy.core.data.api.dao;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class JpaRepoTest {


    JpaRepo repo;

    @Before
    public void init() {
        repo = Mockito.spy(JpaRepo.class);
        when(repo.wrapSearchString(anyString())).thenCallRealMethod();
    }

    private String wrap(String query) {
        return repo.wrapSearchString(query);
    }

    @Test
    public void wrapSearchStringTest() throws Exception {
        assertEquals("Wrapped search query is incorrect", "%PIN13%", wrap("PIN13"));
        assertEquals("Wrapped search query is incorrect", "%PIN13", wrap("PIN13$"));
        assertEquals("Wrapped search query is incorrect", "PIN13%", wrap("^PIN13"));
        assertEquals("Wrapped search query is incorrect", "%P%N\\$3%", wrap("P*N\\$3"));
        assertEquals("Wrapped search query is incorrect", "%PI[%]1$3", wrap("PI%1$3$"));
    }



}
