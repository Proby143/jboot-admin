package io.jboot.admin.support.auth;

import io.jboot.admin.base.common.Consts;
import io.jboot.admin.base.plugin.shiro.ShiroUtils;
import io.jboot.admin.service.entity.model.SysUser;
import io.jboot.admin.service.entity.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 授权认证工具类
 */
public class AuthUtils {

    /**
     * 是否登录
     * @return
     */
    public static boolean isLogin() {
        return ShiroUtils.isAuthenticated();
    }

    /**
     * 获取平台登录用户
     * @return
     */
    public static SysUser getLoginUser() {
        SysUser user = new SysUser();
        if (ShiroUtils.isAuthenticated()) {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            Object attribute = session.getAttribute(Consts.SESSION_USER);
            user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER);
        }
        return user;
    }

    /**
     * 校验用户登录密码
     * @param newPwd 新未加密的密码
     * @param oldPwd 旧加密后的密码
     * @param oldSalt2 旧加密盐
     * @return true-校验一致 否则 false
     */
    public static boolean checkPwd(String newPwd, String oldPwd, String oldSalt2) {
        return ShiroUtils.checkPwd(newPwd, oldPwd, oldSalt2);
    }
}
