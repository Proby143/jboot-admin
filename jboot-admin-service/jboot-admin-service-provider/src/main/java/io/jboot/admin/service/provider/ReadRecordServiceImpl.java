package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.ReadRecordService;
import io.jboot.admin.service.entity.model.ReadRecord;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class ReadRecordServiceImpl extends JbootServiceBase<ReadRecord> implements ReadRecordService {

}