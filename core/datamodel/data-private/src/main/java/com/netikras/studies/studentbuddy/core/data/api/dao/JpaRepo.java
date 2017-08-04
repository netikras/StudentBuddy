package com.netikras.studies.studentbuddy.core.data.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaRepo <T> extends JpaRepository<T, String> {

}
