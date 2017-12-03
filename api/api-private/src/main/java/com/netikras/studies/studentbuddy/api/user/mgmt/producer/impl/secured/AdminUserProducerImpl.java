package com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.service.PermissionsService;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ROLE_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.USER;

@Component
public class AdminUserProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private UserService userService;

    @Resource
    private PermissionsService permissionsService;
    @Resource
    private DtoMapper dtoMapper;


    @Authorizable(resource = USER, action = PURGE)
    public void purgeUser(String id) {
        userService.purgeUser(id);
    }

    @Authorizable(resource = USER, action = Action.CREATE)
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.apply(new User(), userDto, new MappingSettings().setForceUpdate(true));
        if (user != null) user.setId(null);
        user = userService.createUser(user);
        UserDto dto = (UserDto) dtoMapper.toDto(user);
        return dto;
    }

    @Authorizable(resource = USER, action = Action.DELETE)
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = MODIFY)
    @Transactional
    public UserDto assignUserRoleByName(String id, String rolename) {
        permissionsService.assignUserRoles(id, Arrays.asList(rolename));
        User user = userService.findUser(id);
        UserDto dto = (UserDto) dtoMapper.toDto(user);
        return dto;
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = MODIFY)
    @Transactional
    public UserDto unassignUserRoleByName(String id, String rolename) {
        permissionsService.removeUserRole(id, rolename);
        User user = userService.findUser(id);
        UserDto dto = (UserDto) dtoMapper.toDto(user);
        return dto;
    }
}
