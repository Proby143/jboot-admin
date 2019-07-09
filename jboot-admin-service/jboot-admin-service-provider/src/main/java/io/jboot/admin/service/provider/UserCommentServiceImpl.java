package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.UserCommentService;
import io.jboot.admin.service.entity.model.UserComment;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class UserCommentServiceImpl extends JbootServiceBase<UserComment> implements UserCommentService {

}