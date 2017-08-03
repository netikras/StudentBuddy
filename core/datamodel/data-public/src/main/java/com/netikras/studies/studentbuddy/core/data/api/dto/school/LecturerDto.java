package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;

import java.util.List;

public class LecturerDto {

    private String id;
    private PersonDto person;
    private String degree;
    private List<DisciplineDto> disciplines;

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

    public List<DisciplineDto> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineDto> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public String toString() {
        return "LecturerDto{" +
                "id='" + id + '\'' +
                ", person=" + person +
                ", degree='" + degree + '\'' +
                ", disciplines=" + disciplines +
                '}';
    }
}
