package com.jyyx.webapp.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyyx.core.exception.JyException;
import com.jyyx.core.utils.Permission;
import com.jyyx.dao.mysql.entity.MsgCategory;
import com.jyyx.service.MsgCategoryService;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;

/**
 * andy xu
 * 2016年11月6日
 */
@Controller
@RequestMapping("/api/msg/category")
public class MsgCategoryController {

	private static Logger logger = LoggerFactory.getLogger(MsgCategoryController.class);
	
	@Autowired
	private MsgCategoryService msgCategoryService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo addResources(@RequestBody MsgCategory msgCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(msgCategory.getCategoryName())) {
				throw new JyException("参数错误，分类名为空");
			}
			msgCategoryService.addResources(msgCategory);
			return result;
		} catch (Exception e) {
			logger.error("增加资讯分类出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modify/{categoryId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo modifyResources(@PathVariable int categoryId, @RequestBody MsgCategory msgCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (msgCategory.getCategoryName() == null) {
				throw new JyException("参数错误，分类名为空");
			}
			
			msgCategory.setId(categoryId);
			msgCategoryService.modifyResources(msgCategory);
			return result;
		} catch (Exception e) {
			logger.error("修改资讯分类" + categoryId + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modifyOrder", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo modifyResourcesOrders(@RequestBody HashMap<Integer, Integer> orderMap) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			msgCategoryService.modifyResourcesOrders(orderMap);
			return result;
		} catch (Exception e) {
			logger.error("批量修改资源分类排序号" + orderMap + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo getResources(@RequestBody MsgCategory msgCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			List<MsgCategory> msgCatList = msgCategoryService.getResources(msgCategory);
			result.setData(msgCatList);
			return result;
		} catch (Exception e) {
			logger.error("查询资讯分类出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo deleteResources(@PathVariable int categoryId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			msgCategoryService.deleteResources(categoryId);
			return result;
		} catch (Exception e) {
			logger.error("删除资讯分类" + categoryId + "失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/{categoryId}")
	@ResponseBody
	public JyResultVo getResources(@PathVariable int categoryId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			MsgCategory msgCategory = msgCategoryService.getResourcesById(categoryId);
			result.setData(msgCategory);
			return result;
		} catch (Exception e) {
			logger.error("查询资讯分类详情出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
}
