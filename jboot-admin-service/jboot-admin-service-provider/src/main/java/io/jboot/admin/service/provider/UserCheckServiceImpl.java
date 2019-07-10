package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.service.api.UserCheckService;
import io.jboot.admin.service.entity.model.ReadRecord;
import io.jboot.admin.service.entity.model.UserCheck;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class UserCheckServiceImpl extends JbootServiceBase<UserCheck> implements UserCheckService {
    @Override
    public Page<UserCheck> findPage(UserCheck userCheck, int pageNumber, int pageSize) {

        Columns columns = Columns.create();

//        if (StrKit.notBlank(userCheck.get("userName")+"")) {
//            columns.like("user_name", "%"+userCheck.get("userName")+"%");
//        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}