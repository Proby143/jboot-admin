package io.jboot.admin.service.provider;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import io.jboot.admin.base.common.ZTree;
import io.jboot.admin.service.entity.status.system.ResStatus;
import io.jboot.admin.service.entity.status.system.RoleStatus;
import io.jboot.aop.annotation.Bean;
import io.jboot.admin.service.api.SysResService;
import io.jboot.admin.service.entity.model.SysRes;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class SysResServiceImpl extends JbootServiceBase<SysRes> implements SysResService {
    @Override
    public Page<SysRes> findPage(SysRes sysRes, int pageNumber, int pageSize) {
        Columns columns = Columns.create();

        columns.eq("pid", sysRes.getPid() == null ? 0L : sysRes.getPid());
        if (StrKit.notBlank(sysRes.getName())) {
            columns.like("name", "%"+sysRes.getName()+"%");
        }
        if (StrKit.notBlank(sysRes.getUrl())) {
            columns.like("url", "%"+sysRes.getUrl()+"%");
        }

        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "seq asc");
    }

    @Override
    public List<ZTree> findTreeOnUse() {
        Columns columns = Columns.create();
        columns.eq("status", ResStatus.USED);
        List<SysRes> list = DAO.findListByColumns(columns, "pid desc, seq asc");

        List<ZTree> zList = new ArrayList<ZTree>();
        for (SysRes SysRes : list) {
            ZTree ztree = new ZTree(SysRes.getId(), SysRes.getName(), SysRes.getPid());
            zList.add(ztree);
        }
        return zList;
    }

    @Override
    public List<ZTree> findAllTree() {
        List<SysRes> list = findAll();

        List<ZTree> zList = new ArrayList<ZTree>();
        for (SysRes SysRes : list) {
            ZTree ztree = new ZTree(SysRes.getId(), SysRes.getName(), SysRes.getPid());
            zList.add(ztree);
        }
        return zList;
    }

    @Override
    public List<ZTree> findTreeOnUseByRoleId(Long id) {
        List<ZTree> allTree = findTreeOnUse();
        List<SysRes> resList = findByRoleIdAndStatusUsed(id);

        List<Long> idList = new ArrayList<Long>();
        for (SysRes SysRes : resList) {
            idList.add(SysRes.getId());
        }

        for (ZTree tree : allTree) {
            if (idList.contains(tree.getId())) {
                tree.checked();
            }
        }
        return allTree;
    }

    @Override
    public List<SysRes> findByRoleIdAndStatusUsed(Long id) {
        SqlPara sp = Db.getSqlPara("system-SysRes.findByRoleIdAndStatusUsed");
        sp.addPara(ResStatus.USED);
        sp.addPara(RoleStatus.USED);
        sp.addPara(id);
        return DAO.find(sp);
    }

    @Override
    public List<SysRes> findByStatus(String status) {
        return DAO.findListByColumn("status", status);
    }

    @Override
    public List<SysRes> findByUserNameAndStatusUsed(String name) {
        SqlPara sp = Db.getSqlPara("system-SysRes.findByUserNameAndStatusUsed");
        sp.addPara(ResStatus.USED);
        sp.addPara(RoleStatus.USED);
        sp.addPara(name);
        return DAO.find(sp);
    }

    @Override
    public List<SysRes> findTopMenuByUserName(String name) {
        SqlPara sp = Db.getSqlPara("system-SysRes.findTopMenuByUserName");
        sp.addPara(ResStatus.USED);
        sp.addPara(RoleStatus.USED);
        sp.addPara(0L);
        sp.addPara(name);
        return DAO.find(sp);
    }

    @Override
    public List<SysRes> findLeftMenuByUserNameAndPid(String name, Long pid) {
        SqlPara sp = Db.getSqlPara("system-SysRes.findLeftMenuByUserNameAndPid");
        sp.addPara(ResStatus.USED);
        sp.addPara(RoleStatus.USED);
        sp.addPara(pid);
        sp.addPara(name);
        return DAO.find(sp);
    }

    @Override
    public boolean hasChildRes(Long id) {
        return DAO.findFirstByColumn("pid", id) != null;
    }
}