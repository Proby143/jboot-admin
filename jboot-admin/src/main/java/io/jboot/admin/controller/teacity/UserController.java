package io.jboot.admin.controller.teacity;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.common.ServiceConst;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.UserService;
import io.jboot.admin.service.entity.model.User;
import io.jboot.admin.support.auth.AuthUtils;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * 商品管理
 * @author Rlax
 *
 */
@RequestMapping("/teacity/user")
public class UserController extends BaseController {

    @JbootrpcService(group = ServiceConst.SERVICE_SYSTEM, version = ServiceConst.VERSION_1_0)
    private UserService userService;


    /**
     * index
     */
//    @EnableMetricCounter("商品管理点击次数")
//    @EnableMetricHistogram("商品管理点击分布")
//    @EnableMetricMeter("商品管理点击频率")
//    @EnableMetricTimer("商品管理计时")
    public void index() {
        render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        User user = new User();
        user.setUserName(getPara("name"));

        Page<User> dataPage = userService.findPage(user, pageNumber, pageSize);
        renderJson(new DataTable<User>(dataPage));
    }

    /**
     * add
     */
    public void add() {
        render("add.html");
    }

    /**
     * 保存提交
     */
    @NotNullPara({"product.name", "product.prodDesc", "product.totalNum"})
    public void postAdd() {
        User user = getBean(User.class, "");

        if (!userService.save(user)) {
            throw new BusinessException("保存失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        Long id = getParaToLong("userId");
        User user = userService.findById(id);

        setAttr("user", user).render("update.html");
    }

    /**
     * 修改提交
     */
    @NotNullPara({"user.userId", "user.userName", "user.age", "user.totalNum"})
    public void postUpdate() {
        User user = getBean(User.class, "");

        if (userService.findById(user.getUserId()) == null) {
            throw new BusinessException("用户不存在");
        }

        user.setUserName(AuthUtils.getLoginUser().getName());
        user.setAge(user.getAge());
        user.setCity(user.getCity());
        user.setSex(user.getSex());

        if (!userService.update(user)) {
            throw new BusinessException("修改失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 删除
     */
    @NotNullPara({"id"})
    public void delete() {
        Long id = getParaToLong("id");
        if (!userService.deleteById(id)) {
            throw new BusinessException("删除失败");
        }

        renderJson(RestResult.buildSuccess());
    }
}
