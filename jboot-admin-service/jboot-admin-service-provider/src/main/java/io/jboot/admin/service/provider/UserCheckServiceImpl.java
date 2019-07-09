package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.UserCheckService;
import io.jboot.admin.service.entity.model.UserCheck;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class UserCheckServiceImpl extends JbootServiceBase<UserCheck> implements UserCheckService {

}