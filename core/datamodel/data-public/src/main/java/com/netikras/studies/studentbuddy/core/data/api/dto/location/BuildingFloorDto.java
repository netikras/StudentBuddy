package com.netikras.studies.studentbuddy.core.data.api.dto.location;

import java.util.List;

public class BuildingFloorDto {

    private String id;
    private String number;
    private String title;
    private BuildingSectionDto buildingSection;
    private BuildingDto building;
    private List<FloorLayoutDto> layouts;
    private List<LectureRoomDto> lectureRooms;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BuildingSectionDto getBuildingSection() {
        return buildingSection;
    }

    public void setBuildingSection(BuildingSectionDto buildingSection) {
        this.buildingSection = buildingSection;
    }

    public BuildingDto getBuilding() {
        return building;
    }

    public void setBuilding(BuildingDto building) {
        this.building = building;
    }

    public List<FloorLayoutDto> getLayouts() {
        return layouts;
    }

    public void setLayouts(List<FloorLayoutDto> layouts) {
        this.layouts = layouts;
    }

    public List<LectureRoomDto> getLectureRooms() {
        return lectureRooms;
    }

    public void setLectureRooms(List<LectureRoomDto> lectureRooms) {
        this.lectureRooms = lectureRooms;
    }

    @Override
    public String toString() {
        return "BuildingFloorDto{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", buildingSection=" + buildingSection +
                ", building=" + building +
                ", layouts=" + layouts +
                ", lectureRooms=" + lectureRooms +
                '}';
    }
}
