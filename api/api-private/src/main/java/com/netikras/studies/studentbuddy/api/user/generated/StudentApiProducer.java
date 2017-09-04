package com.netikras.studies.studentbuddy.api.user.generated;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.api.user.generated.StudentApiConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.lang.String;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;


/**
 * This class is generated automatically with Rest API preprocessor (netikras' commons).<br/>
 * It is not recommended to modify this file as it will be overwritten on next compile.<br/>
 * In case one needs to modify this class either modify its template or override its methods.
 */

@RequestMapping(value = StudentApiConstants.BASE_URL)
public class StudentApiProducer {

    @RequestMapping(value = StudentApiConstants.URL_STUDENT_DTO_RETRIEVE, method = RequestMethod.GET)
    @ResponseBody
    public StudentDto retrieveStudentDto(
                        @PathVariable(value = "id") String id) {
        StudentDto result = onRetrieveStudentDto(id);
        return result;
    }

    protected StudentDto onRetrieveStudentDto(String id) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENT_DTO_UPDATE, method = RequestMethod.PUT)
    @ResponseBody
    public StudentDto updateStudentDto(
                        @RequestBody(required = true) StudentDto item) {
        StudentDto result = onUpdateStudentDto(item);
        return result;
    }

    protected StudentDto onUpdateStudentDto(StudentDto item) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENTS_GROUP_DTO_RETRIEVE, method = RequestMethod.GET)
    @ResponseBody
    public StudentsGroupDto retrieveStudentsGroupDto(
                        @PathVariable(value = "id") String id) {
        StudentsGroupDto result = onRetrieveStudentsGroupDto(id);
        return result;
    }

    protected StudentsGroupDto onRetrieveStudentsGroupDto(String id) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_LECTURE_GUEST_DTO_RETRIEVE, method = RequestMethod.GET)
    @ResponseBody
    public LectureGuestDto retrieveLectureGuestDto(
                        @PathVariable(value = "id") String id) {
        LectureGuestDto result = onRetrieveLectureGuestDto(id);
        return result;
    }

    protected LectureGuestDto onRetrieveLectureGuestDto(String id) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_LECTURE_GUEST_DTO_UPDATE, method = RequestMethod.PUT)
    @ResponseBody
    public LectureGuestDto updateLectureGuestDto(
                        @RequestBody(required = true) LectureGuestDto item) {
        LectureGuestDto result = onUpdateLectureGuestDto(item);
        return result;
    }

    protected LectureGuestDto onUpdateLectureGuestDto(LectureGuestDto item) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENT_DTO_GET_BY_PERSON_ID, method = RequestMethod.GET)
    @ResponseBody
    public StudentDto getByPersonIdStudentDto(
                        @PathVariable(value = "id") String id) {
        StudentDto result = onGetByPersonIdStudentDto(id);
        return result;
    }

    protected StudentDto onGetByPersonIdStudentDto(String id) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENTS_GROUP_DTO_GET_BY_TITLE, method = RequestMethod.GET)
    @ResponseBody
    public StudentsGroupDto getByTitleStudentsGroupDto(
                        @PathVariable(value = "title") String title) {
        StudentsGroupDto result = onGetByTitleStudentsGroupDto(title);
        return result;
    }

    protected StudentsGroupDto onGetByTitleStudentsGroupDto(String title) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENTS_GROUP_DTO_GET_ALL, method = RequestMethod.GET)
    @ResponseBody
    public List<StudentsGroupDto> getAllStudentsGroupDto() {
        List result = onGetAllStudentsGroupDto();
        return result;
    }

    protected List<StudentsGroupDto> onGetAllStudentsGroupDto() {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENT_DTO_GET_ALL_BY_GROUP, method = RequestMethod.GET)
    @ResponseBody
    public List<StudentDto> getAllByGroupStudentDto(
                        @PathVariable(value = "id") String id) {
        List result = onGetAllByGroupStudentDto(id);
        return result;
    }

    protected List<StudentDto> onGetAllByGroupStudentDto(String id) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENT_DTO_SEARCH_ALL_BY_FIRST_NAME, method = RequestMethod.GET)
    @ResponseBody
    public List<StudentDto> searchAllByFirstNameStudentDto(
                        @PathVariable(value = "fname") String fname) {
        List result = onSearchAllByFirstNameStudentDto(fname);
        return result;
    }

    protected List<StudentDto> onSearchAllByFirstNameStudentDto(String fname) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENT_DTO_SEARCH_ALL_BY_LAST_NAME, method = RequestMethod.GET)
    @ResponseBody
    public List<StudentDto> searchAllByLastNameStudentDto(
                        @PathVariable(value = "lname") String lname) {
        List result = onSearchAllByLastNameStudentDto(lname);
        return result;
    }

    protected List<StudentDto> onSearchAllByLastNameStudentDto(String lname) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_STUDENTS_GROUP_DTO_SEARCH_ALL_BY_TITLE, method = RequestMethod.GET)
    @ResponseBody
    public List<StudentsGroupDto> searchAllByTitleStudentsGroupDto(
                        @PathVariable(value = "title") String title) {
        List result = onSearchAllByTitleStudentsGroupDto(title);
        return result;
    }

    protected List<StudentsGroupDto> onSearchAllByTitleStudentsGroupDto(String title) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_LECTURE_GUEST_DTO_SEARCH_BY_LAST_NAME, method = RequestMethod.GET)
    @ResponseBody
    public List<LectureGuestDto> searchByLastNameLectureGuestDto(
                        @PathVariable(value = "lname") String lname) {
        List result = onSearchByLastNameLectureGuestDto(lname);
        return result;
    }

    protected List<LectureGuestDto> onSearchByLastNameLectureGuestDto(String lname) {
        return null;
    }

    @RequestMapping(value = StudentApiConstants.URL_LECTURE_GUEST_DTO_SEARCH_BY_FIRST_NAME, method = RequestMethod.GET)
    @ResponseBody
    public List<LectureGuestDto> searchByFirstNameLectureGuestDto(
                        @PathVariable(value = "fname") String fname) {
        List result = onSearchByFirstNameLectureGuestDto(fname);
        return result;
    }

    protected List<LectureGuestDto> onSearchByFirstNameLectureGuestDto(String fname) {
        return null;
    }


}
