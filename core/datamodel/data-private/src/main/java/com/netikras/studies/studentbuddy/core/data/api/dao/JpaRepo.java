package com.netikras.studies.studentbuddy.core.data.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRepo <T> extends JpaRepository<T, String> {

}
