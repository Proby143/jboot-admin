package io.jboot.admin.controller.teacity;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.base.common.ServiceConst;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.ShopService;
import io.jboot.admin.service.entity.model.Shop;
import io.jboot.admin.service.entity.model.User;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * describe:
 *
 * @author 白野
 * @date 2019\7\7 0007
 */
@RequestMapping("/teacity/shop")
public class ShopController extends BaseController {
    @JbootrpcService(group = ServiceConst.SERVICE_SYSTEM, version = ServiceConst.VERSION_1_0)
    ShopService shopService;
    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        Shop shop = new Shop();
        shop.setShopName(getPara("name"));

        Page<Shop> dataPage = shopService.findPage(shop, pageNumber, pageSize);
        renderJson(new DataTable<Shop>(dataPage));
    }
}
