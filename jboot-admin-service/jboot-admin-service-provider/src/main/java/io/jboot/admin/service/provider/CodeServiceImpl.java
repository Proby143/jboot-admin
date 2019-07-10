package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.service.api.CodeService;
import io.jboot.admin.service.entity.model.Code;
import io.jboot.admin.service.entity.model.ReadRecord;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class CodeServiceImpl extends JbootServiceBase<Code> implements CodeService {
    @Override
    public boolean isUsed(String code) {
        Integer rows = Db.queryInt("select count(1) from code where state = 0 and code = "+code);
        if(rows>0){
            return true;
        }
        return false;
    }

    @Override
    public void userCode(String userId, String code) {
        Db.update("update code set state = 1,user_id = "+userId+" where code = "+code);
    }
    @Override
    public Page<Code> findPage(Code code, int pageNumber, int pageSize) {

        Columns columns = Columns.create();

//        if (StrKit.notBlank(code.get("userName")+"")) {
//            columns.like("user_name", "%"+code.get("userName")+"%");
//        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}