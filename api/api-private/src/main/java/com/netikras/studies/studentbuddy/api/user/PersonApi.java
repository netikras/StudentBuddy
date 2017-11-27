package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.CrudMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/person")
@GenerateCrud(dtoType = PersonDto.class, methods = {CrudMethod.RETRIEVE})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class PersonApi {

    @RestEndpoint(url = "/code/{code}", method = HttpMethod.GET)
    public abstract PersonDto getByCode(@MethodParam(type = Type.URL, name = "code") String id);

    @RestEndpoint(url = "/id2/{identifier}", method = HttpMethod.GET)
    public abstract PersonDto getByIdentifier(@MethodParam(type = Type.URL, name = "identifier") String id);

    @RestEndpoint(url = "/all", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> getAll();

    @RestEndpoint(url = "/all/fname/{firstName}", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> getAllByFirstName(@MethodParam(type = Type.URL, name = "firstName") String firstName);

    @RestEndpoint(url = "/all/lname/{lastName}", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> getAllByLastName(@MethodParam(type = Type.URL, name = "lastName") String lastName);

    @RestEndpoint(url = "/all/fname/{firstName}/lname/{lastName}", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> getAllByFirstAndLastName(
            @MethodParam(type = Type.URL, name = "firstName") String firstName,
            @MethodParam(type = Type.URL, name = "lastName") String lastName

    );

    @RestEndpoint(url = "/search/fname/{fname}", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> searchAllByFirstName(@MethodParam(type = Type.URL, name = "fname") String fnameSubstring);

    @RestEndpoint(url = "/search/lname/{lname}", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> searchAllByLastName(@MethodParam(type = Type.URL, name = "lname") String lnameSubstring);

    @RestEndpoint(url = "/search/code/{code}", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> searchAllByPersonalCode(@MethodParam(type = Type.URL, name = "code") String codeSubstring);

    @RestEndpoint(url = "/search/id2/{identifier}", method = HttpMethod.GET, dtoType = PersonDto.class)
    public abstract List<PersonDto> searchAllByIdentifier(@MethodParam(type = Type.URL, name = "identifier") String id);



}
