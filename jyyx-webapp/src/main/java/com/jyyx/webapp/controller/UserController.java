package com.jyyx.webapp.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyyx.core.constant.Constants;
import com.jyyx.core.exception.JyException;
import com.jyyx.core.utils.Permission;
import com.jyyx.dao.mysql.entity.User;
import com.jyyx.service.UserService;
import com.jyyx.service.utils.Md5Utils;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;

/**
 * andy xu
 * 2016年11月7日
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登出
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo loginOut(HttpServletRequest request) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			Object userName = request.getSession().getAttribute(Constants.USER_SESSION_KEY);
			if (null == userName) {
				throw new JyException("没有操作权限");
			}
			
			request.getSession().removeAttribute(Constants.USER_SESSION_KEY);
			return result;
		} catch (Exception e) {
			logger.error("用户登录失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	/**
	 * 登录
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo login(@RequestBody User param, HttpServletRequest request) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(param.getLoginName())
					|| StringUtils.isBlank(param.getLoginPwd())) {
				throw new JyException("参数错误，信息不全");
			}
			List<User> userList = userService.getResources(param);
			if (CollectionUtils.isEmpty(userList)) {
				throw new JyException("登录失败，用户" + param.getLoginName() + "不存在");
			}
			User user = userList.get(0);
			String dbPwd = user.getLoginPwd();
			
			boolean correct = Md5Utils.verify(param.getLoginPwd(), dbPwd);
			if (!correct) {
				throw new JyException("登录失败，密码错误");
			} 
			
			request.getSession().setAttribute(Constants.USER_SESSION_KEY, user.getLoginName());
			result.setData(user);
			return result;
		} catch (Exception e) {
			logger.error("增加用户出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo addResources(@RequestBody User param) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(param.getLoginName())
					|| StringUtils.isBlank(param.getLoginPwd())) {
				throw new JyException("参数错误，信息不全");
			}
			
			List<User> userList = userService.getResources(param);
			if (null != userList && userList.size() > 0) {
				throw new JyException("用户名" + param.getLoginName() + "已经存在");
			}
			
			userService.addResource(param);
			return result;
		} catch (Exception e) {
			logger.error("增加用户出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modify/{userId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo modifyResources(@PathVariable int userId, @RequestBody User param) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(param.getLoginName())) {
				throw new JyException("参数错误，信息不全");
			}
			param.setLoginName(null);
			param.setId(userId);
			
			User dbUser = userService.getResourceById(userId);
			if (dbUser.getLoginPwd().equals(param.getLoginPwd())) {
				param.setLoginPwd(null);
			}
			userService.modifyResources(param);
			return result;
		} catch (Exception e) {
			logger.error("修改用户" + userId + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo deleteResources(@PathVariable int userId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (1 == userId) {
				throw new JyException("管理员也想删掉？？不干不准删");
			}
			userService.deleteResource(userId);
			return result;
		} catch (Exception e) {
			logger.error("删除用户" + userId + "失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/{userId}")
	@ResponseBody
	@Permission
	public JyResultVo getResources(@PathVariable int userId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			User user = userService.getResourceById(userId);
			result.setData(user);
			return result;
		} catch (Exception e) {
			logger.error("查询用户详情出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get")
	@ResponseBody
	@Permission
	public JyResultVo getResources() {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			List<User> userList = userService.getResources(new User());
			for (User user : userList) {
				user.setLoginPwd(null);
			}
			result.setData(userList);
			return result;
		} catch (Exception e) {
			logger.error("查询用户列表出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/info")
	@ResponseBody
	public JyResultVo getUserInfo(HttpServletRequest request) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			Object userObj = request.getSession().getAttribute(Constants.USER_SESSION_KEY);
			if (null == userObj) {
				return result;
			}

			User user = new User();
			user.setLoginName(userObj.toString());
			List<User> userList = userService.getResources(user);
			if (CollectionUtils.isEmpty(userList)) {
				return result;
			} else {
				result.setData(userList.get(0));
			}
			return result;
		} catch (Exception e) {
			logger.error("查询用户列表出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
}
