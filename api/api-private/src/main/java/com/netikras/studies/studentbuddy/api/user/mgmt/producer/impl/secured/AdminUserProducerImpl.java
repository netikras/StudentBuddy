package com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.USER;

@Component
public class AdminUserProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private UserService userService;


    @Authorizable(resource = USER, action = PURGE)
    public void purgeUser(String id) {
        userService.purgeUser(id);
    }

    @Authorizable(resource = USER, action = Action.CREATE)
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.apply(new User(), userDto, new MappingSettings().setForceUpdate(true));
        if (user != null) user.setId(null);
        user = userService.createUser(user);
        userDto = modelMapper.transform(user, new UserDto());

        return userDto;
    }

    @Authorizable(resource = USER, action = Action.DELETE)
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }
}
