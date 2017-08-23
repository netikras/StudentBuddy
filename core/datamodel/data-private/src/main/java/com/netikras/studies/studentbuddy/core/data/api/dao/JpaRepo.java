package com.netikras.studies.studentbuddy.core.data.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaRepo<T> extends JpaRepository<T, String> {

    default String wrapSearchString(String substring) {
        String result = null;

        if (substring == null || substring.isEmpty()) {
            return "";
        }

        result = substring.replace('%', '%');

        if (!result.startsWith("^")) {
            result = "%" + result;
        }

        if (!result.endsWith("$")) {
            result = result + "%";
        }


        return result;
    }

}
