package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.api.constants.AdminUserConstants;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Resource.USER;

@RestController
@RequestMapping(value = AdminUserConstants.BASE_URL)
public class AdminUserController {

    @Resource
    private UserService userService;

    @RequestMapping(
            value = AdminUserConstants.ADM_USR_URL_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = USER, action = Action.CREATE)
    public UserDto createUser(
            @RequestBody UserDto userDto
    ) {
        User user = ModelMapper.apply(new User(), userDto, new MappingSettings().setForceUpdate(true));
        if (user != null) user.setId(null);
        user = userService.createUser(user);
        userDto = ModelMapper.transform(user, new UserDto());

        return userDto;
    }

    @RequestMapping(
            value = AdminUserConstants.ADM_USR_URL_DELETE_BY_ID,
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = USER, action = Action.DELETE)
    public void deleteUser(
            @PathVariable(name = "id") String userId
    ) {
        userService.deleteUser(userId);
    }


}
