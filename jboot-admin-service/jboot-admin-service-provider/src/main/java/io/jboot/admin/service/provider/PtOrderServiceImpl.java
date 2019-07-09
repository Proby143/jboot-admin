package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.PtOrderService;
import io.jboot.admin.service.entity.model.PtOrder;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class PtOrderServiceImpl extends JbootServiceBase<PtOrder> implements PtOrderService {

}