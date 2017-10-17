package com.sc.tradmaster.controller.project;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBuilding;
import com.sc.tradmaster.bean.ProjectBuildingUnit;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectZone;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.SystemChargeDefinition;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.project.impl.dto.HouseJson;
import com.sc.tradmaster.service.project.impl.dto.HouseResult;
import com.sc.tradmaster.service.project.impl.dto.NewOneShopDTO;
import com.sc.tradmaster.service.project.impl.dto.ProjectHouseInfo;
import com.sc.tradmaster.service.project.impl.dto.ProjectZoneDto;
import com.sc.tradmaster.service.project.impl.dto.ShopsNewDTO;
import com.sc.tradmaster.service.project.impl.dto.UserDTO;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.ExcelType;
import com.sc.tradmaster.utils.Page;

/**
 * 2017-01-08
 * 
 * @author grl
 *
 */
@Controller("projectController")
@Scope("prototype")
public class ProjectController extends BaseController {

	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "houseService")
	private HouseService houseService;

	/**
	 * 案场助理首页跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_pro_index")
	public String toProIndex() {
		return "project/projectIndex";
	}

	/**
	 * 门店店长首页跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_store_index")
	public String toShopStoreIndex() {
		return "publicpage/shopsPublicPage";
	}

	// 得到店长首页页面
	@RequestMapping("/to_store_shop_index")
	public String toShopStoreHex() {

		return "login/store_index";
	}

	/**
	 * 案场信息维护页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_pro_baseInfo")
	public String toProBaseInfo() {
		return "project/basicInformation";
	}

	/**
	 * 预售证管理页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_idManage")
	public String toIdManage() {
		return "project/idManage";
	}

	/**
	 * 成交业务页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_trade_business_page")
	public String toTradeBusinessPage() {
		return "project/tradeBusiness";
	}

	/**
	 * 职业顾问对账单页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_agent_bill_page")
	public String toAgentSureBillPage() {
		return "project/sureBill";
	}

	/**
	 * 门店对账单页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("to_shoper_bill_page")
	public String toShoperSureBillPage() {
		return "project/shopSureBill";
	}

	/**
	 * 案场信息维护-获取案场信息控制器
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_msg_pro")
	public void toMessageProtect() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && u.getParentId() != "") {
			Project project = projectService.findProjectById(u.getParentId());
			System.out.println(project);
			this.outObjectString(project, null);
		}
	}

	/**
	 * 案场信息维护-数据保存控制器
	 * 
	 * @param project
	 * @param province
	 * @param market
	 * @param area
	 * @param isSave
	 * @param pic
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/save_pro_info")
	public ModelAndView addOrUpdateBaseInfo(Project project, String province, String market, String area,
			String isSave) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 设置省、市、区
		project.setCity(province + "-" + market + "-" + area);
		projectService.addOrUpdateProject(project, user);
		ModelAndView mv = new ModelAndView();
		if (isSave.equals("保存")) {
			mv.setViewName("project/basicInformation");
		} else {
			mv.setViewName("project/effectPicture");
		}
		return mv;
	}

	/**
	 * 添加或更新预售证信息
	 * 
	 * @param idManageNum
	 * @param pic
	 * @param isSave
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/add_or_update_id_mange_for_pro")
	public String addOrUpdateIdManage(String idManageNum, MultipartFile pic, String isSave) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("")) {
			projectService.addIdManageInfo(u, idManageNum, pic);
		}
		if (isSave.equals("保存")) {
			return "redirect:/to_idManage";
		} else {
			return "redirect:/to_buyRule";
		}
	}

	/**
	 * 修改当前预售证信息
	 * 
	 * @param idManageNum
	 * @param pic
	 * @param index
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updata_curren_idmanage_info")
	public String updateIdManage(String idManageNum, MultipartFile pic, int index) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("") && index >= 0) {
			projectService.updateIdManageInfo(u, idManageNum, pic, index);
		}
		return "redirect:/to_idManage";
	}

	/**
	 * 预售证信息列表
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/list_id_manage_infos")
	public void listIdManage() throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("")) {
			Project pro = projectService.findProjectById(u.getParentId());
			String urlStr = pro.getPresalePermissionURL();
			List<Map<String, String>> urlList = this.outStringToList(urlStr);
			List<Map> list = projectService.findCurrentIdManageBeenUsed(urlList);
			this.outListString(list);
		}
	}

	/**
	 * 通过项目id和预售证所在数组中的下标将其删除
	 * 
	 * @param pId
	 * @param index
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete_id_manage_pro_pic_index")
	public void deleteIdManageByProPicIndex(int index) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 执行后台逻辑
		if (u.getParentId() != null && !u.getParentId().equals("")) {
			projectService.dropIdManageByProPicIndex(u.getParentId(), index);
			this.outString("");
		}
	}

	/**
	 * 通过下标获取当前预售证信息
	 * 
	 * @param index
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/curren_id_manage_info")
	public void currentIdManage(int index) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		// 判断该用户是否有管理的案场
		if (u.getParentId() != null && !u.equals("")) {
			Project pro = projectService.findProjectById(u.getParentId());
			String urlStr = pro.getPresalePermissionURL();
			List<Map<String, String>> urlList = this.outStringToList(urlStr);
			Map<String, String> currenIM = urlList.get(index);
			this.outObjectString(currenIM, null);
		}
	}

	/**
	 * 今日待办-counts
	 */
	@ResponseBody
	@RequestMapping("/agent_page_today_need_doing")
	public void agentFristPageForCounts() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map mapCount = projectService.findCountInfo(user);
			this.outObjectString(mapCount, null);
		}

	}

	/**
	 * 今日待办-list
	 * 
	 * @param title
	 */
	@ResponseBody
	@RequestMapping("/agent_page_list")
	public void agentFristPageForList(Integer num) {
		String title = null;
		if (num == 0) {
			title = "enterBuy";
		} else if (num == 1) {
			title = "getMoney";
		} else if (num == 2) {
			title = "waitSign";
		} else if (num == 3) {
			title = "waitCash";
		}
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			List listInfo = projectService.findInfoAboutProForAgent(user, title);
			this.outListString(listInfo);
		}
	}

	/**
	 * 今日待办 - list 查看 跳转控制器
	 */
	@RequestMapping("/to_see_buy_apply")
	public ModelAndView toseeBuyApplyPage(Integer houseNum) {
		ModelAndView mv = new ModelAndView("project/purchaseApply");
		mv.addObject("houseNum", houseNum);
		return mv;
	}

	/**
	 * 今日待办 - 查看认购申请
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/get_see_buy_apply_date")
	public void seeBuyApply(Integer houseNum) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map<String, Object> map = projectService.findCurrentHouseBuyApply(user, houseNum);
			this.outObjectString(map, null);
		}

	}

	/**
	 * 今日待办 - 认购申请 审核
	 */
	@ResponseBody
	@RequestMapping("agree_check_buy_apply_date")
	public void agreeCheckBuyApply(Integer recordNo, String checkReson) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		try {
			projectService.updateAgreeBuyApply(u, recordNo, checkReson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 今日待办 - 认购申请 拒绝
	 */
	@RequestMapping("refuse_buy_apply_date")
	public String refuseBuyApply(Integer recordNo, String reason) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		try {
			projectService.updateRefuseBuyApply(u, recordNo, reason);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/to_trade_business_page";
	}

	/**
	 * 今日案场 - counts
	 */
	@ResponseBody
	@RequestMapping("/agent_today_pro_counts")
	public void agentFristPageTodayProCounts() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map mapCount = projectService.findTodayProCounts(user);
			this.outObjectString(mapCount, null);
		}
	}

	/**
	 * 今日案场 - list
	 * 
	 * @param title
	 */
	@ResponseBody
	@RequestMapping("/agent_today_pro_list")
	public void agentFristPageTodayList(String title) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			List listInfo = projectService.findTodayProList(user, title);
			this.outListString(listInfo);
		}
	}

	/**
	 * 帐号管理页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_account_manager_page")
	public String toAccountsManagerPage() {
		return "accountsNumManage/accountManage";
	}

	/**
	 * 用户信息编辑页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_edit_account_page")
	public ModelAndView toEditAccountPage(String userId) {
		ModelAndView mv = new ModelAndView();
		if (userId != null && !userId.equals("")) {
			UserDTO user = projectService.findUserById(userId);
			mv.setViewName("accountsNumManage/editAccount");
			mv.addObject("user", user);
		}
		return mv;
	}

	/**
	 * 帐号管理 list
	 */
	@RequestMapping("/accountsNum_agent_info_list")
	public void accountsNumManageList(String selectStatus) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		Page page = new Page();
		if (user != null) {
			List listAllAgentInfo = projectService.findAllAgentByProId(user, selectStatus, page);
			this.outListString(listAllAgentInfo);
		}
	}

	/**
	 * 帐号管理 新增页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/goto_accountsnum_manage_page")
	public String toAddEnginerOrAgentPage() {
		return "accountsNumManage/addAccount";
	}

	/**
	 * 帐号管理 新增、更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/account_manager_to_add_or_update")
	public Object saveOrUpdateEnginerOrAgent(User user, String rightSign, String returnSign) {
		// 获取当前登录用户对象
		User loginuser = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(loginuser.getUserId());
		// 调用后台业务逻辑
		Map map = userService.addOrUpdateUser(u, user, rightSign);
		/*
		 * if (returnSign != null && returnSign.equals("提交后继续新增下一个")) {
		 * map.put("url", "goto_accountsnum_manage_page"); } else {
		 * map.put("url","to_account_manager_page"); }
		 */
		return map;
	}

	/**
	 * 帐号管理 list 密码重置、用户启用、用户删除
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/account_manager_to_update_userInfo")
	public void setUpUsers(User user, String doSign) {
		userService.updateUserInfo(user, doSign);
	}

	/**
	 * 经纪人管理页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_medi_manager_page")
	public String toMediManagerPage() {
		return "projectCustomer/shopAgentManagent";
	}

	/**
	 * 经纪人管理 编辑页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_edit_Shop_manager_page")
	public ModelAndView toEditShopManagerPage(String userId) {
		ModelAndView mv = new ModelAndView();
		if (userId != null && !userId.equals("")) {
			UserDTO user = projectService.findUserById(userId);
			mv.setViewName("projectCustomer/shopAgentChange");
			mv.addObject("user", user);
			System.out.println(user);
		}
		return mv;
	}

	/**
	 * 经纪人管理 新增页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/goto_medi_manage_page")
	public String toAddOrMediPage() {
		return "projectCustomer/shopAddAgent";
	}

	/**
	 * 经纪人管理 list
	 */
	@RequestMapping("/medi_manage_info_list")
	public void mediManageList(String selectRole, String enOrDisable) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			List listAllAgentInfo = projectService.findAllMediByShopId(user, selectRole, enOrDisable);
			this.outListString(listAllAgentInfo);
		}
	}

	/**
	 * 经济人管理 新增、更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/medi_manager_to_add_or_update")
	public String saveOrUpdateMediOrAgent(User user, String rightSign, String returnSign) {
		// 获取当前登录用户对象
		User loginuser = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(loginuser.getUserId());
		// 调用后台业务逻辑
		userService.addOrUpdateMediUser(u, user, rightSign);
		// 根据请求跳转相应页面
		if ("提交后继续新增下一个".equals(returnSign)) {
			return "redirect:/goto_medi_manage_page";
		} else {
			return "redirect:/to_medi_manager_page";
		}

	}

	/**
	 * 今日门店 - counts
	 */
	@ResponseBody
	@RequestMapping("/shoper_today_shop_counts")
	public void shoperFristPageTodayProCounts() {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		if (user != null) {
			Map mapCount = projectService.findTodayShopCounts(user);
			this.outObjectString(mapCount, null);
		}
	}

	/**
	 * 今日门店 - list
	 */
	@ResponseBody
	@RequestMapping("/shoper_today_shop_list")
	public void shoperFristPageTodayList(Integer num) {
		String title = null;
		if (num == 0) {
			title = "enterbuy";
		} else if (num == 1) {
			title = "record";
		} else if (num == 2) {
			title = "guid";
		} else if (num == 3) {
			title = "willexpire";
		}
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user != null) {
			List list = projectService.findTodayShopList(user, title);
			this.outListString(list);
		}
	}

	/**
	 * 判断基本信息是否填写完整
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/basicInfoIsFull")
	public Object basecInfoIsFull() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = projectService.basicInfoIsFull(user);
		return map;
	}

	/**
	 * 成交业务 购买申请
	 * 
	 * @param doSign
	 */
	@ResponseBody
	@RequestMapping("/buy_apply_{doSign}_list")
	public void tradeBusinessForBuyApply(@PathVariable String doSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			List appointList = projectService.findBuyApplyAppointList(user, doSign);
			this.outListString(appointList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 定金到款
	 * 
	 * @param doSign
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_get_bargain_list")
	public void tradeBusinessForGetBargain(String doSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			List appointList = projectService.findGetBargainAppointList(user, doSign);
			this.outListString(appointList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 定金到款 未确认的到款 确认操作
	 * 
	 * @param recordNo
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_enter_bargain")
	public void tradeBusinessForEnterGetBargain(Integer recordNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			projectService.addOrUpdateContractRecords(user, recordNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 签约确认
	 * 
	 * @param doSign
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_sign_list")
	public void tradeBusinessForSign(String doSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			List appointList = projectService.findSignAppointList(user, doSign);
			this.outListString(appointList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成交业务 签约确认 未确认的签约 确认操作
	 * 
	 * @param recordNo
	 */
	@ResponseBody
	@RequestMapping("/trade_business_for_enter_sign")
	public void tradeBusinessForSignCheck(Integer recordNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForSign(user, recordNo);
	}

	/**
	 * 对账单 - list 案场助理端
	 * 
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("/agent_check_bill_list")
	public void agentCheckBill(String startTime, String endTime) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List billList = projectService.findContracRecordsBill(user, startTime, endTime);
		this.outListString(billList);
	}

	/**
	 * 对账单 - list中结款操作
	 * 
	 * @param desc
	 *            确认到款描述
	 * @param enterSign
	 *            确认到款端标识（平台、中介）
	 */
	@RequestMapping("/agent_check_bill_list_enter")
	public String agentCheckBillEnter(String desc, Integer recordNo, String enterSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForSignEnterPayMoney(user, recordNo, enterSign, desc);
		return "redirect:/to_agent_bill_page";
	}

	/**
	 * 对账单 - list中取消结款操作
	 * 
	 * @param desc
	 *            确认到款描述
	 * @param enterSign
	 *            确认到款端标识（平台、中介）
	 */
	@RequestMapping("/agent_check_bill_list_cancel")
	public String agentCheckBillCancelEnter(String desc, Integer recordNo, String cancelSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForSignCancelPayMoney(user, recordNo, cancelSign, desc);
		return "redirect:/to_agent_bill_page";
	}

	/**
	 * 对账单 - list 中介端
	 * 
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping("/shoper_check_bill_list")
	public void shoperCheckBill(String startTime, String endTime, Integer start, Integer limit, String projectId) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);

		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findShoperContracRecordsBill(user, startTime, endTime, page, projectId);
		this.outPageString(page);
	}

	/**
	 * 对账单 - list 中介端 案场菜单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_project_menu")
	public Object getProjectMenuData() {
		List<Map<String, String>> proMenu = projectService.findAllProjectForMenu();
		return proMenu;
	}

	/**
	 * 对账单 - list中到款操作
	 * 
	 * @param desc
	 *            确认到款描述
	 */
	@ResponseBody
	@RequestMapping("/shoper_check_bill_list_enter_or_cancel")
	public void shoperCheckBillEnterOrCancel(String desc, Integer recordNo, String doSingle) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.addorUpdateContractRecordsForShoperEnterReceiveMoney(user, recordNo, desc, doSingle);
	}

	/**
	 * 对账单 list 经纪人数据排行
	 * 
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping("/shoper_medi_data_order_list")
	public void mediDataOrder(String startTime, String endTime, Integer start, Integer limit, String projectId) {

		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findAllMediRelativeDataInfo(user, startTime, endTime, page, projectId);
		this.outPageString(page);
	}

	/**
	 * 对账单 list 项目数据排行
	 * 
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping("/shoper_pro_data_order_list")
	public void proDataOrderForMedi(String startTime, String endTime, Integer start, Integer limit, String projectId) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findAllProRelativeDataForMediInfo(user, startTime, endTime, page, projectId);
		this.outPageString(page);
	}

	/**
	 * 2017-3-6 转向增加案场页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddProject")
	public String toAddProject(Model model) {
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		model.addAttribute("provinces", allProvince);
		return "/system/addProjectManage";
	}

	/**
	 * 2017-3-6 maoxy 增加案场
	 * 
	 * @return
	 */
	@RequestMapping("/addProject")
	public String addProject(Project project, User u, String province, String market, String area, String isSave) {
		project.setCity(province + "-" + market + "-" + area);
		projectService.addOrUpdateFirstProject(project, u);
		// if (!"提交并返回项目列表".equals(isSave)) {// 提交后继续新增
		// return "redirect:/toAddProject";
		// } else {// 提交后返回列表
		// return "redirect:/system_to_projectList";
		// }
		return "redirect:/system_to_projectList";
	}

	/**
	 * 陈冬华 2017-03-06 通过ajax传过来的地址遍历项目相关数据并返回
	 * 
	 * @param projectId
	 */
	@RequestMapping("/find_projectId")
	public void selectProjectsByCity(String city) {
		List allProjects = projectService.findAllProjectsNameByCity(city);
		if (allProjects != null) {
			this.outListString(allProjects);
		}
	}

	/**
	 * 跳转到佣金定义页面 2017-03-06 cdh
	 * 
	 * @return
	 */
	@RequestMapping("/to_paltform")
	public String toPlatform(Model model) {
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		model.addAttribute("provinces", allProvince);
		return "project/platform_lis";
	}

	/**
	 * 添加佣金定义 2017-03-06
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping("/platform_definition")
	public ModelAndView addPlatform(SystemChargeDefinition s) {

		if (s != null) { // 如果表单为空

			ModelAndView mv = new ModelAndView();
			projectService.addPlatform(s);
			mv.addObject("data", s);
			mv.setViewName("project/platform_dengdai");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", "表单信息填写错误，请重新填写");
			mv.setViewName("/platform_definition");// 可能要重新填写view的名字
			return mv;
		}
	}

	/**
	 * 陈冬华 2017-03-06 将所有已经定义了佣金的项目发往前端
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/all_project_list")
	public ModelAndView selectAllProject() {
		List<Map<String, Object>> mapList = projectService.selectAllPlatform();
		Map<String, Object> projectMap = null;
		for (Map<String, Object> map : mapList) {
			projectMap = new HashMap<String, Object>();
			String projectId = (String) map.get("projectId");
			if (projectId != null && !projectId.equals("")) {
				String projectName = projectService.findProjectById(projectId).getProjectName();

				System.out.println(projectId + " : " + projectName);
				map.put("projectName", projectName);
			} else {
				map.put("projectName", null);
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", mapList);
		mv.addObject("data2", projectMap); // 此map为projectName
		mv.setViewName("/project/platform_lists");
		return mv;
	}

	/**
	 * 
	 * 进入所有状态的中介门店审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/all_reviewd_page")
	public String shopReviewListPage() {
		return "/project/review_shop";
	}

	/**
	 * 进入审核中的中介门店审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/apply_reviewd_page")
	public String shopApplyReviewdPage() {
		return "/project/apply_shop";
	}

	/**
	 * 进入已经通过审核的中介门店页面
	 * 
	 * @return
	 */
	@RequestMapping("/passed_reviewd_page")
	public String shopPassedReviewPage() {
		return "/project/passed_shop";
	}

	/**
	 * 进入已经拒绝的中介门店页面
	 * 
	 * @return
	 */
	@RequestMapping("/refuse_reviewd_page")
	public String shopRefuseReviewPage() {
		return "/project/refuse_shop";
	}

	/**
	 * 中介门店的审核列表 applyId是前端筛选条件
	 * 
	 * @param start
	 *            当前页数
	 * @param limit
	 *            每页显示条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/all_shops_reviewd")
	public void shopReviewdList(Integer start, Integer limit, Integer applyId) {
		Page page = new Page();
		page.setStart((start - 1) * limit);
		page.setLimit(limit);

		projectService.findAllReviewedShops(page, applyId);
		this.outPageString(page);
	}

	/**
	 * 中介门店的审核界面信息
	 * 
	 * @param shopId
	 * @param tags
	 * @return
	 */
	@RequestMapping("/select_shop_reviewd")
	public ModelAndView selectShopReviewd(Integer shopId) {

		User u = (User) this.request.getSession().getAttribute("userInfo");
		if (u != null && !u.equals("")) {
			Shops shop = projectService.findReviewdShopById(shopId);
			if (shop != null) {
				// 将区号转换成城市名
				String cityNo = shop.getCity();
				String cityName = projectService.findCityNameByCityNum(cityNo);
				shop.setCity(cityName + shop.getAddress());
				ModelAndView mv = new ModelAndView();
				mv.addObject("data", shop);
				mv.setViewName("/project/shop_reviewed");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView();
				mv.addObject("data", "未找到该门店信息");
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", "您没有登录，请登录后在进行操作");
			mv.setViewName("/login/store_login");
			return mv;
		}
	}

	/**
	 * 
	 * 进行门店的审核 tag代表是否通过审核
	 * 
	 * @param shopId
	 * @param tag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add_shop_reviewd")
	public String doShopReviewd(Shops shop, Integer tag) {
		User u = (User) this.request.getSession().getAttribute("userInfo");
		if (shop.getAuditOpinion().trim() == null)
			shop.setAuditOpinion("");
		if (u != null && !u.equals("")) {
			String ret = projectService.addReviewShopById(u, shop, tag);
			if (ret.equals("success")) { // 表示通过申请
				return "success";
			} else if (ret.equals("failure")) { // 表示拒绝申请
				return "jujue";
			} else { // 表示shop为空
				return "not data";
			}
		} else {
			return "login/engin_login";
		}
	}

	/**
	 * 
	 * 进行门店的删除的操作
	 * 
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/delete_shop")
	public String deleteShop(Integer shopId) {
		projectService.dropShopByShopId(shopId);
		return "redirect:/apply_reviewd_page";
	}

	/**
	 * 业务 门店 页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_shoper_business_page")
	public String toShoperBusinessPage() {
		return "project/shopBusiness";
	}

	/**
	 * 业务 门店 list
	 * 
	 * @param cusOrProName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param start
	 * @param limit
	 */
	@RequestMapping("/list_shoper_business_info")
	public void shoperBusinessList(String cusOrProName, String startTime, String endTime, String status, Integer start,
			Integer limit) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);

		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		projectService.findCusForShopBusiness(user, cusOrProName, startTime, endTime, status, page);
		this.outPageString(page);
	}

	/*---------------------新功能-中介成就----------------------------*/

	/**
	 * 本月中介签约
	 */
	@RequestMapping("/signed_in_this_month")
	public void signedInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List<ContractRecords> list = projectService.findThisMonthSignedInfo(user);
		this.outListString(list);
	}

	/**
	 * 本月中介认购
	 */
	@RequestMapping("/contract_in_this_month")
	public void contractInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List<ContractRecords> list = projectService.findThisMonthApplyAndAgreeContract(user);
		this.outListString(list);
	}

	/**
	 * 本月新增客户
	 */
	@RequestMapping("/new_customer_in_this_month")
	public void newCustomerInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		List<ProjectCustomers> list = projectService.findNewCustomerInThisMonth(user);
		this.outListString(list);
	}

	/**
	 * 本月中介备案
	 */
	@RequestMapping("/records_in_this_month")
	public void recordInThisMonth() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		this.outListString(projectService.findGuideRecordInThieMonth(user));
	}
	/*--------------中介成就-end-----------------*/

	// 认筹客户 页面跳转控制器
	public String toConfessCustomerPage() {
		return "";
	}

	// 认筹客户 list
	public Object getConfessCustomerList() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List confCusList = projectService.findConfessOfCurrentUser(user);
		return confCusList;
	}

	// ____________________________________项目搜门店_________________________________________________________________________________
	/**
	 * 返回区域门店___________
	 * 
	 * @param cityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getWantToKonwShops")
	public List<ShopsNewDTO> getWantToKonwShops(String cityId) {
		String address = this.request.getRemoteAddr();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		/*
		 * if (cityName != null && !"".equals(cityName)){ String cityId =
		 * projectService.findCityIdByCityName(cityName); }else { return
		 * projectService.findNewShopsList(user, null,address); }
		 */
		return projectService.findNewShopsList(user, cityId, address);
	}

	/**
	 * 门店list__________________________________________
	 * 
	 * @param addressOne
	 * @param addressTwo
	 * @param start
	 * @param limit
	 * @param quId
	 * @param sign
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getOneShopsList")
	public Object getOneShopsList(String addressOne, String addressTwo, Integer start, Integer limit, String quId,
			String sign) {
		String address = this.request.getRemoteAddr();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);

		projectService.findOneAreaShopsList(user, addressOne, addressTwo, page, quId, sign, address);

		return page;
	}

	/**
	 * 地图上展示____________________________门店__________
	 * 
	 * @param addressOne
	 * @param addressTwo
	 * @param start
	 * @param limit
	 * @param quId
	 * @param sign
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getOneShopsListInMap")
	public List<Shops> getOneShopsListInMap() {
//		String address = this.request.getRemoteAddr();
		// 获取session中的用户信息
//		User u = (User) this.request.getSession().getAttribute("userInfo");
//		// 获取持久化用户对象
//		User user = userService.findById(u.getUserId());
		List<Shops> list = projectService.findOneAreaShopsListInMap();
		return list;
	}

	/**
	 * 单个门店信息
	 * 
	 * @param shopId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getOneShops")
	public List<NewOneShopDTO> getOneShopsList(Integer shopId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		return projectService.findOneShop(user, shopId);
	}

	/**
	 * 单个门店信息ditu______________
	 * 
	 * @param shopId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getOneShopsByMap")
	public List<NewOneShopDTO> getOneShopsListByMap(String jingdu, String weidu) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		return projectService.findOneShopByMap(user, jingdu, weidu);
	}

	/**
	 * 跳转地图控制器__________________________
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToManagerMap")
	public String toGoToManagerMap() {

		return "directorWeb/managerMap";
	}

	/**
	 * 获取门店数量________________
	 * 
	 * @param addressOne
	 * @param addressTwo
	 * @param quId
	 * @param sign
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_goToGetAllShopsList")
	public Map<String, Object> toGoToGetAllShopsList(String addressOne, String addressTwo, String quId, String sign) {
		String address = this.request.getRemoteAddr();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		return projectService.findGoToGetAllShopsList(user, addressOne, addressTwo, quId, sign);
	}

	/**
	 * 根据ip获取经纬度____________
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/to_getJingweiduByIp", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getJingweiduByIp(HttpServletRequest request) throws Exception {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");

		String[] str = userService.findjingweiduByIp(user, request);
		return str;
	}
	/**
	 * 房源生成----------------添加分区-----------------------------------------------------------------------------------------------------
	 * @param pz
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_createZone")
	public Map<String,Object> createZone(ProjectZone pz){
		Map<String,Object> result = new HashMap<>();
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		pz.setProjectId(projectId);
		pz.setZoneStatus(1);
		boolean addProjectZone = projectService.addProjectZone(pz);
		if(addProjectZone){
			result.put("status", 200);
			result.put("message", "添加成功");
		}else{
			result.put("status", 202);
			result.put("message", "添加失败");
		}
		return result;
	}
	/**
	 * 房源生成----------------修改分区
	 * @param pz
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_updateProjectZone")
	public Map<String,Object> updateProjectZone(ProjectZone pz){
		Map<String,Object> result = new HashMap<>();
		boolean flag = projectService.updateProjectZone(pz);
		if(flag){
			result.put("status", 200);
			result.put("message", "修改成功");
		}else{
			result.put("status", 202);
			result.put("message", "分区不存在");
		}
		return result;
	}
	/**
	 * 查询分区
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_findAllProjectZone")
	public List<ProjectZone> findAllZoneByProjectId(){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		return projectService.findAllZoneByProjectId(projectId);
	}
	/**
	 * 创建楼栋
	 * @param pd
	 * @return
	 */
	@RequestMapping("/manage_createBuilding")
	@ResponseBody
	public Map<String,Object> createProjectBuilding(ProjectBuilding pd){
		Map<String,Object> result = new HashMap<>();
		pd.setBuildingStatus(1);
		boolean flag = projectService.addProjectBuilding(pd);
		if(flag){
			result.put("status", 200);
			result.put("message", "添加成功");
		}else{
			result.put("status", 202);
			result.put("message", "添加失败");
		}
		return result;
	}
	/**
	 * 修改楼栋
	 * @param pb
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_updateBuilding")
	public Map<String,Object> updateProjectBuilding(ProjectBuilding pb){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Map<String,Object> result = new HashMap<>();
		boolean flag = projectService.updateProjectBuilding(pb,user.getParentId());
		if(flag){
			result.put("status", 200);
			result.put("message", "修改成功");
		}else{
			result.put("status", 202);
			result.put("message", "楼栋不存在");
		}
		return result;
	}
	/**
	 * 查询分区下所有楼栋
	 * @param zoneId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_findAllBuildingByZoneId")
	public List<ProjectBuilding> findProjectBuildingByZoneId(Integer zoneId){
		return projectService.findAllBuildingByZoneId(zoneId);
	}
	/**
	 * 删除分区
	 * @param zoneId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_delProjectZone")
	public Map<String,Object> deleteProjectZone(Integer zoneId){
		Map<String,Object> result = new HashMap<>();
		boolean flag = projectService.updateFordeleteProjectZone(zoneId);
		if(flag){
			result.put("status", 200);
			result.put("message", "删除成功");
		}else{
			result.put("status", 202);
			result.put("message", "不允许删除");
		}
		return result;
	}
	/**
	 * 删除楼栋
	 * @param buildingId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_delProjectBuilding")
	public Map<String,Object> deleteProjectBuilding(Integer buildingId){
		Map<String,Object> result = new HashMap<>();
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		boolean flag = projectService.updateForDeleteProjectBuilding(buildingId,projectId);
		if(flag){
			result.put("status", 200);
			result.put("message", "删除成功");
		}else{
			result.put("status", 202);
			result.put("message", "不允许删除");
		}
		return result;
	}
	/**
	 * 保存房源-----房源生成
	 */
	@ResponseBody
	@RequestMapping("/manage_saveProjectHouse")
	public Map<String,Object> savePorjectHouse(Integer floorNum,String houseInfo,String unitInfo,Integer buildingId,Integer zoneId,Integer housePropertyType,String propertyYear,String propertyExpireTime,Integer sellType){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		Map<String,Object> result = new HashMap<>();
		Map<String,String> unitInfoMap = JSON.parseObject(unitInfo, Map.class);
		List<HouseJson> houseInfoList = JSON.parseArray(houseInfo, HouseJson.class);
		try {
			projectService.addBatchHouse(floorNum,houseInfoList,unitInfoMap,buildingId,zoneId,housePropertyType,projectId,propertyYear,propertyExpireTime,sellType);
			result.put("status", 200);
			result.put("message", "成功");
		} catch (Exception e) {
			result.put("status", 202);
			result.put("message", e.getMessage());
		}
		return result;
	}
	/**
	 * 根据楼栋号查询房源
	 * @param buildingId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_findBuildingHouses")
	public HouseResult findHousesByZoneAndBuilding(Integer buildingId){
		HouseResult hr = projectService.findBuildingAllHouses(buildingId);
		return hr;
	}
	/**
	 * 查询所有户型
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_findHouseType")
	public List<HouseType> findHouseTypeByProject(){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		List<HouseType> types = projectService.findHouseTypeByProjectId(projectId);
		return types;
	}
	/**
	 * 房源合并
	 * @param firstHouseNum
	 * @param houseNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_mergeHouse")
	public Map<String,Object> mergeHouse(Integer firstHouseNum,Integer houseNum){
		Map<String,Object> result = new HashMap<>();
		try {
			projectService.updateHouseInfoForMerge(firstHouseNum,houseNum);
			result.put("status", 200);
			result.put("message", "成功");
		} catch (Exception e) {
			result.put("status", 202);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/manage_splitHouse")
	public Map<String,Object> splitHouse(Integer houseNum){
		Map<String,Object> result = new HashMap<>();
		try {
			projectService.updateHouseInfoForSplit(houseNum);
			result.put("status", 200);
			result.put("message", "成功");
		} catch (Exception e) {
			result.put("status", 202);
			result.put("message", e.getMessage());
		}
		return result;
	}
	/**
	 * 控制器---房源管理
	 * @return
	 */
	@RequestMapping("/manage_toHousesManage")
	public String toProjectHousesManage(){
		return "directorWeb/managerHouseManagerment";
	}
	
	/**
	 * 控制器------切换至房源列表
	 * @return
	 */
	@RequestMapping("/manage_toListType")
	public String toListHouses(){
		return "directorWeb/managerFloor";
	}
	
	/**
	 * 导出房源
	 * @param buildingId
	 */
	//@ResponseBody
	@RequestMapping("/manage_getHousesExcelByBuildingId")
	public void outExcelByBuildingId(Integer districtId, Integer buildingId,Integer unitId,Integer houseNum){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Map<String, Object> map = new HashMap<>();
		List<House> houses = projectService.createHousesExcel(user,districtId, buildingId, unitId, houseNum);
		//map.put("msg", url);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("house");
		HSSFSheet sheet2 = wb.createSheet("houseDetails");
		HSSFSheet sheet3 = wb.createSheet("projectBuilding");
		HSSFSheet sheet4 = wb.createSheet("projectBuildingUnit");
		HSSFSheet sheet5 = wb.createSheet("project");
		HSSFRow row = sheet1.createRow(0);
		HSSFRow row2 = sheet2.createRow(0);
		HSSFRow row3 = sheet3.createRow(0);
		HSSFRow row4 = sheet4.createRow(0);
		HSSFRow row5 = sheet5.createRow(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中格式
		int i = 0;
		int ii = 0;
		int iii = 0;
		int iiii = 0;
		int iiiii = 0;
		//house
		row.createCell(i++).setCellValue("房源id");
		row.createCell(i++).setCellValue("区位号");
		row.createCell(i++).setCellValue("楼栋号");
		row.createCell(i++).setCellValue("单元号");
		row.createCell(i++).setCellValue("楼层号");
		row.createCell(i++).setCellValue("房号");
		row.createCell(i++).setCellValue("朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row.createCell(i++).setCellValue("物业类型(1-别墅,2-小高层,3-高层,4-超高层,5-叠排,6-多层,7-排屋,8-商业街,9-集中商业,10-酒店公寓,11-底商,12-soho,13-写字楼)");
		row.createCell(i++).setCellValue("产权年限(年)");
		row.createCell(i++).setCellValue("房屋用途()");
		row.createCell(i++).setCellValue("建筑面积（预测)");
		row.createCell(i++).setCellValue("建筑面积（实测)");
		row.createCell(i++).setCellValue("总价(元)");
		row.createCell(i++).setCellValue("套内面积");
		row.createCell(i++).setCellValue("公摊面积");
		row.createCell(i++).setCellValue("占地面积");
		row.createCell(i++).setCellValue("非计容面积");
		row.createCell(i++).setCellValue("实得面积");
		row.createCell(i++).setCellValue("实得面积比");
		row.createCell(i++).setCellValue("预售证信息");
		//楼栋朝向  1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
		row.createCell(i++).setCellValue("户型位置(1-边套,2-中间套)");
		row.createCell(i++).setCellValue("总面宽(米)");
		row.createCell(i++).setCellValue("采光面(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row.createCell(i++).setCellValue("最大进深(米)");
		row.createCell(i++).setCellValue("层高(米)");
		row.createCell(i++).setCellValue("日照时间(小时)");
		row.createCell(i++).setCellValue("装修标准(1-精装 2-毛坯)");
		row.createCell(i++).setCellValue("租售类型(1-出租2-出售3-租售)");
		//housedetail
		row2.createCell(ii++).setCellValue("房源id");
		row2.createCell(ii++).setCellValue("主卧1尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("主卧1朝向 (1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("主卧2尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("主卧2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("次卧1尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("次卧1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("次卧2尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("次卧2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("书房尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("书房朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("餐厅尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("餐厅朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("客厅尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("客厅朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("卫生间1尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("卫生间1明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("卫生间2尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("卫生间2明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("卫生间3尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("卫生间3明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("卫生间4尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("卫生间4明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("卫生间5尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("卫生间5明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("厨房尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("工人房尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("工人房明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("洗衣房尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("阳台1尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("阳台1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("阳台2尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("阳台2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("阳台3尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("阳台3朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("设备平台尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("设备平台朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("储藏间尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("露台1尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("露台1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("露台2尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("露台2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("阁楼面积(㎡)");
		row2.createCell(ii++).setCellValue("阁楼层高(米)");
		row2.createCell(ii++).setCellValue("入户花园面积(㎡)");
		row2.createCell(ii++).setCellValue("花园1面积(㎡)");
		row2.createCell(ii++).setCellValue("花园1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("花园2面积(㎡)");
		row2.createCell(ii++).setCellValue("花园2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("半地下室面积(㎡)");
		row2.createCell(ii++).setCellValue("半地下室朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("地下室1面积(㎡)");
		row2.createCell(ii++).setCellValue("地下室1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("地下室1明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("地下室2面积(㎡)");
		row2.createCell(ii++).setCellValue("地下室2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row2.createCell(ii++).setCellValue("地下室2明暗(0-暗,1-明)");
		row2.createCell(ii++).setCellValue("采光井/通风井 尺寸(长*宽)");
		row2.createCell(ii++).setCellValue("观景角度(文字说明)");
		//projectBuilding
		row3.createCell(iii++).setCellValue("楼栋id");
		row3.createCell(iii++).setCellValue("楼栋号");
		row3.createCell(iii++).setCellValue("楼栋朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)");
		row3.createCell(iii++).setCellValue("结构形式(框架/框剪/钢构)");
		row3.createCell(iii++).setCellValue("是否直连地库(0-否,1-是)");
		row3.createCell(iii++).setCellValue("架空层数(0表示没有架空层)");
		row3.createCell(iii++).setCellValue("是否含有非机动车库(0-无,1-有)");
		row3.createCell(iii++).setCellValue("有无裙房(0-无,1-有)");
		row3.createCell(iii++).setCellValue("楼栋具体位置");
		//projectBuildingUnit
		row4.createCell(iiii++).setCellValue("单元id");
		row4.createCell(iiii++).setCellValue("电梯数");
		row4.createCell(iiii++).setCellValue("电梯材质");
		row4.createCell(iiii++).setCellValue("单元每层户数");
		row4.createCell(iiii++).setCellValue("单元楼层数");
		row4.createCell(iiii++).setCellValue("单元状态(0-删除,1-存在)");
		row4.createCell(iiii++).setCellValue("单元得房率直接存%");
		//project
		row5.createCell(iiiii++).setCellValue("案场id");
		row5.createCell(iiiii++).setCellValue("重要信息(房性差异/不利因素)");
		row5.createCell(iiiii++).setCellValue("企业性质 (1-民企,2-国企,3-上市公司)");
		row5.createCell(iiiii++).setCellValue("开发资质(1-1级,2-1级暂定,3-2级,4-2级暂定)");
		row5.createCell(iiiii++).setCellValue("设计单位");
		row5.createCell(iiiii++).setCellValue("施工单位");
		row5.createCell(iiiii++).setCellValue("是否入住平台 (0-申请入住平台,1-同意入住平台,2-拒绝入住平台)");
		row5.createCell(iiiii++).setCellValue("车位数");
		row5.createCell(iiiii++).setCellValue("小区会所(自建配套)");
		row5.createCell(iiiii++).setCellValue("健身房(面积/品牌)(自建配套)");
		row5.createCell(iiiii++).setCellValue("社区中心(面积/品牌)(自建配套)");
		row5.createCell(iiiii++).setCellValue("游泳池(自建配套)");
		row5.createCell(iiiii++).setCellValue("学校(自建配套)");
		row5.createCell(iiiii++).setCellValue("幼儿园(自建配套)");
		row5.createCell(iiiii++).setCellValue("商业(自建配套)");
		row5.createCell(iiiii++).setCellValue("其他(自建配套)");
		row5.createCell(iiiii++).setCellValue("商业(周边配套)");
		row5.createCell(iiiii++).setCellValue("教育(周边配套)");
		row5.createCell(iiiii++).setCellValue("金融(周边配套)");
		row5.createCell(iiiii++).setCellValue("交通(周边配套)");
		row5.createCell(iiiii++).setCellValue("医疗(周边配套)");
		row5.createCell(iiiii++).setCellValue("其他(周边配套)");
		
		
		int y = 1;
		int y2 = 1;
		int y3 = 1;
		int y4 = 1;
		int y5 = 1;
		row5 = sheet5.createRow(y5);
		int z5 = 0;
		String projectId = "";
		Set<Integer> is = new HashSet<>();
		Set<Integer> is2 = new HashSet<>();
		for (House house : houses) {
			int z = 0;
			int z2 = 0;
			
			row = sheet1.createRow(y++);
			row2 = sheet2.createRow(y2++);
			row3 = sheet3.createRow(y3++);
			row4 = sheet4.createRow(y4++);
			row.createCell(z++).setCellValue(house.getHouseNum());
			row.createCell(z++).setCellValue(house.getDistrict());
			row.createCell(z++).setCellValue(house.getBuildingNo());
			row.createCell(z++).setCellValue(house.getUnit());
			row.createCell(z++).setCellValue(house.getFloor());
			row.createCell(z++).setCellValue(house.getHouseNo());
			
			row2.createCell(z2++).setCellValue(house.getHouseNum());
			
			if (house.getBuildingId() != null && !"".equals(house.getBuildingId())){
				is.add(house.getBuildingId());
			}
			if (house.getUnitId() != null && !"".equals(house.getUnitId())){
				is2.add(house.getUnitId());
			}
			
		}
		for (Integer integer : is) {
			int z3 = 0;
			row3.createCell(z3++).setCellValue(integer);
			ProjectBuilding pb = (ProjectBuilding) baseDao.loadObject("from ProjectBuilding where buildingId = '"+ integer+"' ");
			row3.createCell(z3++).setCellValue(pb.getBuildingName());
		}
		for (Integer integer : is2) {
			int z4 = 0;
			row4.createCell(z4++).setCellValue(integer);
		}
		row5.createCell(z5).setCellValue(user.getParentId());
		
		try {
			String rename = sdf.format(new Date())+".xls";
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);
			byte[] bytes = out.toByteArray();
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename=house.xls");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 房源导入
	 * @param file
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/import_houses_info")
	public Object newImportExcel(MultipartFile file) throws Exception{
		Map map = new HashMap<>();
		HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = null;
		int sheets = wb.getNumberOfSheets();
		for(int m=0;m<sheets;m++){
			sheet = wb.getSheetAt(m);
			HSSFRow title = sheet.getRow(0);
			List<String> titles = new ArrayList<String>();
			String sheetName = sheet.getSheetName();
			ExcelType excelType = ExcelType.valueOf(sheetName);
			Map<String,String> zhName = excelType.zhName();
			//获取文件头
			for (int i = 0; i < title.getLastCellNum(); i++) {
				HSSFCell cell = title.getCell(i);
				if (cell != null) {
					titles.add(cell.getStringCellValue());
				} else {
					break;
				}
			}
			//遍历行
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				String str = (new StringBuilder()).append(Character.toUpperCase(sheetName.charAt(0))).append(sheetName.substring(1)).toString();
				Object obj = Class.forName("com.sc.tradmaster.bean." + str).newInstance();
				HSSFRow row = sheet.getRow(i);
				// 遍历列
				int k = 0;
				for (String string : titles) {
					HSSFCell cell = row.getCell(k);
					if (cell != null) {
						try {
							Class clazz = PropertyUtils.getPropertyType(obj, zhName.get(string));
							if(clazz!=null){
								if (Double.class.equals(clazz)) {
									String scell = cell.toString().substring(0, cell.toString().indexOf("."));
									PropertyUtils.setProperty(obj, zhName.get(string), Double.parseDouble(scell));
								} else if (Integer.class.equals(clazz)) {
									String scell = cell.toString().substring(0, cell.toString().indexOf("."));
									PropertyUtils.setProperty(obj, zhName.get(string), Integer.parseInt(scell));
								} else if (Float.class.equals(clazz)) {
									String scell = cell.toString().substring(0, cell.toString().indexOf("."));
									PropertyUtils.setProperty(obj, zhName.get(string), Float.parseFloat(scell));
								} else if (String.class.equals(clazz) && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									String scell = cell.toString().substring(0, cell.toString().indexOf("."));
									PropertyUtils.setProperty(obj, zhName.get(string), scell);
								} else {
									PropertyUtils.setProperty(obj, zhName.get(string), cell.getStringCellValue());
								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					k++;
				}
				// 调用后台保存数据
				houseService.addOrUpdateObj(obj);
			}
		}
		map.put("code", 200);
		return map;
	}
	
	/**
	 * 
	 * @param houseKind 房源类型
	 * @param houseStatus 房源状态
	 * @param houseTypeId	户型ID
	 * @param isSee 经纪人是否可见 0-不可见，1-可见
	 * @param page 当前页
	 * @param num 一页显示条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_searchHouse")
	public Map<String,Object> searchHouses(Integer houseKind,Integer houseStatus,String houseTypeId,Integer isSee,int page, int num){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		return projectService.searchProjectHousesAndLimit(houseKind,houseStatus,houseTypeId,isSee,page,num,projectId);
		
	}
	/**
	 * 根据查询房源
	 * @param houseNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_findHouseById")
	public House findHouseByHouseId(Integer houseNum){
		House house = projectService.findHouseById(houseNum);
		return house;
	}
	
	/**
	 * 修改房源信息
	 * @param house
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_editHouseInfo")
	public Map<String,Object> saveProjectHouse(House house){
		Map<String,Object> result = new HashMap<>();
		try {
			projectService.update_editHouse(house);
			result.put("status", 200);
			result.put("message", "保存成功");
		} catch (Exception e) {
			result.put("status", 202);
			result.put("message", "保存失败");
		}
		return result;
	}
	
	/**
	 * 批量上架1，批量下架2，批量删除3，批量对经纪人可见4, 批量对经纪人不可见5
	 * @param houseNums
	 * @param flag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manage_batchEditHouseInfo")
	public Map<String,Object> batchEditHouse(Integer[] houseNums,int flag){
		Map<String,Object> result = new HashMap<>();
		try {
			projectService.update_editBatchHouse(houseNums,flag);
			result.put("status", 200);
			result.put("message", "保存成功");
		} catch (Exception e) {
			result.put("status", 202);
			result.put("message", e.getMessage());
		}
		return result;
	}
	/**
	 * web一房一档
	 * @param houseNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/manage_toHouseHouseDetails")
	public String findHouseInfo(Integer houseNum,Model model){
		ProjectHouseInfo pInfo = projectService.findHouseDetails(houseNum);
		model.addAttribute("houseInfo", pInfo);
		return "directorWeb/singleHouseMessage";
	}
	/**
	 * app一房一档
	 * @param houseNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findHouseDetailsInfo")
	public ProjectHouseInfo findHouseInfo2App(Integer houseNum){
		ProjectHouseInfo pInfo = projectService.findHouseDetails(houseNum);
		return pInfo;
	}
	
	@RequestMapping("/manage_findBuildingUnitByBuildingId")
	public List<ProjectBuildingUnit> getUnitByBuildingId(Integer buildingId){
		List<ProjectBuildingUnit> pb = projectService.findUnitByBuildingId(buildingId);
		return pb;
	}
	
	@ResponseBody
	@RequestMapping("/manage_findZoneAndBuilding")
	public List<ProjectZoneDto> getProjectZoneForApp(){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		return projectService.findAllZoneAndBuilding2DTO(projectId);
	}
	
}
