package io.jboot.admin.service.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import io.jboot.Jboot;
import io.jboot.admin.base.util.Msg;
import io.jboot.admin.service.api.UserService;
import io.jboot.admin.service.entity.model.User;
import io.jboot.admin.service.entity.model.UserCheck;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.service.JbootServiceBase;
import org.apache.commons.lang.StringUtils;

import javax.inject.Singleton;
import java.util.Date;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class UserServiceImpl extends JbootServiceBase<User> implements UserService {
    @Override
    public boolean updateUserInfo(String userId, String name, String age, String sex, String occupation) {
        User user = new User();
        user.setAge(Integer.parseInt(age));
        user.setCreatred(new Date());
        user.setOccupation(occupation);
        user.setUserName(name);
        user.setUserId(userId);
        user.setSex(sex);
        return user.saveOrUpdate();
    }

    @Override
    public boolean updateDesc(String userId, String desc) {
        User user = new User();
        user.setUserId(userId);
        user.setDesc(desc);
        return user.saveOrUpdate();
    }

    @Override
    public boolean updateCity(String userId, String city) {
        User user = new User();
        user.setUserId(userId);
        user.setCity(city);
        return user.saveOrUpdate();
    }

    @Override
    @Before(Tx.class)
    public String updateLogin(String relationId, String city, String code) {
        String userId = new UserLoginServiceImpl().login(relationId,city,code);
        new CodeServiceImpl().userCode(userId,code);
        User user = new User();
        user.setUserId(userId);
        user.setCity(city);
        user.saveOrUpdate();
        return userId;
    }

    @Override
    public Record selectUser(String userId) {
        Record record = Db.findFirst("select a.user_name name,a.age,a.sex,a.occupation,a.front_pic frontPic,a.desc,b.code from user a left join user_login b on a.user_id = b.user_id where a.user_id = '"+userId+"'");
        return record;
    }

    @Override
    public List<Record> selectFootprint(String city, String userId, String sex) {
        String sql = "select a.last_login_time lastLoginTime,b.user_name name,b.front_pic frontPic,a.user_id userId";
        String from = " from user_login a left join user b on a.user_id = b.user_id";
        if("0".equals(city)){
            city = DAO.findById(userId).getCity();
        }
        String where = " where b.city='"+city+"' and b.user_id!='"+userId+"'";
        String orderBy = " order by a.last_login_time desc";
        return Db.find(sql+from+where+orderBy);
    }

    @Override
    public boolean reportUser(String userId, String reporter, String commentId) {
        UserCheck userCheck = new UserCheck();
        userCheck.setUserId(userId);
        userCheck.setUserCommentId(commentId);
        userCheck.setReporterId(reporter);
        userCheck.setCreated(new Date());
        return new UserCheckServiceImpl().save(userCheck);
    }

    @Override
    public List<Record> selectChatRecivers(List<String> stringList,String userId) {
        StringBuffer in = new StringBuffer("(");
        for (int i = stringList.size()-1;i>=0;i--){
            if(i==0){
                in.append("'"+StringUtils.substringAfterLast(stringList.get(i),"@") +"')");
            }else{
                in.append("'"+StringUtils.substringAfterLast(stringList.get(i),"@")+"',");
            }
        }
        String sql = "select user_id userId,user_name name,front_pic frontPic from user where user_id in "+in;
        List<Record> records = Db.find(sql);
        for (Record rec:records) {
            JSONArray jsonArray  = Jboot.me().getCache().get("chatRecord:"+userId,"@"+rec.get("userId"));
            Msg msg = JSON.parseObject((String) jsonArray.get(jsonArray.size()-1),Msg.class);
            rec.set("lastMsg",msg.getMsg());
        }
        return records;
    }

    @Override
    public String selectUserState(String userId) {
        String state = Db.queryStr("select state from user_login where user_id = '"+userId+"'");
        return state;
    }
}