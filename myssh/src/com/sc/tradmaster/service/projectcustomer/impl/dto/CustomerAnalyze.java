package com.sc.tradmaster.service.projectcustomer.impl.dto;

import java.util.List;
import java.util.Map;

/**
 * 封装客户数据分析数据
 * @author Administrator
 *
 */
public class CustomerAnalyze {
	
	private List<Integer> histogram;
	private Map<String,String> pie;
	private Map<String,Object> data;
	

	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public List<Integer> getHistogram() {
		return histogram;
	}
	public void setHistogram(List<Integer> histogram) {
		this.histogram = histogram;
	}
	public Map<String, String> getPie() {
		return pie;
	}
	public void setPie(Map<String, String> pie) {
		this.pie = pie;
	}
	
	
	
	
	

}
