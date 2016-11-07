package com.jyyx.webapp.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.jyyx.dao.mysql.entity.Msg;
import com.jyyx.dao.utils.PageData;
import com.jyyx.service.MsgService;
import com.jyyx.webapp.model.JyResultType;
import com.jyyx.webapp.model.JyResultVo;

/**
 * andy xu
 * 2016年11月7日
 */
@Controller
@RequestMapping("/api/msg")
public class MsgController {
	
	private static Logger logger = LoggerFactory.getLogger(MsgController.class);
	
	@Autowired
	private MsgService msgService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo addResources(@RequestBody Msg msg) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(msg.getMsgTitle())
					|| StringUtils.isBlank(msg.getMsgContent())
					|| null == msg.getCategoryId()
					|| 0 == msg.getCategoryId()) {
				throw new JyException("参数错误，信息不全");
			}
			msgService.addResource(msg);
			return result;
		} catch (Exception e) {
			logger.error("增加资讯出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modify/{msgId}", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo modifyResources(@PathVariable int msgId, @RequestBody Msg msg) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (StringUtils.isBlank(msg.getMsgTitle())
					|| StringUtils.isBlank(msg.getMsgContent())
					|| null == msg.getCategoryId()
					|| 0 == msg.getCategoryId()) {
				throw new JyException("参数错误，信息不全");
			}
			
			msg.setId(msgId);
			msgService.modifyResources(msg);
			return result;
		} catch (Exception e) {
			logger.error("修改资讯" + msgId + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/modifyOrder", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo modifyResourcesOrders(@RequestBody HashMap<Integer, Integer> orderMap) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			msgService.modifyOrders(orderMap);
			return result;
		} catch (Exception e) {
			logger.error("批量修改资讯资源排序号" + orderMap + "出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo getResources(@RequestBody Msg msg, @RequestParam(required = false) Integer page
			, @RequestParam(required = false) Integer pageRow) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			if (null != page) {
				if (null == pageRow) {
					pageRow = 0;
				}
				PageData<Msg> msgPageData = msgService.getPicResourcesWithPage(msg, page, pageRow);
				result.setData(msgPageData);
			} else {
				List<Msg> msgList = msgService.getResources(msg);
				result.setData(msgList);
			}
			
			return result;
		} catch (Exception e) {
			logger.error("查询资讯出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	
	@RequestMapping(value = "/delete/{msgId}", method = RequestMethod.POST)
	@ResponseBody
	public JyResultVo deleteResources(@PathVariable int msgId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			msgService.deleteMsgResources(msgId);
			return result;
		} catch (Exception e) {
			logger.error("删除资讯" + msgId + "失败", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
	
	@RequestMapping(value = "/get/{msgId}")
	@ResponseBody
	public JyResultVo getResources(@PathVariable int msgId) {
		JyResultVo result = new JyResultVo(JyResultType.SUCCESS);
		try {
			Msg msg = msgService.getResourceById(msgId);
			result.setData(msg);
			return result;
		} catch (Exception e) {
			logger.error("查询资讯详情出错", e);
			result = new JyResultVo(JyResultType.FAIL, e);
			return result;
		}
	}
}
