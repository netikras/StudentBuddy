package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Is an instance of a lecture. E.g. Maths on Monday @13:45
 *
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "lecture")
public class Lecture {

    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lecture")
    private List<Assignment> pendingAssignments;
    private List<DisciplineTest> pendingTests;
    private Date startsOn;
    private Date endsOn;
    private List<Comment> comments;
    private LectureRoom room;
    private List<Comment> notes;

}
