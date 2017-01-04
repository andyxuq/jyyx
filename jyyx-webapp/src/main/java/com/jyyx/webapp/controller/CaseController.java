package com.jyyx.webapp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyyx.core.exception.JyException;
import com.jyyx.core.utils.Permission;
import com.jyyx.dao.model.CaseParam;
import com.jyyx.dao.model.JyCase;
import com.jyyx.dao.utils.PageData;
import com.jyyx.service.CaseService;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;

/**
 * andy xu
 * 2016年11月7日
 */
@Controller
@RequestMapping("/api/case")
public class CaseController {
	
	private static Logger logger = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	private CaseService caseService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo addResources(@RequestBody CaseParam param) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(param.getCaseName())) {
				throw new JyException("参数错误，信息不全");
			}
			caseService.addResource(param);
			return result;
		} catch (Exception e) {
			logger.error("增加案例出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modify/{caseId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo modifyResources(@PathVariable int caseId, @RequestBody CaseParam param) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(param.getCaseName())) {
				throw new JyException("参数错误，信息不全");
			}
			
			param.setId(caseId);
			caseService.modifyResources(param);
			return result;
		} catch (Exception e) {
			logger.error("修改案例" + caseId + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo getResources(@RequestBody CaseParam param, @RequestParam(required = false) Integer page
			, @RequestParam(required = false) Integer pageRow) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (null != page) {
				if (null == pageRow) {
					pageRow = 0;
				}
				PageData<JyCase> pageData = caseService.getResourcesWithPage(param, page, pageRow);
				result.setData(pageData);
			} else {
				List<JyCase> productList = caseService.getResources(param);
				result.setData(productList);
			}
			
			return result;
		} catch (Exception e) {
			logger.error("查询案例出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	
	@RequestMapping(value = "/delete/{caseId}", method = RequestMethod.POST)
	@ResponseBody
	@Permission
	public JyResultVo deleteResources(@PathVariable int caseId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			caseService.deleteResources(caseId);
			return result;
		} catch (Exception e) {
			logger.error("删除案例" + caseId + "失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/{caseId}")
	@ResponseBody
	public JyResultVo getResources(@PathVariable int caseId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			JyCase product = caseService.getResourceById(caseId);
			result.setData(product);
			return result;
		} catch (Exception e) {
			logger.error("查询案例详情出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
}
