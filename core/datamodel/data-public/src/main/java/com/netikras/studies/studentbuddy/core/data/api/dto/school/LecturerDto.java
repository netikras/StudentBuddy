package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;

import java.util.ArrayList;
import java.util.List;

public class LecturerDto extends AbstractDto {

    private String id;
    private PersonDto person;
    private String degree;
    private List<DisciplineDto> disciplines;
    private List<CourseDto> courses;
    private SchoolDto school;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }

    public List<DisciplineDto> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineDto> disciplines) {
        this.disciplines = disciplines;
    }

    public List<DisciplineDto> addDiscipline(DisciplineDto disciplineDto) {
        List<DisciplineDto> dtos = getDisciplines();
        if (dtos == null) {
            dtos = new ArrayList<>();
            setDisciplines(dtos);
        }
        dtos.add(disciplineDto);
        return dtos;
    }

    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDto> courses) {
        this.courses = courses;
    }

    public List<CourseDto> addDiscipline(CourseDto courseDto) {
        List<CourseDto> dtos = getCourses();
        if (dtos == null) {
            dtos = new ArrayList<>();
            setCourses(dtos);
        }
        dtos.add(courseDto);
        return dtos;
    }

    @Override
    public String toString() {
        return "LecturerDto{" +
                "id='" + id + '\'' +
                ", person=" + person +
                ", degree='" + degree + '\'' +
                ", disciplines=" + disciplines +
                ", courses=" + courses +
                ", school=" + school +
                '}';
    }
}
