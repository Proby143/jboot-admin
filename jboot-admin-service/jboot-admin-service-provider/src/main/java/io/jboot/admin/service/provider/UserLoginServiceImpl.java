package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.UserLoginService;
import io.jboot.admin.service.entity.model.UserLogin;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class UserLoginServiceImpl extends JbootServiceBase<UserLogin> implements UserLoginService {

}