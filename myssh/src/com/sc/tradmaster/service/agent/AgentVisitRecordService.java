package com.sc.tradmaster.service.agent;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.service.agent.impl.visitDTO.Customer;
import com.sc.tradmaster.service.agent.impl.visitDTO.ProjectCustomerDTO;
import com.sc.tradmaster.service.agent.impl.visitDTO.AgentRecessionRecordsDTO;
import com.sc.tradmaster.service.agent.impl.visitDTO.AgentVisitRecordDTO;
import com.sc.tradmaster.utils.Page;

/**
 * 2017-02-03
 * @author grl
 *
 */
public interface AgentVisitRecordService {

	/**
	 * 通过用户信息获取到访客户信息
	 * @param user
	 * @return
	 */
	List<VisitRecords> findVisitInfoByUser(User user,String data);

	/**
	 * 通过手机号获取客户的信息
	 * @param phone
	 * @return
	 */
	ProjectCustomerDTO findCustomerInfoByPhone(User u ,String phone);

	/**
	 * 职业顾问添加客户信息
	 * @param user
	 * @param phone
	 * @throws ParseException 
	 */
	ProjectCustomers addOrUpdateAgentInsertCustomerInfo(User user,String cName, String phone,String desc ,Integer visitNo) throws ParseException;
	
	/**
	 * 通过projectId获得当前案场的所有置业顾问列表
	 * @return
	 */
	List findAgents(String projectId);
	void findAgents(String projectId,Page page);
	/**
	 * 获取签到签退记录
	 * @param user
	 * @param projectId
	 * @param checkTime
	 * @return 
	 */
	List<SignRecords> findAllSignAndOutRecordList(User user, String projectId, String checkTime);

	/**
	 * 上传到访记录，更新到访记录表
	 * @param user 
	 * @param excel
	 * @throws IOException 
	 * @throws Exception 
	 */
	Integer addOrUpdateVisitReocrdFromExcel(VisitRecords vrs,Integer checkTeam);
	
	/**
	 * 获取客户到访记录
	 */
	Customer findCustomerVisitRecord(String projectId, String phone);

	/**
	 * 签到签退
	 */
	void addSignInAndUpdateSignOut(User user,Integer type,String projectId,String userId,String time);

	/**
	 * 更新到访信息
	 * @param user
	 * @param visitNo
	 * @param phone
	 */
	void addorUpdataVistInfo(User user, Integer visitNo, String phone);

	/**
	 * 获取待签退置业顾问列表
	 * @param projectId
	 * @param page
	 */
	void findAgentsNotSignOut(String projectId, Page page);

	/**
	 * 职业顾问排队列表
	 * @param checkTeam
	 * @param projectId
	 * @param page
	 */
	void findQueueUpAgents(Integer checkTeam, String projectId, Page page);

	/**
	 * 置业顾问接待中列表
	 * @param checkTeam
	 * @param projectId
	 * @param page
	 */
	void findReceivIngAgents(Integer checkTeam, String projectId, Page page);

	/**
	 * 接访记录-接待中
	 * @param projectId
	 * @param page
	 */
	void findRecessionAgents(String projectId, Page page);

	/**
	 * 接访记录-已送别
	 * @param projectId
	 * @param page
	 */
	void findFarewelledRecords(String projectId, Page page);

	/**
	 * 获取当前到访记录
	 * @param visitNo
	 * @return
	 */
	AgentVisitRecordDTO findCurrentRecessionRecordDetail(Integer visitNo);

	/**
	 * 获取所有职业顾问分页显示
	 * @param projectId
	 * @param page
	 */
	void findAgentsPageList(String projectId, Page page);

	/**
	 * 当日业务一栏-到岗信息
	 * @param proId
	 * @param dataStr
	 * @return
	 */
	List<AgentRecessionRecordsDTO> findWorkAgentByDataStr(String proId, String dataStr);

	/**
	 * 当日业务一栏-接访信息
	 * @param proId
	 * @param dataStr
	 * @return
	 */
	List<AgentRecessionRecordsDTO> findTodayVisitByDataStr(String proId, String dataStr);

	/**
	 * 判断当前置业顾问是否在接访
	 * @param projectId
	 * @param userId
	 * @return
	 */
	VisitRecords findCurrentAgentIsReplete(String projectId, String userId);

	/**
	 * 签到签退(new)
	 * @param user
	 * @param type
	 * @param projectId
	 * @param userId
	 * @param time
	 */
	void addSignInAndUpdateSignOutNew(User user, Integer type, String projectId, String userId, String time,Integer checkTeam);

	/**
	 * 顺序接访集合
	 * @param checkTeam
	 * @param projectId
	 * @return 
	 */
	List findAllQueueUpAgents(Integer checkTeam, String projectId,Page page);

	/**
	 * 获取签到客户
	 * @param user
	 * @param projectId
	 * @return
	 */
	SignRecords IsSignged(User user, String projectId,String time);

	Integer addOrUpdateMoreVisitReocrdFromExcel(VisitRecords vr, Integer checkTeam) throws Exception;
}
