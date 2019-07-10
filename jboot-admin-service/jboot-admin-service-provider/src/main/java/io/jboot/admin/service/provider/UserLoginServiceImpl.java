package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.Jboot;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.util.IDUtils;
import io.jboot.admin.service.api.UserLoginService;
import io.jboot.admin.service.entity.model.ReadRecord;
import io.jboot.admin.service.entity.model.UserLogin;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.Date;

@Bean
@Singleton
@JbootrpcService
public class UserLoginServiceImpl extends JbootServiceBase<UserLogin> implements UserLoginService {
    @Override
    public String login(String relationId, String city, String code) {
        Record record = Db.findFirst("select user_id userId from user_login where relation_id = '"+relationId+"'");
        if(null==record){
            String userId = IDUtils.genUserId();
            UserLogin userLogin = new UserLogin();
            userLogin.setUserId(userId);
            userLogin.setUserLoginId(IDUtils.generate());
            userLogin.setLastLoginTime(new Date());
            userLogin.setCode(code);
            userLogin.save();
            userLogin.setRelationId(relationId);
            Jboot.me().getCache().put("teacityLoginCode:",""+code,code,1800);
            return userId;
        }else{
            String userId = record.get("userId");
            Db.update("update user_login set last_login_time = "+new Date()+",code = "+code+" where user_id = '" + userId+"'");
            Jboot.me().getCache().put("teacityLoginCode:",code,code,1800);
            return userId;
        }
    }
    @Override
    public Page<UserLogin> findPage(UserLogin userLogin, int pageNumber, int pageSize) {

        Columns columns = Columns.create();

        if (StrKit.notBlank(userLogin.get("userName")+"")) {
            columns.like("user_name", "%"+userLogin.get("userName")+"%");
        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}