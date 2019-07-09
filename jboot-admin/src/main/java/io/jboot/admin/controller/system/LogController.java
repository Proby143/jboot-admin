package io.jboot.admin.controller.system;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.SysLogService;
import io.jboot.admin.service.entity.model.SysLog;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * 日志管理
 * @author Rlax
 *
 */
@RequestMapping("/system/log")
public class LogController extends BaseController {
    
    @JbootrpcService
    private SysLogService logService;

    /**
     * index
     */
    public void index() {
        render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        SysLog log = new SysLog();
        log.setIp(getPara("ip"));
        log.setUrl(getPara("url"));
        log.setLastUpdAcct(getPara("userName"));

        Page<SysLog> logPage = logService.findPage(log, pageNumber, pageSize);
        renderJson(new DataTable<SysLog>(logPage));
    }
    
}
