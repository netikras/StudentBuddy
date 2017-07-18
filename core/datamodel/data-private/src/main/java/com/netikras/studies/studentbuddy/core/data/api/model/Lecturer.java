package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "lecturer")
public class Lecturer {
    private String id;

    private Person person;

    private String degree;


}
