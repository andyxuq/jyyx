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
import com.jyyx.dao.model.JyCaseCategory;
import com.jyyx.dao.mysql.entity.CaseCategory;
import com.jyyx.dao.mysql.entity.MsgCategory;
import com.jyyx.service.CaseCategoryService;
import com.jyyx.service.MsgCategoryService;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;

/**
 * andy xu
 * 2016年11月6日
 */
@Controller
@RequestMapping("/api/case/category")
public class CaseCategoryController {

	private static Logger logger = LoggerFactory.getLogger(CaseCategoryController.class);
	
	@Autowired
	private CaseCategoryService caseCategoryService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo addResources(@RequestBody CaseCategory caseCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(caseCategory.getCategoryName())) {
				throw new JyException("参数错误，分类名为空");
			}
			if (caseCategory.getParentId() == null) {
				caseCategory.setParentId(0);
			}
			caseCategoryService.addResources(caseCategory);
			return result;
		} catch (Exception e) {
			logger.error("增加案例分类出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modify/{categoryId}", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo modifyResources(@PathVariable int categoryId, @RequestBody CaseCategory caseCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(caseCategory.getCategoryName())) {
				throw new JyException("参数错误，分类名为空");
			}
			
			caseCategory.setId(categoryId);
			caseCategoryService.modifyResources(caseCategory);
			return result;
		} catch (Exception e) {
			logger.error("修改案例分类" + categoryId + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modifyOrder", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo modifyResourcesOrders(@RequestBody HashMap<Integer, Integer> orderMap) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			caseCategoryService.modifyResourcesOrders(orderMap);
			return result;
		} catch (Exception e) {
			logger.error("批量修改案例分类排序号" + orderMap + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo getResources(@RequestBody CaseCategory caseCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			List<JyCaseCategory> resList = caseCategoryService.getResources(caseCategory);
			result.setData(resList);
			return result;
		} catch (Exception e) {
			logger.error("查询案例分类出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo deleteResources(@PathVariable int categoryId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			caseCategoryService.deleteResources(categoryId);
			return result;
		} catch (Exception e) {
			logger.error("删除案例分类" + categoryId + "失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/{categoryId}")
	@ResponseBody
	public JyResultVo getResources(@PathVariable int categoryId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			CaseCategory caseCategory = caseCategoryService.getResourcesById(categoryId);
			result.setData(caseCategory);
			return result;
		} catch (Exception e) {
			logger.error("查询案例分类详情出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
}
