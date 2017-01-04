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
import com.jyyx.dao.model.JyProductCategory;
import com.jyyx.dao.mysql.entity.MsgCategory;
import com.jyyx.dao.mysql.entity.ProductCategory;
import com.jyyx.service.MsgCategoryService;
import com.jyyx.service.ProductCategoryService;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;

/**
 * andy xu
 * 2016年11月6日
 */
@Controller
@RequestMapping("/api/product/category")
public class ProductCategoryController {

	private static Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo addResources(@RequestBody ProductCategory productCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(productCategory.getCategoryName())) {
				throw new JyException("参数错误，分类名为空");
			}
			if (productCategory.getParentId() == null) {
				productCategory.setParentId(0);
			}
			productCategoryService.addResources(productCategory);
			return result;
		} catch (Exception e) {
			logger.error("增加产品分类出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modify/{categoryId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo modifyResources(@PathVariable int categoryId, @RequestBody ProductCategory productCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(productCategory.getCategoryName())) {
				throw new JyException("参数错误，分类名为空");
			}
			
			productCategory.setId(categoryId);
			productCategoryService.modifyResources(productCategory);
			return result;
		} catch (Exception e) {
			logger.error("修改产品分类" + categoryId + "出错", e);
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
			productCategoryService.modifyResourcesOrders(orderMap);
			return result;
		} catch (Exception e) {
			logger.error("批量修改产品分类排序号" + orderMap + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo getResources(@RequestBody ProductCategory productCategory) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			List<JyProductCategory> resList = productCategoryService.getResources(productCategory);
			result.setData(resList);
			return result;
		} catch (Exception e) {
			logger.error("查询产品分类出错", e);
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
			productCategoryService.deleteResources(categoryId);
			return result;
		} catch (Exception e) {
			logger.error("删除产品分类" + categoryId + "失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/{categoryId}")
	@ResponseBody
	public JyResultVo getResources(@PathVariable int categoryId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			ProductCategory productCategory = productCategoryService.getResourcesById(categoryId);
			result.setData(productCategory);
			return result;
		} catch (Exception e) {
			logger.error("查询产品分类详情出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
}
