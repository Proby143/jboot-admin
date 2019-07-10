package io.jboot.admin.controller.teacity;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.base.common.ServiceConst;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.AdService;
import io.jboot.admin.service.api.ShopService;
import io.jboot.admin.service.entity.model.Ad;
import io.jboot.admin.service.entity.model.Shop;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * describe:
 *
 * @author 白野
 * @date 2019\7\10 0010
 */
@RequestMapping("/teacity/ad")
public class AdController extends BaseController {
    @JbootrpcService(group = ServiceConst.SERVICE_SYSTEM, version = ServiceConst.VERSION_1_0)
    AdService adService;
    public void index() {
        render("main.html");
    }
    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        Ad ad = new Ad();

        Page<Ad> dataPage = adService.findPage(ad, pageNumber, pageSize);
        renderJson(new DataTable<Ad>(dataPage));
    }
}
