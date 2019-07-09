package io.jboot.admin.service.provider;

import com.jfinal.plugin.activerecord.Db;
import io.jboot.admin.service.entity.model.SysUserRole;
import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.SysUserRoleService;
import io.jboot.admin.service.entity.model.SysUserRole;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class SysUserRoleServiceImpl extends JbootServiceBase<SysUserRole> implements SysUserRoleService {
    @Override
    public int deleteByUserId(Long userId) {
        return Db.update("delete from sys_user_role where user_id = ?", userId);
    }

    @Override
    public int[] batchSave(List<SysUserRole> list) {
        return Db.batchSave(list, list.size());
    }
}