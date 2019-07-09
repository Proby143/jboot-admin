package io.jboot.admin.support.log;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jboot.Jboot;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.SysLogService;
import io.jboot.admin.service.entity.model.SysLog;
import io.jboot.admin.service.entity.model.SysUser;
import io.jboot.admin.support.auth.AuthUtils;

import java.util.Date;

/**
 * 系统日志拦截器
 * @author Rlax
 *
 */
public class SysLogInterceptor implements Interceptor {

	private final static com.jfinal.log.Log logger = com.jfinal.log.Log.getLog(SysLogInterceptor.class);

	@Override
	public void intercept(Invocation inv) {
		if (inv.getController() instanceof BaseController) {
			BaseController c = (BaseController) inv.getController();

			SysUser user = AuthUtils.getLoginUser();
			UserAgent userAgent = UserAgent.parseUserAgentString(c.getRequest().getHeader("User-Agent"));
			Browser browser = userAgent.getBrowser();

			SysLog log = new SysLog();
			log.setUid(user.getId());
			log.setBrowser(browser.getName());
			log.setOperation(c.getRequest().getMethod());
			log.setFrom(c.getReferer());
			log.setIp(c.getIPAddress());
			log.setUrl(c.getRequest().getRequestURI());
			log.setCreateDate(new Date());
			log.setLastUpdAcct(user.getId() == null ? "guest" : user.getName());
			log.setLastUpdTime(new Date());
			log.setNote("记录日志");

			try {
				SysLogService logService = Jboot.service(SysLogService.class);
				logService.save(log);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			} finally {
				inv.invoke();
			}
		} else {
			inv.invoke();
		}
	}

}
