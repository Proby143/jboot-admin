package io.jboot.admin.service.provider;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.Jboot;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.util.IDUtils;
import io.jboot.admin.service.api.UserCommentService;
import io.jboot.admin.service.entity.model.ReadRecord;
import io.jboot.admin.service.entity.model.UserComment;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class UserCommentServiceImpl extends JbootServiceBase<UserComment> implements UserCommentService {


    @Override
    public Record selectIndexComments(String userId, String type, String city) {
        String select = "select a.user_comment_id commentId,a.comment_url url,a.comment_content content,a.created,b.user_name name,b.front_pic frontPic,c.shop_name shopName,a.user_id userId,a.age ";
        String from = "from user_comment a LEFT JOIN user b on a.user_id = b.user_id left join shop c on a.shop_id = c.shop_id left join user_login d on a.user_id = d.user_id and d.state = 0";
        String where = "";
        if(UserComment.COMMENT_TYPE_IMPORTANT.equals(type)){
            where = "where a.type = "+type+" and a.state = 1 and b.city = '"+city +"' and not exists (select 1 from read_record rr where rr.user_id = "+userId+" and rr.user_comment_id = a.user_comment_id)";
        }else if(UserComment.COMMENT_TYPE_All.equals(type)){
            where = "where a.type = "+type+" and a.state = 1 and b.sex != (select sex from user where user_id = "+userId+") and b.city ='"+city+"' and not exists (select 1 from read_record rr where rr.user_id = "+userId+" and rr.user_comment_id = a.user_comment_id)";
        }
        String orderBy = " order by a.created desc,a.type desc,a.vip_cost desc";
        List<Record> records = Db.find(select+from+where+orderBy);
        if(records.size()>0){
            ReadRecord readRecord = new ReadRecord();
            readRecord.setUserCommentId(records.get(0).get("commentId"));
            readRecord.setUserId(userId);
            if(new ReadRecordServiceImpl().save(readRecord)){
                if(records.size()>1){
                    records.get(0).set("isHaveNext","1");
                }else{
                    records.get(0).set("isHaveNext","0");
                }
                return records.get(0);
            }
        }
        return null;
    }

    @Override
    public boolean insertComment(String content, String url, String userId, String shopId, String type, String age, String reciveId, String cost, String code) {
        String msg = Jboot.me().getCache().get("sendMsg:",code);
        if(msg!=null&&"2".equals(type)){
            return false;
        }
        UserComment comment = new UserComment();
        comment.setCommentContent(content);
        comment.setCommentUrl(url);
        comment.setShopId(shopId);
        comment.setUserId(userId);
        comment.setType(Integer.parseInt(type));
        comment.setCreated(new Date());
        comment.setUserCommentId(IDUtils.genUserId());
        comment.setAge(Integer.parseInt(age));
        comment.setVipCost(new BigDecimal(cost));
        comment.setState(1);
        if(!"0".equals(reciveId)){
            comment.setReciveId(reciveId);
        }
        if("2".equals(type)){
            Jboot.me().getCache().put("sendMsg:",code, JSON.toJSON(comment).toString(),3600);
        }
        return comment.save();
    }

    @Override
    public Record selectBeforeComments(String userId) {
        String select = "select a.user_comment_id commentId,a.comment_url url,a.comment_content content,a.created,b.user_name name,b.front_pic frontPic,c.shop_name shopName,a.user_id userId,a.age ";
        String from = "from user_comment a LEFT JOIN user b on a.user_id = b.user_id left join shop c on a.shop_id = c.shop_id left join user_login d on a.user_id = d.user_id and d.state = 0 ";
        String where = "where a.state = 1 and type = 2 and recive_id = "+userId;
        String orderBy = " order by a.vip_cost desc,a.created desc";
        List<Record> records = Db.find(select+from+where+orderBy);
        if(records.size()>0){
            Integer updateRows = Db.update("update user_comment set state = 0 where user_comment_id = "+records.get(0).get("commentId"));
            if(updateRows>0){
                if(records.size()>1){
                    records.get(0).set("isHaveNext","1");
                }else{
                    records.get(0).set("isHaveNext","0");
                }
                return records.get(0);
            }
        }
        return null;
    }
    @Override
    public Page<UserComment> findPage(UserComment userComment, int pageNumber, int pageSize) {


        Columns columns = Columns.create();

//        if (StrKit.notBlank(userComment.get("userName")+"")) {
//            columns.like("user_name", "%"+userComment.get("userName")+"%");
//        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}