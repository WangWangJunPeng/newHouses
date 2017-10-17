package com.sc.tradmaster.service.user;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.AnalysisCategory;
import com.sc.tradmaster.bean.ManaerChartData;
import com.sc.tradmaster.bean.ManagerOwnAnalyse;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.user.impl.dto.AllCheckedCustomerDTO;
import com.sc.tradmaster.service.user.impl.dto.AnalyzeTagsDTO;
import com.sc.tradmaster.service.user.impl.dto.GrDto;
import com.sc.tradmaster.service.user.impl.dto.RankingDto;
import com.sc.tradmaster.service.user.impl.dto.VisitRecordsDTO;


public interface UserService {
	/**
	 * 通过id查询用户信息
	 * @param id
	 * @return
	 */
	User findById(String id);
	
	/**
	 * 用户登录接口
	 * @param u
	 * @return
	 */
	User addUserTokenAndlogin(User u);
	
	/**
	 * 中介和置业顾问app个人资料
	 * @param u
	 */
	Map findUserInfo(User user);
	
	/**
	 * 中介和置业顾问app原来密码验证
	 * @param u
	 */
	void findOldPassword(User user, String password);
	
	/**
	 * 中介和置业顾问app密码修改
	 * @param u
	 */
	void updatePassowrd(User user, String firstPassword, String secondPassword);

	/**
	 * 添加获取更新user
	 * @return 
	 */
	Map addOrUpdateUser(User u ,User user,String rightSign);

	/**
	 * 账户管理 重置密码、启停用户、删除用户（修改状态）
	 * @param proCustomerId
	 * @param doSign
	 */
	void updateUserInfo(User user, String doSign);
	
	/**
	 * 中介经纪人我的页面上面的信息显示
	 */
	Map findMidInfo(User user);
	
	/**
	 * 中介经纪人我的页面业务统计
	 */
	Map findMidBusiness(User user)throws ParseException;
	
	/**
	 * 置业顾问我的页面上面的信息显示
	 */
	Map findSaleInfo(User user);
	
	/**
	 * 置业顾问我的页面业务统计
	 */
	Map findSaleBusiness(User user)throws ParseException;

	
	/**
	 * 陈冬华 2017-03-06
	 * 注册门店
	 * @param shop
	 * @throws IOException 
	 * @throws Exception 
	 */
	void addShop(Shops shop, MultipartFile photo, MultipartFile licensePhoto, String province, String market, String area) throws Exception;
	/**
	 * 添加或更新中介用户
	 * @param u
	 * @param user
	 * @param rightSign
	 */
	void addOrUpdateMediUser(User u, User user, String rightSign);

	/**
	 * 通过userToken
	 * @param userToken
	 * @return
	 */
	User findByUserToken(String userToken);
	
	/**
	 * 查找注册的手机号码是否已经注册了
	 * @param phoneNum
	 * @return
	 */
	boolean findExistPhoneNum(String phoneNum);

	/**
	 * 异常测试
	 */
	void getArray();
	
	/**
	 * 查找所有的用户
	 * @param  status代表筛选条件
	 * @return 用户所有的信息
	 */
	List<Map<String,Object>> findAllUser(String status);
	
	
	/**
	 * 更新用户信息包括角色的更换
	 * @param user
	 * @param doSign
	 */
	void updateSystemUser(User u,User user, String doSign, String role);
	/**
	 * 密码修改
	 * @param u
	 * @param oldPsw
	 * @param newPsw
	 * @return 
	 */
	Boolean addOrUpdateUserPassWorld(User u, String oldPsw, String newPsw);
	
	/**
	 * 中介经纪人我的动态
	 * @return
	 */
	List<Mydynamic> findMidMydynamic(User user);
	
	/**
	 * 中介经纪人我的动态改已读
	 * @return
	 */
	void updateMidMydynamic(User user,Integer dynamicId);
	
	/**
	 * 中介经纪人我的动态未读数量显示
	 * @return
	 */
	int toGetMydynamicNotReadNum(User user);
	
	/**
	 * 经理今日接访未登记
	 * @param user
	 * @return
	 */
	List<VisitRecordsDTO> findToadyVisitRecords(String time,User user)throws ParseException;
	
	/**
	 * 案场今日备案数量和备案为到访数量
	 * @param todayTime
	 * @param user
	 * @return
	 */
	Map<String, String> findTodayGuideRecords(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 经理_任务_带盘客
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Integer> findCheckCustomerNum(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 案场备案详情
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<GrDto> findGuideRecordsDetail(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 接访排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 */
	List<RankingDto> findVisitRanking(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 认购排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<RankingDto> findRengouRanking(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 签约排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<RankingDto> findContractRanking(String startTime,String endTime,User user)throws ParseException;

	/**
	 * 成交转换率排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<RankingDto> findSlewRateRanking(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 业务报表_接访_______
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findVisitStatement(String startTime,String endTime,User user)throws ParseException;
	

	/**
	 * 业务报表_成交________ 
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findDealStatement(String startTime,String endTime,User user)throws ParseException;
	
	
	/**
	 * 业务报表_外场________ 
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findOutFieldStatement(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 生成城市session
	 * @param user
	 * @return
	 */
	CitySessionDTO findCityIntoSession(User user);
	
	
	/**
	 * 生成城市session
	 * @param cityId
	 * @return
	 */
	CitySessionDTO findCityIntoSessionByCityId(String cityId,User user);
	
	/**
	 * 经理_任务_带盘客页面
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<AllCheckedCustomerDTO> findAllCheckedCustomer(String time,User user)throws ParseException;
	
	/**
	 * 经理点评
	 * @param projectCustomerId
	 * @throws Exception 
	 */
	void updateProjectCustomerByManager(Integer visitNo,User user,String agentId,String projectCustomerId,String customerComment,String describe,String commitSpeed,String customerAspiration,String customerInfo,String isAgree) throws Exception;
	
	
	/**
	 * 案场客户归属
	 * @param projectCustomerId
	 * @param user
	 * @return
	 */
	Map<String, String> findCustomerAffiliation(String projectCustomerId,User user);
	
	/**
	 * 经理收藏的标签___________
	 * @param user
	 * @return
	 */
	List<ManagerOwnAnalyse> findManagerOwnAnalyse(User user);
	
	/**
	 * 经理新增收藏标签
	 * @param user
	 */
	void updateOneManagerOwnAnalyse(User user,String chartDataIds);
	
	/**
	 * 经理删除标签
	 * @param user
	 * @param categoryId
	 */
	void updateManagerOwnAnalyse(User user,String chartDataIds);
	
	
	/**
	 * 经理_分析_客户接待成效
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findReceptionByManager(String startTime,String endTime,User user,String chartDataId)throws ParseException;
	
	/**
	 * 观察首访有效率
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @param chartDataId
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findReceptionByCustomerFirstVisit(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	/**
	 * 经理_分析_新老客户通道
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findNewAndOldCustomerPassagewayInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;
	/**
	 * 分页查询用户
	 * @param status 状态
	 * @param page 当前页
	 * @param count 显示条数
	 * @return
	 */
	List<Map<String, Object>> findAllUserByPage(String status, int page, int count);
	/**
	 * 查询用户条数
	 * @param status 状态
	 * @return
	 */
	int findAllUserNum(String status);

	
	/**
	 * 经理_分析_观察指定接访
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeAppointCustomerReceptionInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_观察替接____
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeReplaceCustomerReceptionInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;
	
	
	/**
	 * 经理_分析_接访时长_________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeCustomerReceptionTimeInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_认购货值分析_____________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeCustomerContractRecordsInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_观察客户签约__________________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeCustomerHaveDealInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_签约货值分析______________________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeCustomerHaveDealMoneyInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_成交量分析_____________________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeCustomerHaveDealNumberInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_销售进度观察____________________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeSaleScheduleInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_报备到访情况___________________________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeCustomerGuideRecprdsToVisitInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

		
	/**
	 * 新增分析标签__________________________
	 * @param mcd
	 * @param user
	 */
	void addManaerChartData(ManaerChartData mcd,User user, MultipartFile pic) throws Exception;
	
	/**
	 * 分析标签集合_________________________
	 * @param mcd
	 * @param user
	 * @return
	 */
	List<ManaerChartData> findManaerChartDataList(User user,String isUseful);

	/**
	 * 获取分析类目集合___________
	 * @param user
	 * @return
	 */
	List<AnalysisCategory> findAnalysisCategoryList(User user);

	/**
	 * 删除_分析_标签
	 * @param chartDataId
	 */
	void updateManaerChartData(String chartDataId);

	/**
	 * 获取项目id集合______________________
	 * @param user
	 * @return
	 */
	List<Project> findProjectIdList(User user);

	/**
	 * 新增分析_类目_______________________
	 * @param categoryName
	 * @param chartDataIdArr
	 * @param projectId
	 * @param priority
	 * @param isMade
	 * @param user
	 */
	void addAnalysisCategory(String categoryName,String chartDataIdArr,String projectId,String priority,String isMade,User user);
	
	/**
	 * 经理_分析_外场成交分析___________________________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeOutCustomerDealInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_观察老客户接访_____________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeOldCustomerVisitInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	
	/**
	 * 经理_分析_储客分析_________________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findReserveCustomerInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	/**
	 * 观察客户认购_______________________
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findAllCustomerContractRecordInfo(String startTime,String endTime,User user,String chartDataId)throws ParseException;

	/**
	 * 更新类目
	 * @param categoryId
	 */
	void updateAnalysisCategory(Integer categoryId,String categoryName,String haveLabel,String projectId,String priority,String isMade);

	/**
	 * 更新儿子_)))))))))))
	 * @param chartDataId
	 * @param mcd
	 */
	void updateManaerChartData(ManaerChartData mcd, MultipartFile file)throws Exception;

	/**
	 * app经理分析类目显示_________________
	 * @param user
	 * @return
	 */
	List<AnalyzeTagsDTO> findMcdList(User user);
	
	/**
	 * 分析按钮控制器______________________________________
	 * @param chartDataId
	 * @return
	 */
	ManaerChartData findMCd(String chartDataId);
	
	/**
	 * 根据ID 查询儿子
	 * @param chartDataId id
	 * @return
	 */
	ManaerChartData findManaerChartById(String chartDataId);
	
	/**
	 * 关联按钮集合_________________
	 * @param chartDataId
	 * @return
	 */
	List<ManaerChartData> findRelationFenXiInfo(String chartDataId);
	
	/**
	 * 发送验证码_______________
	 * @param user
	 * @return
	 */
	String toSentMessageToChangePsd(User user) throws IOException ;
	
	/**
	 * 修改密码_____________
	 * @param newVerification
	 * @param password
	 * @param truepassword
	 * @param verificationCode
	 */
	Map<String, Object> updatePasswordNew(User user,String newVerification, String password, String truepassword,String verificationCode);

	/**
	 * 通过项目获取某一个案场助理
	 * @param proId 
	 * @return
	 */
	Map findEngineerByProId(String proId);

	/**
	 * 通过时间删除导入数据
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 */
	Map dropImportDataByTime(String start, String end,String proId) throws Exception;

	/**************************************************客户端数据模拟********************************************************/
	
	Map addOrUpdateUserFromClient(User user, String role,String shopName);
	
	/**
	 * 通过用户名查询User
	 * @param shopAgentName
	 * @param shopAgentPhone 
	 * @return
	 */
	User findUserByName(String shopAgentName, String shopAgentPhone,String proId);
	
	
	String[] findjingweiduByIp(User user,HttpServletRequest request) throws Exception;

	User findUserByProperty(String agentPhone, String proId);

	/**
	 * 置业顾问分组
	 * @param userId
	 * @param group
	 */
	void addOrUpdateUser(User userId, Integer group);

	//Map addOrUpdateUserFromClient1(User user, String roleSign);
	/**
	 * 查询指定个数指定案场置业顾问(姓名，id)
	 * @param num 为0或者null 为查询该案场所有置业顾问
	 * @param projectId 案场Id
	 * @return
	 */
	List<User> findProjectAgentByLimit(Integer num,String projectId);
}
