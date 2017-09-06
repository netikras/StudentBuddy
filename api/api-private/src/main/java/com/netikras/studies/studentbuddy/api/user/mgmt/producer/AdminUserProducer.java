package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminUserApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Resource.USER;

@RestController
public class AdminUserProducer extends AdminUserApiProducer {

    @Resource
    private UserService userService;


    @Override
    @Authorizable(resource = USER, action = Action.CREATE)
    protected UserDto onCreateUserDto(UserDto userDto) {
        User user = ModelMapper.apply(new User(), userDto, new MappingSettings().setForceUpdate(true));
        if (user != null) user.setId(null);
        user = userService.createUser(user);
        userDto = ModelMapper.transform(user, new UserDto());

        return userDto;
    }

    @Override
    @Authorizable(resource = USER, action = Action.DELETE)
    protected void onDeleteUserDto(String id) {
        userService.deleteUser(id);
    }
}
