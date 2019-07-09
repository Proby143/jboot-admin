package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.service.api.SysLogService;
import io.jboot.admin.service.api.SysUserService;
import io.jboot.admin.service.entity.model.SysLog;
import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Bean
@Singleton
public class SysLogServiceImpl extends JbootServiceBase<SysLog> implements SysLogService {
    @Inject
    private SysUserService userService;

    @Override
    public Page<SysLog> findPage(SysLog log, int pageNumber, int pageSize) {
        Columns columns = Columns.create();

        if (StrKit.notBlank(log.getIp())) {
            columns.like("ip", "%"+log.getIp()+"%");
        }
        if (StrKit.notBlank(log.getUrl())) {
            columns.like("url", "%"+log.getUrl()+"%");
        }
        if (StrKit.notBlank(log.getLastUpdAcct())) {
            columns.like("lastUpdAcct", "%"+log.getLastUpdAcct()+"%");
        }

        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "id desc");
    }
}