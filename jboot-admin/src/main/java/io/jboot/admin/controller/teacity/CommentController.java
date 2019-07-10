package io.jboot.admin.controller.teacity;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.base.common.ServiceConst;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.ShopService;
import io.jboot.admin.service.api.UserCommentService;
import io.jboot.admin.service.entity.model.Shop;
import io.jboot.admin.service.entity.model.UserComment;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * describe:
 *
 * @author 白野
 * @date 2019\7\10 0010
 */
@RequestMapping("/teacity/comment")
public class CommentController extends BaseController {
    @JbootrpcService(group = ServiceConst.SERVICE_SYSTEM, version = ServiceConst.VERSION_1_0)
    UserCommentService userCommentService;
    public void index() {
        render("main.html");
    }
    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        UserComment userComment = new UserComment();
//        shop.setShopName(getPara("name"));

        Page<UserComment> dataPage = userCommentService.findPage(userComment, pageNumber, pageSize);
        renderJson(new DataTable<UserComment>(dataPage));
    }
}
