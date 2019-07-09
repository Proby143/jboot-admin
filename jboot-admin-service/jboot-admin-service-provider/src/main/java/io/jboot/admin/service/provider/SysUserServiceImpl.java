package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.service.api.SysUserRoleService;
import io.jboot.admin.service.api.SysUserService;
import io.jboot.admin.service.entity.model.SysUser;
import io.jboot.admin.service.entity.model.SysUserRole;
import io.jboot.admin.service.entity.status.system.UserOnlineStatus;
import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Bean
@Singleton
public class SysUserServiceImpl extends JbootServiceBase<SysUser> implements SysUserService {
    @Inject
    private SysUserRoleService userRoleService;

    @Override
    public Page<SysUser> findPage(SysUser SysUser, int pageNumber, int pageSize) {
        Columns columns = Columns.create();

        if (StrKit.notBlank(SysUser.getName())) {
            columns.like("name", "%"+SysUser.getName()+"%");
        }
        if (StrKit.notBlank(SysUser.getPhone())) {
            columns.like("phone", "%"+SysUser.getPhone()+"%");
        }
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "id desc");
    }

    @Override
    public boolean hasUser(String name) {
        return findByName(name) != null;
    }

    @Override
    public SysUser findByName(String name) {
        return DAO.findFirstByColumn("name", name);
    }

    @Override
    public boolean saveUser(SysUser SysUser, Long[] roles) {
        String pwd = SysUser.getPwd();

        if (StrKit.notBlank(pwd)) {
            String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
            SimpleHash hash = new SimpleHash("md5", pwd, salt2, 2);
            pwd = hash.toHex();
            SysUser.setPwd(pwd);
            SysUser.setSalt2(salt2);
        }

        SysUser.setOnlineStatus(UserOnlineStatus.OFFLINE);
        SysUser.setCreatedate(new Date());
        SysUser.setLastUpdTime(new Date());
        SysUser.setNote("保存系统用户");

        return Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                if (!SysUser.save()) {
                    return false;
                }

                if (roles != null) {
                    List<SysUserRole> list = new ArrayList<SysUserRole>();
                    for (Long roleId : roles) {
                        SysUserRole userRole = new SysUserRole();
                        userRole.setUserId(SysUser.getId());
                        userRole.setRoleId(roleId);
                        list.add(userRole);
                    }
                    int[] rets = userRoleService.batchSave(list);

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
    public boolean updateUser(SysUser SysUser, Long[] roles) {
        String pwd = SysUser.getPwd();
        if (StrKit.notBlank(pwd)) {
            String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
            SimpleHash hash = new SimpleHash("md5", pwd, salt2, 2);
            pwd = hash.toHex();
            SysUser.setPwd(pwd);
            SysUser.setSalt2(salt2);
        } else {
            SysUser.remove("pwd");
        }

        SysUser.setLastUpdTime(new Date());
        SysUser.setNote("修改系统用户");

        return Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                if (!SysUser.update()) {
                    return false;
                }

                userRoleService.deleteByUserId(SysUser.getId());

                if (roles != null) {
                    List<SysUserRole> list = new ArrayList<SysUserRole>();
                    for (Long roleId : roles) {
                        SysUserRole userRole = new SysUserRole();
                        userRole.setUserId(SysUser.getId());
                        userRole.setRoleId(roleId);
                        list.add(userRole);
                    }

                    int[] rets = userRoleService.batchSave(list);
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
}