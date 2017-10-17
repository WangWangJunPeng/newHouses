package com.sc.tradmaster.service.project;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseDetails;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.MiniMessageWaitSend;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBuilding;
import com.sc.tradmaster.bean.ProjectBuildingUnit;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectZone;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.SystemChargeDefinition;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.project.impl.dto.HouseJson;
import com.sc.tradmaster.service.project.impl.dto.HouseResult;
import com.sc.tradmaster.service.project.impl.dto.NewOneShopDTO;
import com.sc.tradmaster.service.project.impl.dto.ProjectHouseInfo;
import com.sc.tradmaster.service.project.impl.dto.ProjectZoneDto;
import com.sc.tradmaster.service.project.impl.dto.ShopAndUserNameDTO;
import com.sc.tradmaster.service.project.impl.dto.ShopsNewDTO;
import com.sc.tradmaster.service.project.impl.dto.UserDTO;
import com.sc.tradmaster.utils.Page;

/**
 * 2017-01-08
 * 
 * @author grl
 *
 */
public interface ProjectService {
	/**
	 * 添加或更新项目
	 * 
	 * @param p
	 * @param u
	 */
	public void addOrUpdateProject(Project p, User u);

	/**
	 * 通过id删除该项目
	 * 
	 * @param id
	 */
	public void dropProjectById(String id);

	/**
	 * 通过id查询该项目
	 * 
	 * @param id
	 * @return
	 */
	public Project findProjectById(String id);

	/**
	 * 中介端房源首页按条件搜索项目
	 */
	List findProjectByConditions(User user, String[] selectNames, String[] selectValues,CitySessionDTO  csdto)throws IOException;

	/**
	 * 添加预售证信息
	 * 
	 * @param u
	 * @param idManageNum
	 * @param pic
	 * @throws Exception 
	 */
	public void addIdManageInfo(User u, String idManageNum, MultipartFile pic) throws Exception;

	/**
	 * 通过项目id和其所在数组中的下标将其删除
	 * 
	 * @param pId
	 * @param index
	 * @throws Exception
	 */
	public void dropIdManageByProPicIndex(String pId, int index) throws Exception;

	/**
	 * 更新当前预售证信息
	 * 
	 * @param u
	 * @param idManageNum
	 * @param pic
	 * @param index
	 * @throws Exception 
	 */
	public void updateIdManageInfo(User u, String idManageNum, MultipartFile pic, int index) throws Exception;

	/**
	 * 项目详情app(中介端)
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> findProjectByProperty(User user,String projectId);

	/**
	 * 项目详情app(置业顾问端)
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> findProjectByProperty(User user);

	/**
	 * 中介端app我的业务
	 */
	List findMidBusiness(User user, String startTime, String endTime, String projectName) throws ParseException;

	/**
	 * 置业顾问app端我的业务
	 */
	Map<String, Object> findSaleBusiness(User user);

	/**
	 * 置业顾问首页信息,今日待办List
	 * 
	 * @param user
	 * @return
	 */
	List findInfoAboutProForAgent(User user, String title);

	/**
	 * 置业顾问首页信息,今日待办counts
	 * 
	 * @param user
	 * @return
	 */
	public Map findCountInfo(User user);

	/**
	 * 置业顾问首页信息,今日案场counts
	 * 
	 * @param user
	 * @return
	 */
	public Map findTodayProCounts(User user);

	/**
	 * 置业顾问首页信息,今日案场list
	 * 
	 * @param user
	 * @return
	 */
	public List findTodayProList(User user, String title);

	/**
	 * 案场助理-帐号管理
	 * 
	 * @param user
	 * @param page 
	 * @return
	 */
	public List findAllAgentByProId(User user, String selectStatus, Page page);

	/**
	 * 今日门店 - counts
	 * 
	 * @param user
	 * @return
	 */
	public Map findTodayShopCounts(User user);

	/**
	 * 今日门店 - list
	 * 
	 * @param user
	 * @return
	 */
	public List findTodayShopList(User user, String title);

	/**
	 *
	 * 今日待办 - 查看认购申请
	 * 
	 * @param user
	 * @param houseNum
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> findCurrentHouseBuyApply(User user, Integer houseNum) throws Exception;

	/**
	 * 今日待办 - 认购申请 审核
	 * 
	 * @param u
	 * @param recordNo
	 * @throws IOException
	 * @throws Exception
	 */
	public void updateAgreeBuyApply(User u, Integer recordNo, String checkReson) throws Exception;

	/**
	 * 今日待办 - 认购申请 拒绝
	 * 
	 * @param u
	 * @param recordNo
	 * @param reason
	 * @throws IOException
	 */
	public void updateRefuseBuyApply(User u, Integer recordNo, String reason) throws IOException;

	/**
	 * 成交业务 购买申请
	 * 
	 * @param user
	 * @param doSign
	 * @return
	 * @throws ParseException
	 */
	public List findBuyApplyAppointList(User user, String doSign) throws ParseException;

	/**
	 * 成交业务 定金到款
	 * 
	 * @param user
	 * @param doSign
	 * @return
	 */
	public List findGetBargainAppointList(User user, String doSign) throws ParseException;

	/**
	 * 成交业务 签约确认
	 * 
	 * @param user
	 * @param doSign
	 * @return
	 */
	public List findSignAppointList(User user, String doSign) throws ParseException;

	/**
	 * 定时监控器获取同意订购未打款的信息
	 * 
	 * @return
	 */
	public List<MiniMessageWaitSend> findMiniMessageWaitSendByHQL();

	/**
	 * 成交业务 定金到款 未到款确认
	 * 
	 * @param user
	 * @param recordNo
	 * @throws IOException
	 */
	public void addOrUpdateContractRecords(User user, Integer recordNo) throws IOException;

	/**
	 * 成交业务 签约确认 未确认的签约 确认操作
	 * 
	 * @param user
	 * @param recordNo
	 */
	public void addorUpdateContractRecordsForSign(User user, Integer recordNo);

	/**
	 * 对账单 - list
	 * 
	 * @param user
	 * @param startTime
	 * @param endTime
	 */
	public List findContracRecordsBill(User user, String startTime, String endTime);

	/**
	 * 对账单 - list中结款操作
	 * 
	 * @param user
	 * @param recordNo
	 * @param enterSign
	 */
	public void addorUpdateContractRecordsForSignEnterPayMoney(User user, Integer recordNo, String enterSign,
			String desc);

	/**
	 * 对账单 - list中取消结款操作
	 * 
	 * @param user
	 * @param recordNo
	 * @param cancelSign
	 * @param desc
	 */
	public void addorUpdateContractRecordsForSignCancelPayMoney(User user, Integer recordNo, String cancelSign,
			String desc);

	/**
	 * 对账单 - list 中介端
	 * 
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param projectId
	 * @return
	 */
	public void findShoperContracRecordsBill(User user, String startTime, String endTime, Page page, String projectId);

	/**
	 * 对账单 - list到款操作 中介端
	 * 
	 * @param user
	 * @param recordNo
	 * @param desc
	 * @param doSingle
	 *            （取消到款、确认到款）
	 */
	public void addorUpdateContractRecordsForShoperEnterReceiveMoney(User user, Integer recordNo, String desc,
			String doSingle);

	/**
	 * 对账单 list 经纪人数据排行
	 * 
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param projectId
	 * @return
	 */
	public void findAllMediRelativeDataInfo(User user, String startTime, String endTime, Page page, String projectId);

	/**
	 * 对账单 list 项目信息排行
	 * 
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param projectId
	 * @return
	 */
	public void findAllProRelativeDataForMediInfo(User user, String startTime, String endTime, Page page, String projectId);

	/**
	 * 获取修改用户的信息
	 * 
	 * @return
	 */
	public UserDTO findUserById(String userId);

	/**
	 * 业务 门店list
	 * 
	 * @param user
	 * @param cusOrProName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param page
	 * @return
	 */
	public void findCusForShopBusiness(User user, String cusOrProName, String startTime, String endTime, String status, Page page);

	/**
	 * 经纪人管理 list
	 * 
	 * @param user
	 * @param selectStatus
	 * @return
	 */
	public List findAllMediByShopId(User user, String selectRole, String enOrDisable);

	/**
	 * 陈冬华 2017-03-06 通过城市名查找所有的项目名
	 * 
	 * @param city
	 * @return
	 */
	public List findAllProjectsNameByCity(String city);

	/**
	 * 
	 * 添加佣金定义
	 * 
	 * @param systemChargeDefinition
	 */
	void addPlatform(SystemChargeDefinition s);

	/**
	 * 找所有定义佣金的项目
	 * 
	 * @param projectId
	 * @return
	 */
	List selectAllPlatform();
	
	/**
	 * 将传送过来的xxxxxx-xxxxxx-xxxxxx格式的区号转换成xx省xx市xx区
	 * @param cityNum
	 * @return
	 */
	String findCityNameByCityNum(String cityNum);

	/**
	 * 
	 * 子方法，便利list里面的shop 然后添加到shopAndUserNameDTO里面
	 * 
	 * @param shopList
	 * @return
	 */
	public List<ShopAndUserNameDTO> findShopAndUserNameDTO(List<Shops> shopList);

	/**
	 * 显示所有审核的项目
	 * 
	 * @return
	 */
	public void findAllReviewedShops(Page page, Integer applyId);

	/**
	 * 
	 * 按shopId查找shop信息
	 * 
	 * @param shopId
	 * @return
	 */
	public Shops findReviewdShopById(Integer shopId);

	/**
	 * 
	 * 添加审核意见
	 * 
	 * @param shopId
	 * @param tag
	 */
	public String addReviewShopById(User u, Shops s, Integer tag);

	void addOrUpdateFirstProject(Project project, User u);

	List<Project> findAllBySystem();

	/**
	 * 根据shopId删除该门店
	 * @param shopId
	 */
	void dropShopByShopId(Integer shopId);

	public List<ContractRecords> findConRecListByHql(String conrecHql);

	public void updateConRec(ContractRecords cr);
	
	
	
	/**
	 * 查找本月中介客户的分销信息
	 * @return
	 */
	List<ContractRecords> findThisMonthShopownerRecord(User user);
	
	/**
	 * 获取本月中介客户带看记录信息
	 * @return
	 */
	List<ContractRecords> findThisMonthAgentDistribute(User user);
	
	/**
	 * 获取本月所有的中介认购记录
	 * @return
	 */
	List<ContractRecords> findThisMonthAllMediInfo(User user);
	
	/**
	 * 获取本月中介认购记录中“申请”和“同意”
	 * @return
	 */
	List<ContractRecords> findThisMonthApplyAndAgreeContract(User user);
	
	/**
	 * 获取本月中介签约记录
	 * @param user
	 * @return
	 */
	List<ContractRecords> findThisMonthSignedInfo(User user);
	
	/**
	 * 
	 * 中介本月新增客户
	 * @param user
	 * @return
	 */
	List<ProjectCustomers> findNewCustomerInThisMonth(User user);
	
	/**
	 * 本月中介备案
	 * @param user
	 * @return
	 */
	List<GuideRecords> findGuideRecordInThieMonth(User user);
	/**
	 * 认筹客户 list
	 * @param user
	 * @return
	 */
	public List findConfessOfCurrentUser(User user);

	/**
	 * 短信定时发送后，更新短信发送状态
	 * @param mmws 
	 */
	public void addOrupdateMessageSendStatus(MiniMessageWaitSend mmws);

	/**
	 * 定时发送短信，通过id获取当前数据
	 * @param id
	 * @return 
	 */
	public MiniMessageWaitSend findMessageById(Integer id);

	/**
	 * 判断基本信息是否填充完整
	 * @param user
	 * @return
	 */
	public Map basicInfoIsFull(User user);
	
	/**
	 * 添加超时打款动态
	 * @param outTimeDynamic
	 */
	public void addorUpdateMyDynamic(Mydynamic outTimeDynamic);

	/**
	 * 
	 * @param pcq 
	 * @return
	 */
	public Map findAllProAndShopInfo(String pcq);

	/**
	 * 判断当前预售证是否使用
	 * @param urlList
	 * @return
	 */
	public List<Map> findCurrentIdManageBeenUsed(List<Map<String, String>> urlList);

	/**
	 * 对账单 中介端 案场菜单
	 * @return
	 */
	public List<Map<String, String>> findAllProjectForMenu();

	/**
	 * 通过id获取对象
	 * @param aOrmId
	 * @return
	 */
	public User findObjById(String aOrmId);

	/**
	 * 数据导入工具
	 * @param txtFile
	 * @return
	 * @throws IOException 
	 * @throws Exception 
	 */
	public Map<String, Object> addorUpdateByTxtFile(MultipartFile[] txtFile,String city,String proId) throws Exception;

	/**
	 * 获取所有项目
	 */
	public List<Map<String, Object>> findAllProForMenu();

	/**
	 * 项目搜索门店__________________________
	 * @param user
	 * @return
	 */
	List<ShopsNewDTO> findNewShopsList(User user,String cityId,String address);
	
	/**
	 * 项目搜索门店list____
	 * @param user
	 * @param addressOne
	 * @param addressTwo
	 * @param start
	 * @param limit
	 * @param quId
	 * @param address
	 * @return
	 */
	void findOneAreaShopsList(User user,String addressOne,String addressTwo,Page page,String quId,String sign,String address);

	List<Shops> findOneAreaShopsListInMap();
	
	/**
	 * 返回单个门店信息
	 * @param user
	 * @param shopId
	 * @return
	 */
	List<NewOneShopDTO> findOneShop(User user,Integer shopId);
	
	/**
	 * 返回单个门店信息ditu_______
	 * @param user
	 * @param shopId
	 * @return
	 */
	List<NewOneShopDTO> findOneShopByMap(User user,String jingdu,String weidu);
	
	/**
	 * 根据城市名字返回城市id
	 * @param cityName
	 * @return
	 */
	String findCityIdByCityName(String cityName);

	/**
	 * 平台段地图  筛选获取项目
	 */
	void findAllProOrShopsByProperties(String maxLongitudes, String minLongitudes, String maxLatitudes,
			String minLatitudes, String[] tags, String[] houseTags, String projectId, Page page);

	/**
	 * 获取门店数量_______________________
	 * @param user
	 * @param addressOne
	 * @param addressTwo
	 * @param quId
	 * @param sign
	 * @return
	 */
	Map<String, Object> findGoToGetAllShopsList(User user,String addressOne,String addressTwo,String quId,String sign);

	/**
	 * 客户端签约确认
	 * @param agent
	 * @param user
	 * @param enterBuyCusPhone
	 * @param proId
	 * @param enterBuyHouseNum
	 */
	public void addorUpdateContractRecordsForSign(User agent, User user, String enterBuyCusPhone, String proId,
			Integer enterBuyHouseNum);

	/**
	 * 申请注册项目
	 * @param project
	 * @param user
	 * @return 
	 */
	public String addRegisterProject(Project project, User user);
	/**
	 * 随机生成公告
	 * @return
	 */
	public Set<String> findRandAnnouncement();
	/**
	 * 添加案场分区
	 * @param pz
	 */
	public boolean addProjectZone(ProjectZone pz);
	/**
	 * 修改案场分区
	 * @param zoneId
	 */
	public boolean updateProjectZone(ProjectZone pz);
	/**
	 * 添加楼栋
	 * @param pd
	 * @return
	 */
	public boolean addProjectBuilding(ProjectBuilding pd);
	/**
	 * 修改楼栋
	 */
	public boolean updateProjectBuilding(ProjectBuilding pb,String projectId);
	/**
	 * 删除分区
	 * @param zoneId
	 * @return
	 */
	public boolean updateFordeleteProjectZone(Integer zoneId);
	/**
	 * 删除楼栋
	 * @param buildingId
	 * @param projectId
	 * @return
	 */
	public boolean updateForDeleteProjectBuilding(Integer buildingId, String projectId);
	/**
	 * 房源生成
	 * @param floorNum 
	 * @param houseInfoList
	 * @param unitInfoMap
	 * @param buildingId
	 * @param zoneId
	 * @param housePropertyType
	 * @param projectId
	 * @param propertyYear
	 * @param propertyExpireTime
	 * @param sellType
	 */
	public void addBatchHouse(Integer floorNum, List<HouseJson> houseInfoList, Map<String, String> unitInfoMap,
			Integer buildingId, Integer zoneId, Integer housePropertyType, String projectId, String propertyYear,
			String propertyExpireTime, Integer sellType);
	/**
	 * 查询楼栋的所有房源信息
	 * @param projectId
	 * @param zoneId
	 * @param buildingId
	 * @return
	 */
	public HouseResult findBuildingAllHouses(Integer buildingId);
	/**
	 * 根据项目Id查询户型
	 * @param projectId
	 * @return
	 */
	public List<HouseType> findHouseTypeByProjectId(String projectId);
	/**
	 * 合并房源操作
	 * @param firstHouseNum
	 * @param houseNum
	 */
	public void updateHouseInfoForMerge(Integer firstHouseNum, Integer houseNum);
	/**
	 * 房源拆分
	 * @param houseNum
	 */
	public void updateHouseInfoForSplit(Integer houseNum);
	/**
	 * 查询所有分区
	 * @param projectId
	 * @return
	 */
	public List<ProjectZone> findAllZoneByProjectId(String projectId);
	/**
	 * 查询分区下所有的楼栋
	 * @param zoneId
	 * @return
	 */
	public List<ProjectBuilding> findAllBuildingByZoneId(Integer zoneId);
	/**
	 * 导出表格（房源生成）
	 * @param buildingId
	 * @return
	 */
	public List<House> createHousesExcel(User user,Integer districtId, Integer buildingId,Integer unitId,Integer houseNum);
	/**
	 * 分页查询房源
	 * @param houseKind
	 * @param houseStatus
	 * @param houseTypeId
	 * @param isSee
	 * @param page
	 * @param num
	 * @param projectId
	 */
	public Map<String,Object> searchProjectHousesAndLimit(Integer houseKind, Integer houseStatus, String houseTypeId, Integer isSee,
			int page, int num, String projectId);
	/**
	 * 根据Id查询房源
	 * @param houseNum
	 * @return
	 */
	public House findHouseById(Integer houseNum);
	/**
	 * 保存修改
	 * @param house
	 */
	public void update_editHouse(House house);
	/**
	 * 批量上架1，批量下架2，批量删除3，批量对经纪人可见4, 批量对经纪人不可见5
	 */
	public void update_editBatchHouse(Integer[] houseNums,int flag);
	/**
	 * 一房一档(新)---毛肖瑶
	 * @param houseNum
	 * @return
	 */
	public ProjectHouseInfo findHouseDetails(Integer houseNum);
	/**
	 * 查询房源详细信息
	 * @param houseNum
	 * @return
	 */
	public HouseDetails findHouseJvtiInfo(Integer houseNum);
	/**
	 * 查询所有单元
	 * @param buildingId
	 * @return
	 */
	public List<ProjectBuildingUnit> findUnitByBuildingId(Integer buildingId);

	public List<ProjectZoneDto> findAllZoneAndBuilding2DTO(String projectId);
}
