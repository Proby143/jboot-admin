package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import io.jboot.admin.service.api.SysRoleResService;
import io.jboot.admin.service.api.SysRoleService;
import io.jboot.admin.service.entity.model.SysRole;
import io.jboot.admin.service.entity.model.SysRoleRes;
import io.jboot.admin.service.entity.status.system.RoleStatus;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class SysRoleServiceImpl extends JbootServiceBase<SysRole> implements SysRoleService {
    @Inject
    private SysRoleResService roleResService;

    @Override
    public Page<SysRole> findPage(SysRole sysRole, int pageNumber, int pageSize) {
        Columns columns = Columns.create();

        if (StrKit.notBlank(sysRole.getName())) {
            columns.like("name", "%"+sysRole.getName()+"%");
        }

        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "seq asc");
    }

    @Override
    public List<SysRole> findByUserName(String name) {
        SqlPara sp = Db.getSqlPara("system-role.findByUserName");
        sp.addPara(name);
        return DAO.find(sp);
    }

    @Override
    public boolean auth(Long id, String resIds) {
        List<SysRoleRes> roleResList = new ArrayList<SysRoleRes>();

        return Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                roleResService.deleteByRoleId(id);

                if (StrKit.notBlank(resIds)) {
                    String[] ress = resIds.split(",");

                    for (String resId : ress) {
                        SysRoleRes roleRes = new SysRoleRes();
                        roleRes.setRoleId(id);
                        roleRes.setResId(Long.parseLong(resId));
                        roleResList.add(roleRes);
                    }

                    int[] rets = Db.batchSave(roleResList, roleResList.size());
                    for (int ret : rets) {
                        if (ret < 1) {
                            return false;
                        }
                    }
                }

                return true;
            }
        });
    }

    @Override
    public List<SysRole> findByStatusUsed() {
        return DAO.findListByColumn("status", RoleStatus.USED);
    }
}