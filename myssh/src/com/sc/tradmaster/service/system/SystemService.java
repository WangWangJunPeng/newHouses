package com.sc.tradmaster.service.system;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectReportRecord;
import com.sc.tradmaster.bean.ReportResult;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.system.impl.dto.AdDTO;
import com.sc.tradmaster.service.system.impl.dto.Partner;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndShopDTO;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndUsers;
import com.sc.tradmaster.utils.Page;

/**
 * 
 * 平台管理Service
 * @author cdh
 *
 */
public interface SystemService {

	
	/**
	 * 获取平台管理中项目总数，置业顾问总数，房源总数，门店总数，经纪人总数，合伙人总数
	 * @return
	 */
	List<Map<String,String>> getSystemCount();
	
	
	/**
	 * 获取平台签约，到访记录总数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String,String>> getRecordsNum(String startTime, String endTime);
	
	/**
	 * 
	 * 获取中介平台发起的所有的数据的数量
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String, String>> getMeidNum(String startTime, String endTime);
	/**
	 * 2017-3-9 maoxy
	 * 查询合伙人
	 * @return
	 */
	Set<User> findUsersByRoleId();
	
	/**
	 * 2017-3-10 maoxy
	 * 查询出未与合伙人关联的项目
	 * @return
	 */
	List<Project> findPartnerOtherProjects();
	/**
	 * 2017-3-10 maoxy
	 * 查询出未与合伙人关联的门店
	 * @return
	 */
	List<Shops> findPartnerOtherShops();
	
	/**
	 * 2017-3-10 maoxy
	 * 添加合伙人与案场的关系
	 * @param userId
	 * @param projectIds
	 * @param pvalidity
	 */
	void addPartnerAndProjects(String userId, String[] projectIds, Integer pvalidity);
	/**
	 * 2017-3-10 maoxy
	 * 添加合伙人与门店关系
	 * @param userId
	 * @param shopIds
	 * @param svalidity
	 */
	void addPartnerAndShops(String userId, String[] shopIds, Integer svalidity);
	/**
	 * 2017-3-13 maoxy
	 * 查询状态正常的合伙人
	 */
	List<Partner> findPartners(int page,int count);
	/**
	 * 2017-3-13 maoxy
	 * 根据ID查询合伙人相关
	 * @param userId
	 * @return
	 */
	Partner findPartnerById(String userId);
	/**
	 * 2017-3-13 maoxy
	 * 更新合伙人关联信息
	 * @param userId
	 * @param projectIds
	 * @param shopIds
	 */
	void updatePartnerRel(User user, String[] projectIds, String[] shopIds);

	/**
	 * 2017-3-16 maoxy
	 * 查询案场所关联的合伙人以及案场助理
	 * @return
	 */
	List<ProjectsAndUsers> findAllProjectAndUsers();
	
	/**
	 * 2017-3-16 maoxy
	 * 查询案场所关联的合伙人以及案场助理(分页)
	 * @param page 分页对象
	 * @return
	 */
	void findAllProjectAndUsersToPage(Page page,String addr);

	/**
	 * 2017-3-16 maoxy
	 * 新建合伙人
	 * @param user
	 */
	boolean addPartner(User user);

	/**
	 * 分页查询广告
	 * @param page 当前页
	 * @param count 每页条数
	 * @return
	 */
	List<AdDTO> findAdvertisementByPage(int page,int count);

	/**
	 * 2017-3-22 maoxy
	 * 根据iD查询广告
	 * @param adId
	 * @return
	 */
	AdDTO findAdByAdId(String adId);

	
	/**
	 * 查找所有项目对账单
	 * @param city
	 * @param time
	 * @return
	 */
	List<ProjectsAndShopDTO> findStatementOfAccount(String city, String startTime, String endTime); 
	
	/**
	 * 平台对账单确定和取消收款
	 * @param houseNum 房源的唯一标示
	 * @param isConfirm 是否确认到款 0,取消收款  1,确认到款
	 * @param user 平台结款确认人
	 * @return
	 */
	boolean updateCommissionForm(Integer houseNum, Integer isConfirm, User user);
	
	/**
	 * 查找所有门店的报表
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String, Object>> findShopReportForms(String city, String startTime, String endTime, int page, int count);
	
	
	/**
	 * 查找所有经纪人的报表
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String, Object>> findMediReportForms(String city, String startTime, String endTime,int page,int count);
	
	
	/**
	 * 查找所有项目的报表
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @param count 
	 * @param page 
	 * @return
	 */
	List<Map<String, Object>> findProjectReportForms(String city, String startTime, String endTime, int page, int count);

	/**
	 * 查询广告条数
	 * @return
	 */
	int findAdTotal();

	/**
	 * 根据省市 查询项目
	 * @param string
	 * @return
	 */
	List<Project> findProByCity(String string);

	/**
	 * 查询项目的位置信息
	 * @param projectId
	 * @return
	 */
	Map<String, String> findProjectAddressById(String projectId);

	/**
	 * 根据市的ID 查询本市下面的所有项目
	 * @param cityId
	 * @return
	 */
	List<Project> findProjectByCity(String cityId);

	/**
	 * 更新保存广告
	 * @param ad
	 */
	void updateAd(Advertisement ad);

	/**
	 * 查询合伙人条数
	 * @return
	 */
	int findPartnerNum();

	/**
	 * 获取项目报表条数
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getProjectRepCount(String city, String startTime, String endTime);

	/**
	 * 获取门店报表条数
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getShopRepCount(String city, String startTime, String endTime);

	/**
	 * 获取中介条数
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getMediRepCount(String city, String startTime, String endTime);

	/**
	 * 分页获取申请中的项目
	 * @param page
	 * @param count
	 * @return
	 */
	Map<String,Object> findRegisterProjectByPage(int page, int count);

	/**
	 * 审核项目申请
	 * @param projectId
	 * @param userId
	 * @param agree 是否同意(0-否决,1-通过)
	 */
	void updateProjectAndUser(String projectId, int agree);
	
	/**
	 * 添加周、年..报到腾讯云，并将url添加到数据库
	 * @param rr
	 * @return
	 */
	boolean addReportExcelToCloud(ReportResult rr);
	
	
	/**
	 * 将数据报返回的url添加到数据库
	 * @param prr
	 * @param rr
	 * @return
	 */
	boolean addReportExcelToDB(ReportResult rr, String url);
	
	
	/**
	 * 下载项目的数据报
	 * @param projectId
	 * @param reportName  数据报的类别：week:周报 month月报 quarter 季报 half 半年报 year年报  other 阶段报
	 * @param startTime	yyyy-MM-dd HH:mm:ss
	 * @param endTime	yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	List<ProjectReportRecord> findDownloadReportExcel(String projectId, String reportName, String startTime, String endTime);
}
