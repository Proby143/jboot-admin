package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.CodeService;
import io.jboot.admin.service.entity.model.Code;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class CodeServiceImpl extends JbootServiceBase<Code> implements CodeService {

}