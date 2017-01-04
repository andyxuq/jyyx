package com.jyyx.webapp.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jyyx.core.constant.Constants;
import com.jyyx.core.utils.Permission;

/**
 * 功能权限验证
 * andy xu
 * 2016年11月2日
 */
public class PermissionInteceptor extends HandlerInterceptorAdapter{

	private static Logger logger = LoggerFactory.getLogger(PermissionInteceptor.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		try {
			if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
				Permission permission = ((HandlerMethod)handler).getMethodAnnotation(Permission.class);
				if (null == permission) {
					return true;
				}
				
				//TODO 打开注册，验证用户
				Object userLoginName = request.getSession().getAttribute(Constants.USER_SESSION_KEY);
				if (null == userLoginName) {
					response.sendError(403);
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("请求预处理报错", e);
			response.sendError(403, "你没有访问该接口的权限");
			return false;
		}
	}
}
