package com.netikras.studies.studentbuddy.api.timetable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointCreateLecture;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointDeleteById;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByGroupIdStartingBetween;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByGroupIdStartingIn;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByLecturerIdStartingBetween;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByLecturerIdStartingIn;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByRoomIdStartingBetween;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByRoomIdStartingIn;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByStudentIdStartingBetween;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointGetByStudentIdStartingIn;
import static com.netikras.studies.studentbuddy.api.constants.LecturesConstants.endpointUpdateLecture;

public class LecturesConsumer extends GenericRestConsumer {

    enum TimeVal {
        MONTHS("M"),
        DAYS("d"),
        HOURS("H"),
        MINUTES("m"),
        SECONDS("s");

        private final String val;

        TimeVal(String val) {
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }

    private TypeReference lecturesListTypeRef = new TypeReference<List<LectureDto>>() {};

    public LectureDto create(LectureDto lectureDto) {
        HttpRequest<LectureDto> request = createRequest(endpointCreateLecture())
                .withExpectedType(LectureDto.class)
                .setObject(lectureDto);

        LectureDto dto = (LectureDto) sendRequest(request);
        return dto;
    }

    public LectureDto update(LectureDto lectureDto) {
        HttpRequest<LectureDto> request = createRequest(endpointUpdateLecture())
                .withExpectedType(LectureDto.class)
                .setObject(lectureDto);

        LectureDto dto = (LectureDto) sendRequest(request);
        return dto;
    }

    public LectureDto getById(String id) {
        HttpRequest request = createRequest(endpointGetById())
                .withExpectedType(LectureDto.class)
                .setUrlProperty("id", id);

        LectureDto dto = (LectureDto) sendRequest(request);
        return dto;
    }

    public boolean deleteById(String id) {
        HttpRequest request = createRequest(endpointDeleteById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }


    public List<LectureDto> getAllByGroupIdStartingIn(String groupId, TimeVal timeVal, long timeValue) {
        HttpRequest request = createRequest(endpointGetByGroupIdStartingIn())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("timeUnits", timeVal.getVal())
                .setUrlProperty("value", timeValue)
                .setUrlProperty("groupId", groupId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }

    public List<LectureDto> getAllByStudentIdStartingIn(String studentId, TimeVal timeVal, long timeValue) {
        HttpRequest request = createRequest(endpointGetByStudentIdStartingIn())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("timeUnits", timeVal.getVal())
                .setUrlProperty("value", timeValue)
                .setUrlProperty("studentId", studentId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }

    public List<LectureDto> getAllByLecturerIdStartingIn(String lecturerId, TimeVal timeVal, long timeValue) {
        HttpRequest request = createRequest(endpointGetByLecturerIdStartingIn())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("timeUnits", timeVal.getVal())
                .setUrlProperty("value", timeValue)
                .setUrlProperty("lecturerId", lecturerId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }

    public List<LectureDto> getAllByRoomIdStartingIn(String roomId, TimeVal timeVal, long timeValue) {
        HttpRequest request = createRequest(endpointGetByRoomIdStartingIn())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("timeUnits", timeVal.getVal())
                .setUrlProperty("value", timeValue)
                .setUrlProperty("roomId", roomId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }


    // starting between

    public List<LectureDto> getAllByGroupIdStartingBetween(String groupId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetByGroupIdStartingBetween())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp)
                .setUrlProperty("groupId", groupId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }

    public List<LectureDto> getAllByStudentIdStartingBetween(String studentId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetByStudentIdStartingBetween())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp)
                .setUrlProperty("studentId", studentId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }

    public List<LectureDto> getAllByLecturerIdStartingBetween(String lecturerId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetByLecturerIdStartingBetween())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp)
                .setUrlProperty("lecturerId", lecturerId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }

    public List<LectureDto> getAllByRoomIdStartingBetween(String roomId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetByRoomIdStartingBetween())
                .withTypeReference(lecturesListTypeRef)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp)
                .setUrlProperty("roomId", roomId);

        List<LectureDto> dtos = (List<LectureDto>) sendRequest(request);
        return dtos;
    }
}
