package com.sc.tradmaster.controller.group;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.utils.DataResult;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.HttpClientService;
import com.sc.tradmaster.utils.SmsContext;

/**
 * 集团控制层
 * 
 * @author cdh 2017-10-10
 *
 */
@Controller
public class GroupController {

	@Autowired
	private HttpClientService httpClientService;

	/**
	 * 封装的发送httpClient请求并返回解析好的数据
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object getHttpClientData(String url) {

		String proUrl = SmsContext.httpClientURL + url;
		try {
			String result = httpClientService.doPost(proUrl, null);
			DataResult<Object> rs = JSON.parseObject(result, DataResult.class);
			if (rs.isSuccess()) {
				return rs.getData();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/group_visit_deal", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object groupVisitAndDealResult(String groupId, String date) {
		String currentDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		if (StringUtils.isEmpty(date)) {
			date = currentDate;
		}
		String url = "/group/label/visitdeal/" + groupId + "/" + date;
		return getHttpClientData(url);

	}

	@ResponseBody
	@RequestMapping(value = "/group_visit_top", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object groupVisitTopResult(String groupId, String date) {
		String currentDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		if (StringUtils.isEmpty(date)) {
			date = currentDate;
		}
		String url = "/group/top/visit/" + groupId + "/" + date;
		return getHttpClientData(url);
	}

	@ResponseBody
	@RequestMapping(value = "/group_customer_top", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object groupCustomerTopResult(String groupId, String date) {
		String currentDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		if (StringUtils.isEmpty(date)) {
			date = currentDate;
		}
		String url = "/group/top/customer/" + groupId + "/" + date;
		return getHttpClientData(url);
	}

	@ResponseBody
	@RequestMapping(value = "/group_deal_top", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object groupDealTopResult(String groupId, String date) {
		String currentDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		if (StringUtils.isEmpty(date)) {
			date = currentDate;
		}
		String url = "/group/top/deal/" + groupId + "/" + date;
		return getHttpClientData(url);
	}
	
	@ResponseBody
	@RequestMapping(value="/group_outside_top", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object groupOutSideTopResult(String groupId, String date){
		String currentDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		if (StringUtils.isEmpty(date)) {
			date = currentDate;
		}
		String url = "/group/top/outside/"+groupId+"/"+date;
		return getHttpClientData(url);
	}
	
	@ResponseBody
	@RequestMapping(value="/group_visit_map", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object groupInSideVisitMap(String groupId, String date){
		String currentDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		if (StringUtils.isEmpty(date)) {
			date = currentDate;
		}
		String url = "/group/top/map/"+groupId+"/"+date;
		return getHttpClientData(url);
	}

}
