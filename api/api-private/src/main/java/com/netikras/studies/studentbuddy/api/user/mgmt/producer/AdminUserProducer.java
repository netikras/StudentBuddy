package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminUserApiProducer;
import com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured.AdminUserProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.USER;

@RestController
public class AdminUserProducer extends AdminUserApiProducer {

    @Resource
    private AdminUserProducerImpl impl;


    @Override
    protected void onPurgeUserDto(String id) {
        impl.purgeUser(id);
    }

    @Override
    protected UserDto onCreateUserDto(UserDto userDto) {
        return impl.createUser(userDto);
    }

    @Override
    protected void onDeleteUserDto(String id) {
        impl.deleteUser(id);
    }
}
