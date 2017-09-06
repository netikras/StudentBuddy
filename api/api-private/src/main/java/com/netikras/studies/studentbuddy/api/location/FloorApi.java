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

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/floor", cruds = {
        @GenerateCrud(dtoType = BuildingFloorDto.class),
        @GenerateCrud(dtoType = FloorLayoutDto.class, url = "/layout"),
        @GenerateCrud(dtoType = LectureRoomDto.class, url = "/room")
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class FloorApi {


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
