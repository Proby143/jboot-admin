package io.jboot.admin.service.provider;

import com.jfinal.plugin.activerecord.Db;
import io.jboot.admin.service.api.SysRoleResService;
import io.jboot.admin.service.entity.model.SysRoleRes;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class SysRoleResServiceImpl extends JbootServiceBase<SysRoleRes> implements SysRoleResService {
    @Override
    public int deleteByRoleId(Long roleId) {
        return Db.update("delete from sys_role_res where role_id = ?", roleId);
    }

    @Override
    public int[] batchSave(List<SysRoleRes> list) {
        return  Db.batchSave(list, list.size());
    }
}