package io.jboot.admin.support.auth;

import io.jboot.Jboot;
import io.jboot.admin.base.plugin.shiro.auth.MuitiAuthenticatied;
import io.jboot.admin.service.api.SysResService;
import io.jboot.admin.service.api.SysRoleService;
import io.jboot.admin.service.api.SysUserService;
import io.jboot.admin.service.entity.model.SysRes;
import io.jboot.admin.service.entity.model.SysRole;
import io.jboot.admin.service.entity.model.SysUser;
import io.jboot.admin.service.entity.status.system.UserStatus;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理端认证授权
 * @author Rlax
 *
 */
public class LoginAuth implements MuitiAuthenticatied {

    @Override
    public boolean hasToken(AuthenticationToken authenticationToken) {
        String loginName = authenticationToken.getPrincipal().toString();
        SysUserService sysUserApi = Jboot.service(SysUserService.class);
        return sysUserApi.hasUser(loginName);
    }

    @Override
    public boolean wasLocked(AuthenticationToken authenticationToken) {
        String loginName = authenticationToken.getPrincipal().toString();

        SysUserService sysUserApi = Jboot.service(SysUserService.class);
        SysUser sysUser = sysUserApi.findByName(loginName);
        return !sysUser.getStatus().equals(UserStatus.USED);
    }

    @Override
    public AuthenticationInfo buildAuthenticationInfo(AuthenticationToken authenticationToken) {
        String loginName = authenticationToken.getPrincipal().toString();

        SysUserService sysUserApi = Jboot.service(SysUserService.class);
        SysUser sysUser = sysUserApi.findByName(loginName);
        String salt2 = sysUser.getSalt2();
        String pwd = sysUser.getPwd();

        return new SimpleAuthenticationInfo(loginName, pwd, ByteSource.Util.bytes(salt2), "ShiroDbRealm");
    }

    @Override
    public AuthorizationInfo buildAuthorizationInfo(PrincipalCollection principals) {
        String loginName = (String) principals.fromRealm("ShiroDbRealm").iterator().next();

        SysRoleService sysRoleApi = Jboot.service(SysRoleService.class);
        List<SysRole> sysRoleList = sysRoleApi.findByUserName(loginName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<String> roleNameList = new ArrayList<String>();
        for (SysRole sysRole : sysRoleList) {
            roleNameList.add(sysRole.getName());
        }

        SysResService sysResService = Jboot.service(SysResService.class);
        List<SysRes> sysResList = sysResService.findByUserNameAndStatusUsed(loginName);
        List<String> urls = new ArrayList<String>();
        for (SysRes sysRes : sysResList) {
            urls.add(sysRes.getUrl());
        }

        info.addRoles(roleNameList);
        info.addStringPermissions(urls);
        return info;
    }
}
