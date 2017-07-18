package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table("school")
public class School {

    private String id;

    private String title;
    private List<SchoolDepartment> departments;


}
