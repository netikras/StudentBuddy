package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.api.constants.UserConstants;
import com.netikras.studies.studentbuddy.api.filters.ThreadContext;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Resource.USER;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;


    @RequestMapping(
            value = UserConstants.USER_URL_LOGIN,
            method = RequestMethod.POST
    )
    @ResponseBody
    public UserDto login(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "password", required = false) String password,
            @RequestBody(required = false) AuthenticationDetail auth
    ) {

        if (auth == null || auth.getUsername() == null || auth.getUsername().isEmpty()) {
            auth = new AuthenticationDetail();
            auth.setUsername(username);
            auth.setPassword(password);
        }

        User user = userService.login(auth);
        UserDto dto = ModelMapper.transform(user, new UserDto());

        if (user == null) {
            throw new StudBudUncheckedException()
                    .setMessage1("Login failure")
                    .setMessage2("Incorrect login information")
                    .setProbableCause(username)
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    ;
        }

        ThreadContext.current().setNewUser(user);
        return dto;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_LOGOUT,
            method = RequestMethod.POST
    )
    public void logout() {
        ThreadContext.current().removeUser();
    }


    @RequestMapping(
            value = UserConstants.USER_URL_UPDATE_BY_ID,
            method = RequestMethod.PUT
    )
    @Authorizable(resource = USER, action = Action.MODIFY)
    public UserDto updateUser(
            @PathVariable(name = "id") String userId,
            @RequestBody UserDto userDto
    ) {
        userDto.setId(userId);
        User user = ModelMapper.apply(new User(), userDto);
        user.setId(userId);

        user = userService.updateUser(user);

        userDto = ModelMapper.transform(user, new UserDto());

        return userDto;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_CHANGE_PASSWORD,
            method = RequestMethod.PUT
    )
    @Authorizable(resource = USER, action = Action.MODIFY)
    public void changePassword(
            @PathVariable(name = "id") String userId,
            @RequestParam(name = "password") String password
    ) {
        userService.changePassword(userId, password);
    }

    @RequestMapping(
            value = UserConstants.USER_URL_GET_BY_ID,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.GET)
    public UserDto getUser(
            @PathVariable(name = "id") String userId
    ) {
        UserDto dto;
        User user = userService.findUser(userId);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_GET_BY_NAME,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.GET)
    public UserDto getUserByName(
            @PathVariable(name = "name") String name
    ) {
        UserDto dto;
        User user = userService.findUserByName(name);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }


    @RequestMapping(
            value = UserConstants.USER_URL_GET_USER_BY_PERSON_ID,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.GET)
    public UserDto getUserByPerson(
            @PathVariable(name = "id") String userId
    ) {
        UserDto dto;
        User user = userService.findUserByPerson(userId);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }

}
