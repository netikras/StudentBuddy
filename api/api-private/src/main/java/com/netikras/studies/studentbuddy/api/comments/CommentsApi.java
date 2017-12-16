package com.netikras.studies.studentbuddy.api.comments;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/comment", cruds = {
        @GenerateCrud(dtoType = CommentDto.class)
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class CommentsApi {

    @RestEndpoint(url = "/type/{typeName}/id/{typeId}", method = HttpMethod.GET, dtoType = CommentDto.class)
    public abstract List<CommentDto> getForType(
            @MethodParam(type = Type.URL, name = "typeName") String typeName,
            @MethodParam(type = Type.URL, name = "typeId") String typeId
    );

    @RestEndpoint(url = "/type/{typeName}", method = HttpMethod.GET, dtoType = CommentDto.class)
    public abstract List<CommentDto> getAllForType(
            @MethodParam(type = Type.URL, name = "typeName") String typeName
    );

    @RestEndpoint(url = "/type/{typeName}/id/{typeId}", method = HttpMethod.DELETE, dtoType = CommentDto.class)
    public abstract void deleteForType(
            @MethodParam(type = Type.URL, name = "typeName") String typeName,
            @MethodParam(type = Type.URL, name = "typeId") String typeId
    );

    @RestEndpoint(url = "/type/{typeName}", method = HttpMethod.DELETE, dtoType = CommentDto.class)
    public abstract void deleteAllForType(
            @MethodParam(type = Type.URL, name = "typeName") String typeName
    );

    @RestEndpoint(url = "/type/{typeName}/id/{typeId}", method = HttpMethod.POST, dtoType = CommentDto.class)
    public abstract CommentDto createNewForType(
            @MethodParam(type = Type.URL, name = "typeName") String typeName,
            @MethodParam(type = Type.URL, name = "typeId") String typeId,
            @MethodParam(type = Type.BODY, required = true) CommentDto commentDto
    );

    @RestEndpoint(url = "/tag/value/{value}", method = HttpMethod.GET, dtoType = CommentDto.class)
    public abstract List<CommentDto> getByTagValue(
            @MethodParam(type = Type.URL, name = "value") String value,
            @MethodParam(type = Type.REQUEST, name = "pageno", required = false) Long pageNumber,
            @MethodParam(type = Type.REQUEST, name = "pagesz", required = false) Long pageSize
    );

    @RestEndpoint(url = "/tag/id/{id}", method = HttpMethod.GET, dtoType = CommentDto.class)
    public abstract List<CommentDto> getByTagId(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.REQUEST, name = "pageno", required = false) Long pageNumber,
            @MethodParam(type = Type.REQUEST, name = "pagesz", required = false) Long pageSize
    );

    @RestEndpoint(url = "/author/id/{id}", method = HttpMethod.GET, dtoType = CommentDto.class)
    public abstract List<CommentDto> getAllByPerson(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/author/id/{id}", method = HttpMethod.DELETE, dtoType = CommentDto.class)
    public abstract void deleteAllByPerson(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/entities/updated/after/{after}/ids/{idsCsv}", method = HttpMethod.GET, dtoType = CommentDto.class)
    public abstract List<CommentDto> getEntitiesUpdatedAfter(
            @MethodParam(type = Type.URL, name = "idsCsv") String idsCsv,
            @MethodParam(type = Type.URL, name = "after") long after
    );


}
