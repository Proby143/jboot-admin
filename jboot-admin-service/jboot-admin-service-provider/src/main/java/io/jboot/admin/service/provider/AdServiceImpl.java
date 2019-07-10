package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.service.entity.model.ReadRecord;
import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.AdService;
import io.jboot.admin.service.entity.model.Ad;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class AdServiceImpl extends JbootServiceBase<Ad> implements AdService {
    @Override
    public Page<Ad> findPage(Ad ad, int pageNumber, int pageSize) {

        Columns columns = Columns.create();

//        if (StrKit.notBlank(ad.get("userName")+"")) {
//            columns.like("user_name", "%"+ad.get("userName")+"%");
//        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}