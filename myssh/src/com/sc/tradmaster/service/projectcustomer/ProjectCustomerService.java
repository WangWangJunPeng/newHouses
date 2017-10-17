package com.sc.tradmaster.service.projectcustomer;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerAnalyze;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerManagerInfoDTO;
import com.sc.tradmaster.utils.Page;

public interface ProjectCustomerService {

	/**
	 * list当前职业顾问管理案场下的客户
	 * @param user
	 * @return
	 */
	void findProCustomersByUser(User user,String selectValue,Page page);

	/**
	 * 客户管理-设置归属置业顾问 动态菜单
	 * @param parentId
	 * @return
	 */
	List<Map<String, String>> findAgentsToMenu(String parentId);

	/**
	 * 更新案场客户所属置业顾问
	 * @param proCursId
	 * @param agentId
	 */
	void addOrUpdateProCustomerownerAgent(String[] proCursId, String agentId);

	/**
	 * 中介门店 客户管理list
	 * @param user
	 * @param selectValue
	 * @param page
	 */
	void findShopCustomersByUser(User user, String selectValue, Page page);
	
	/**
	 * 根据用户的phone获取用户的到访记录和认购记录
	 * @param phone
	 * @return
	 */
	public Map<String, Object> findCustomerCAndVInfo(String phone, String projectId);
	
	
	/**
	 * 根据用户的phone查找用户
	 * @param phone
	 * @return
	 */
	public ProjectCustomers findProjectCustomersByPhone(String phone);	
	
	
	/**
	 * 
	 * 根据用户id查找客户的动态
	 * @param customerId
	 * @param customerType 客户类型（value：projectCustomerId || shopCustomerId）
	 * @return
	 */
	public List<Mydynamic> findCustomerDynamicByCustomerId(String customerId, String customerType);
	
	
	/**
	 * 导入客户信息-excell
	 * @param file
	 * @param projectId
	 * @return
	 */
	public Map<String, Object> addCustomerExcell(MultipartFile file, String projectId);
	
	/**
	 * 根据筛选条件分页显示客户  maxy
	 * @param coustomerStatus 客户状态条件(1-已到访,2-认购,3-付款,4-签约,5-认购否决,6-退款)
	 * @param visitNum 到访次数条件(1-一次,2-两次,3-三次)
	 * @param projectAgentId 归属顾问Id条件
	 * @param tagIds 标签id条件
	 * @param projectId 当前项目Id
	 * @param page 当前页
	 * @param num 一页显示条数
	 * @return
	 */
	public Map<String,Object> searchProjectCoustomers(Integer[] coustomerStatus,Integer[] visitNum,String[] projectAgentId,Integer[] tagIds,String projectId,int page,int num);
	/**
	 * 按顾问调整归属 maoxy
	 * @param original
	 * @param neo
	 * @throws Exception
	 */
	public int update_projectCustomerOwner(String original,String neo) throws Exception;
	/**
	 * 按客户调整归属maoxy
	 * @param projectCustomerId
	 * @param agentId
	 * @throws Exception
	 */
	public void update_projectCustomerOwner(String[] projectCustomerId,String agentId)throws Exception;
	/**
	 * 查询客户基本信息
	 * @param projectCustomerId
	 * @return
	 */
	public CustomerManagerInfoDTO findOneCustomerInfo(String projectCustomerId);
	/**
	 * 获取客户分析
	 * @return
	 */
	public CustomerAnalyze getAnalyze(String projectId);
	/**
	 * 根据名字或者电话号码模糊匹配案场客户分页显示
	 * @param projectId
	 * @param srarchCondition
	 * @param page
	 * @param num
	 * @return
	 */
	Map<String, Object> searchProjectCoustomers(String projectId, String srarchCondition, int page, int num);
	/**
	 *  根据名字或者电话号码模糊匹配案场客户
	 * @param projectId
	 * @param srarchCondition
	 * @return
	 */
	List<ProjectCustomers> searchProjectCustomer(String projectId, String srarchCondition);

	void matchPhone(String phone,String projectId);

	/**
	 * 获取案场对象，pc为null返回客户信息不为null返回更新后的客户信息
	 * @param projectCustomerId
	 * @param pc 
	 * @return
	 */
	ProjectCustomers findProjectCustomersById(String projectCustomerId, ProjectCustomers pc);
	
}
