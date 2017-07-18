package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "person")
public class Person {
    private String id;

    private List<Role> roles;

    private String firstName;
    private String lastName;

    private String email;
    private String phoneNo;

    private List<LectureRoom> rooms;


    enum Role {
        STUDENT,
        LECTURER,
        ADMINISTRATION,
        MAINT,

    }

}
