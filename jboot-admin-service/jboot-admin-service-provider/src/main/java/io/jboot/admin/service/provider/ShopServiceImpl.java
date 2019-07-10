package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.admin.service.api.ShopService;
import io.jboot.admin.service.entity.model.Shop;
import io.jboot.admin.service.entity.model.User;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class ShopServiceImpl extends JbootServiceBase<Shop> implements ShopService {

    @Override
    public List<Record> selectCityShop(String city) {
        List<Record> records = Db.find("select shop_id shopId,shop_name shopName from shop where city = '"+city+"'");
        return records;
    }

    @Override
    public Page<Shop> findPage(Shop shop, int pageNumber, int pageSize) {
        Columns columns = Columns.create();

        if (StrKit.notBlank(shop.getShopName())) {
            columns.like("user_name", "%"+shop.getShopName()+"%");
        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "updated desc");
    }
}