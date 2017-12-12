package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.studies.studentbuddy.core.data.meta.Identifiable;
import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "floor")
public class BuildingFloor implements Identifiable {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @Column(name = "number", nullable = false)
    @ModelTransform(dtoUpdatable = false)
    private int number;

    @Column(name = "title")
    @ModelTransform
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_sec_id")
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "buildingSection")
    private BuildingSection section;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "building_id")
    @ModelTransform(dtoUpdatable = false)
    private Building building;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "floor", orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @ModelTransform(dtoUpdatable = false)
    private List<FloorLayout> layouts;

    @OneToMany(mappedBy = "floor", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "lectureRooms")
    private List<LectureRoom> rooms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BuildingSection getSection() {
        return section;
    }

    public void setSection(BuildingSection section) {
        this.section = section;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<FloorLayout> getLayouts() {
        return layouts;
    }

    public void setLayouts(List<FloorLayout> layouts) {
        this.layouts = layouts;
    }

    public List<LectureRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<LectureRoom> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "BuildingFloor{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", section=" + section +
                ", building=" + building +
                ", layouts=" + layouts +
                ", rooms=" + rooms +
                '}';
    }
}
