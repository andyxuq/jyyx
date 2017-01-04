package com.jyyx.webapp.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyyx.core.exception.JyException;
import com.jyyx.core.utils.Permission;
import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.utils.PageData;
import com.jyyx.service.ProductService;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;

/**
 * andy xu
 * 2016年11月7日
 */
@Controller
@RequestMapping("/api/product")
public class ProductController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo addResources(@RequestBody ProductParam param) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(param.getProductName())
					|| CollectionUtils.isEmpty(param.getProductCategorys())) {
				throw new JyException("参数错误，信息不全");
			}
			productService.addResource(param);
			return result;
		} catch (Exception e) {
			logger.error("增加产品出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modify/{productId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo modifyResources(@PathVariable int productId, @RequestBody ProductParam param) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(param.getProductName())
					|| CollectionUtils.isEmpty(param.getProductCategorys())) {
				throw new JyException("参数错误，信息不全");
			}
			
			param.setId(productId);
			productService.modifyResources(param);
			return result;
		} catch (Exception e) {
			logger.error("修改产品" + productId + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo getResources(@RequestBody ProductParam param, @RequestParam(required = false) Integer page
			, @RequestParam(required = false) Integer pageRow) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (null != page) {
				if (null == pageRow) {
					pageRow = 0;
				}
				PageData<JyProduct> pageData = productService.getResourcesWithPage(param, page, pageRow);
				result.setData(pageData);
			} else {
				List<JyProduct> productList = productService.getResources(param);
				result.setData(productList);
			}
			
			return result;
		} catch (Exception e) {
			logger.error("查询产品出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	
	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo deleteResources(@PathVariable int productId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			productService.deleteResources(productId);
			return result;
		} catch (Exception e) {
			logger.error("删除产品" + productId + "失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/{productId}")
	@ResponseBody
	public JyResultVo getResources(@PathVariable int productId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			JyProduct product = productService.getResourceById(productId);
			result.setData(product);
			return result;
		} catch (Exception e) {
			logger.error("查询产品详情出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
}
