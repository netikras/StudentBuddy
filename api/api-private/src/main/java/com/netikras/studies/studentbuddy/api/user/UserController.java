package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.api.filters.ThreadContext;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.meta.dto.UserDto;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import com.netikras.tools.common.model.mapper.ModelMapper;
import com.netikras.tools.common.remote.AuthenticationDetail;
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
            value = "/login",
            method = RequestMethod.POST
    )
    @ResponseBody
    public UserDto login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestBody AuthenticationDetail auth
    ) {

        if (auth == null) {
            auth = new AuthenticationDetail();
            auth.setUsername(username);
            auth.setPassword(password);
        }

        User user = userService.login(auth);
        UserDto dto = ModelMapper.transform(user, new UserDto());

        if (user == null) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Login failure")
                    ;
        }

        ThreadContext.current().setNewUser(user);
        return dto;
    }

    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST
    )
    public void logout() {
        ThreadContext.current().removeUser();
    }

    @RequestMapping(
            value = "/new",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = USER, action = Action.CREATE)
    public UserDto createUser(
            @RequestBody UserDto userDto
    ) {
        User user = ModelMapper.apply(new User(), userDto);
        user = userService.createUser(user);
        userDto = ModelMapper.transform(user, new UserDto());

        return userDto;
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = USER, action = Action.DELETE)
    public void deleteUser(
            @PathVariable(name = "id") String userId
    ) {
        userService.deleteUser(userId);
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.PUT
    )
    @Authorizable(resource = USER, action = Action.MODIFY)
    public UserDto updateUser(
            @PathVariable(name = "id") String userId,
            @RequestBody UserDto userDto
    ) {
        userDto.setId(userId);
        User user = ModelMapper.apply(new User(), userDto);

        user = userService.updateUser(user);

        userDto = ModelMapper.transform(user, new UserDto());

        return userDto;
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.PATCH
    )
    @Authorizable(resource = USER, action = Action.MODIFY)
    public void changePassword(
            @PathVariable(name = "id") String userId,
            @RequestParam(name = "password") String password
    ) {
        userService.changePassword(userId, password);
    }

}
