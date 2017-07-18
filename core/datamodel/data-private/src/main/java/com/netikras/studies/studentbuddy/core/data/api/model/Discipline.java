package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Like Maths, English, etc.
 *
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "discipline")
public class Discipline {

    private String id;


    private School school;
    private String title;
    private String description;
    private List<RelatedSites> sites;

    private List<Lecturer> lecturers;

    private List<Lecture> lectures;

}
