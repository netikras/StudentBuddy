package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.GET_ALL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.PURGE;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/floor", cruds = {
        @GenerateCrud(dtoType = BuildingFloorDto.class, extend = {PURGE}),
        @GenerateCrud(dtoType = FloorLayoutDto.class, url = "/layout", extend = {PURGE}),
        @GenerateCrud(dtoType = LectureRoomDto.class, url = "/room", extend = {PURGE}),
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class FloorApi {


    @RestEndpoint(url = "/building/id/{id}", method = HttpMethod.GET, dtoType = BuildingFloorDto.class, action = "getAllByBuildingId")
    public abstract List<BuildingFloorDto> getAllFloorsByBuildingId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/building/section/id/{id}", method = HttpMethod.GET, dtoType = BuildingFloorDto.class, action = "getAllBySectionId")
    public abstract List<BuildingFloorDto> getAllFloorsByBuildingSectionId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/room/floor/id/{id}", method = HttpMethod.GET, dtoType = LectureRoomDto.class, action = "getAllByFloorId")
    public abstract List<LectureRoomDto> getAllRoomsByFloorId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/room/layout/id/{id}", method = HttpMethod.GET, dtoType = LectureRoomDto.class, action = "getAllByLayoutId")
    public abstract List<LectureRoomDto> getAllRoomsByLayoutId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/room/building/id/{id}", method = HttpMethod.GET, dtoType = LectureRoomDto.class, action = "getAllByBuildingId")
    public abstract List<LectureRoomDto> getAllRoomsByBuildingId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/room/building/section/id/{id}", method = HttpMethod.GET, dtoType = LectureRoomDto.class, action = "getAllBySectionId")
    public abstract List<LectureRoomDto> getAllRoomsBySectionId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/layout/building/id/{id}", method = HttpMethod.GET, dtoType = FloorLayoutDto.class, action = "getAllLayoutsByBuildingId")
    public abstract List<FloorLayoutDto> getAllLayoutsByBuildingId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/layout/floor/id/{id}", method = HttpMethod.GET, dtoType = FloorLayoutDto.class, action = "getAllLayoutsByFloorId")
    public abstract List<FloorLayoutDto> getAllLayoutsByFloorId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/layout/building/section/id/{id}", method = HttpMethod.GET, dtoType = FloorLayoutDto.class, action = "getAllLayoutsBySectionId")
    public abstract List<FloorLayoutDto> getAllLayoutsBySectionId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/title/{title}", method = HttpMethod.GET, dtoType = BuildingFloorDto.class, action = "searchAllByTitle")
    public abstract List<BuildingFloorDto> searchFloorsByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/room/title/{title}", method = HttpMethod.GET, dtoType = LectureRoomDto.class, action = "searchAllByTitle")
    public abstract List<LectureRoomDto> searchRoomsByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/room/number/{number}", method = HttpMethod.GET, dtoType = LectureRoomDto.class, action = "searchAllByNumber")
    public abstract List<LectureRoomDto> searchRoomsByNumber(
            @MethodParam(type = Type.URL, name = "number") String numberSubstring
    );



}
