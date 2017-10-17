package com.sc.tradmaster.service.visitRecords;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.ProjectCustomersDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.VisitCustomerDTO;
import com.sc.tradmaster.utils.Page;

public interface VisitRecordsService {

	/**
	 * 到访客户表
	 */	
	List<VisitCustomerDTO> findVisitCustomer(User user, String projectId, String startTime, String endTime)throws ParseException;
	
	/**
	 * 下载到访客户表
	 */	
	void findVisit(String projectId,String arriveTime,Page p)throws ParseException;
	

	/**
	 * 置业顾问到访客户表
	 */
	List<ProjectCustomersDTO> findSaleVisitList(User user);

	/**
	 * 通过Id获取到访记录
	 * @param visitNo
	 * @return
	 */
	VisitRecords findVisitById(Integer visitNo);
	
	
	/**
	 * 一个月中周一至周日每天接访人数
	 * @param month
	 * @return
	 */
	Map<String,Object> findOneDayVisitNum(String startTime, String endTime,String projectId)throws ParseException;

	/**
	 * 一个月中周一至周日每天接访人数
	 * @param month
	 * @return
	 */
	Map<String,Object> findOneDayVisitNumTwo(String startTime, String endTime,String projectId)throws ParseException;

	/**
	 * 更新到访记录中的案场客户信息
	 * @param visitNo
	 * @param old
	 */
	void addOrUpdateVisitByVisitNo(Integer visitNo, ProjectCustomers old);
}
