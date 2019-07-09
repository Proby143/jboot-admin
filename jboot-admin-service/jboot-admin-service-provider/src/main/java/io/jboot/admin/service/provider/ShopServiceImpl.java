package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.ShopService;
import io.jboot.admin.service.entity.model.Shop;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class ShopServiceImpl extends JbootServiceBase<Shop> implements ShopService {

}