package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.AdService;
import io.jboot.admin.service.entity.model.Ad;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class AdServiceImpl extends JbootServiceBase<Ad> implements AdService {

}