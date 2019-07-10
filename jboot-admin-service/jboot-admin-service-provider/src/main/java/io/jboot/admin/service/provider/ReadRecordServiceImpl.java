package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.service.api.ReadRecordService;
import io.jboot.admin.service.entity.model.ReadRecord;
import io.jboot.admin.service.entity.model.User;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class ReadRecordServiceImpl extends JbootServiceBase<ReadRecord> implements ReadRecordService {

    @Override
    public Page<ReadRecord> findPage(ReadRecord readRecord, int pageNumber, int pageSize) {
//        Record record = new UserServiceImpl().selectUser(readRecord.getUserId());
//        if(readRecord==null){
//            throw new BusinessException("没有此用户");
//        }

        Columns columns = Columns.create();

//        if (StrKit.notBlank(record.get("userName")+"")) {
//            columns.like("user_name", "%"+record.get("userName")+"%");
//        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}