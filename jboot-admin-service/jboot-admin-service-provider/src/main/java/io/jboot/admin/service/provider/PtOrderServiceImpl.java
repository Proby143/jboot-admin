package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.service.entity.model.Code;
import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.PtOrderService;
import io.jboot.admin.service.entity.model.PtOrder;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class PtOrderServiceImpl extends JbootServiceBase<PtOrder> implements PtOrderService {
    @Override
    public Page<PtOrder> findPage(PtOrder ptOrder, int pageNumber, int pageSize) {

        Columns columns = Columns.create();

//        if (StrKit.notBlank(ptOrder.get("orderId")+"")) {
//            columns.like("user_name", "%"+ptOrder.get("orderId")+"%");
//        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}