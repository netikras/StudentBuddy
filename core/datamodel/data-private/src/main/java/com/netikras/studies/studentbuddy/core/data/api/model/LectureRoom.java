package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "lecture_room")
public class LectureRoom {

    private String id;

    private String number; // e.g. 137A

    private School school;
    private Building building;
    private BuildingSection buildingSection;
    private BuildingFloor floor;

}
