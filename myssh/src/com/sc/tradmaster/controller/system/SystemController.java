package com.sc.tradmaster.controller.system;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.AnalysisCategory;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.ManaerChartData;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ReportResult;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.advertisement.AdvertisementService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.project.impl.dto.NewOneShopDTO;
import com.sc.tradmaster.service.project.impl.dto.RegisterProjectDTO;
import com.sc.tradmaster.service.shop.ShopService;
import com.sc.tradmaster.service.system.SystemService;
import com.sc.tradmaster.service.system.impl.dto.AdDTO;
import com.sc.tradmaster.service.system.impl.dto.Partner;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndShopDTO;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndUsers;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.DataResult;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.HttpClientService;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

import net.sf.json.JSONObject;

/**
 * 平台管理控制器
 * 
 * @author cdh
 *
 */
@Controller("systemController")
public class SystemController extends BaseController {

	@Resource(name = "advertisementService")
	private AdvertisementService advertisementService;

	@Resource(name = "systemService")
	private SystemService systemService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "houseService")
	private HouseService houseService;

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "shopService")
	private ShopService shopServie;
	
	@Resource(name = "httpClientService")
	private HttpClientService httpClientService;

	/**
	 * 跳转合伙人新建关联页面 2017-3-9 maoxy
	 * 
	 * @return
	 */
	@RequestMapping("toAdPartner_Relation")
	public String toAdManageRelation(Model model) {
		// 查询所有合伙人
		Set<User> users = systemService.findUsersByRoleId();
		List<User> userList = new ArrayList<>();
		for (User u : users) {
			userList.add(u);
		}
		model.addAttribute("users", userList);
		return "system/addpartner_relation";
	}

	@RequestMapping("system_main")
	public String toSystemMain() {
		return "system/main";
	}

	/**
	 * 2017-3-10 maoxy 异步加载合伙人关联页面数据
	 * 
	 * @param status
	 *            1-案场请求；2-门店请求
	 */
	@RequestMapping("query_all")
	public void queryAll(Integer status) {
		if (status == 1) { // 关联案场
			List<Project> othersProjects = systemService.findPartnerOtherProjects();
			this.outListString(othersProjects);
		}
		if (status == 2) {
			List<Shops> othersShops = systemService.findPartnerOtherShops();
			this.outListString(othersShops);
		}
	}

	/**
	 * 2017-3-10 maoxy 添加合伙人与门店或者案场的信息
	 * 
	 * @param userId
	 * @param Pvalidity
	 * @param Svalidity
	 * @param projectIds
	 * @param shopIds
	 * @param isSave
	 * @return
	 */
	@RequestMapping("addPartner_relation")
	public String addPartnerRelation(String userId, Integer Pvalidity, Integer Svalidity, String[] projectIds,
			String[] shopIds, String isSave) {
		if (projectIds != null) {
			if (projectIds.length > 0) {
				systemService.addPartnerAndProjects(userId, projectIds, Pvalidity);
			}
		}
		if (shopIds != null) {
			if (shopIds.length > 0) {
				systemService.addPartnerAndShops(userId, shopIds, Svalidity);
			}
		}
		if ("提交并新增下一条".equals(isSave)) {
			return "redirect: toAdPartner_Relation";
		} else {
			return "redirect: toPartnerList";
		}

	}

	/**
	 * 2017-3-11 maoxy 跳转至合伙人管理页面
	 * 
	 * @return
	 */
	@RequestMapping("toPartnerList")
	public String toPartnerList() {
		return "system/partnerList";
	}

	/**
	 * 分页查询合伙人
	 * 
	 * @param page
	 *            当前页
	 * @param count
	 *            显示条数
	 */
	@RequestMapping("partner_relation_list")
	public void findPartnerAll(int page, int count) {
		List<Partner> partners = systemService.findPartners(page, count);
		this.outListString(partners);
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"roleId"});
		// String jsonData = JSONArray.fromObject(partners,config).toString();
		// String jsonString = "{total:" + partners.size() + ",root:"
		// + jsonData + "}";
		// return jsonString;
		// this.outListString(partners);
	}

	/**
	 * 查询合伙人页数
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("partner_list_num")
	public int findPartnerNum() {
		int total = systemService.findPartnerNum();
		int count = 0;
		if (total != 0) {
			// 5表示一页显示条数
			if (total % 5 == 0) {
				count = total / 5;
			} else {
				count = total / 5 + 1;
			}
		}
		return count;
	}

	/**
	 * 2017-3-13 maoxy 根据Id查看合伙人详情页
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping("query_partner")
	public String findPartnerById(String userId, Model model) {
		Partner partner = systemService.findPartnerById(userId);
		model.addAttribute("partner", partner);
		return "system/partner_relation";
	}

	/**
	 * 更新合伙人的电话和关联消息
	 * 
	 * @param user
	 * @param projectIds
	 * @param shopIds
	 * @param isSave
	 * @return
	 */
	@RequestMapping("changePartner_relation")
	public String changePartnerRel(User user, String[] projectIds, String[] shopIds, String isSave) {
		systemService.updatePartnerRel(user, projectIds, shopIds);
		return "system/partnerList";
	}

	// /**
	// * 获取平台管理中项目总数，置业顾问总数...
	// */
	// @RequestMapping("all_system")
	// public String toSystemPage() {
	// return "/system/all_count_count";
	// }

	/**
	 * 将所有的项目总数，置业顾问总数传给前台
	 */
	@ResponseBody
	@RequestMapping(value = "all_system_count", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object allSystemCount() {
		List<Map<String, String>> list = systemService.getSystemCount();
		return list;

	}

	/**
	 * 所有的项目中到访登记的数据
	 * 
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping(value = "all_visit_person_count", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object allVisitPersonCount(String startTime, String endTime) {
		List<Map<String, String>> list = systemService.getRecordsNum(startTime, endTime);
		return list;
	}

	/**
	 * 中介管理的备案，确认，认购的数据
	 * 
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping(value = "all_meid_data_count", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object allMeidData(String startTime, String endTime) {

		List<Map<String, String>> list = systemService.getMeidNum(startTime, endTime);
		return list;
	}

	/**
	 * 2017-3-15 maoxy 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping("system_index")
	public String goBackIndex() {
		return "system/all_count_count";
	}

	/**
	 * 2017-3-15 maoxy 跳转至广告列表
	 * 
	 * @return
	 */
	// @RequestMapping("system_to_adList")
	// public String goAdList(Model model){
	// //查询所有的广告
	// List<AdDTO> adList = systemService.findAllAdvertisement();
	// model.addAttribute("adList", adList);
	// return "system/adList2";
	// }

	@RequestMapping("system_to_adList")
	public String goAdListAndFindAdTotal(Model model) {
		int total = systemService.findAdTotal();
		int count = 0;
		if (total != 0) {
			// 5表示一页显示条数
			if (total % 5 == 0) {
				count = total / 5;
			} else {
				count = total / 5 + 1;
			}
		}
		model.addAttribute("count", count);
		return "system/adList";
	}

	/**
	 * 分页查询广告
	 * 
	 * @param page
	 *            当前页面
	 * @param count
	 *            显示条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findAdByPage")
	public List<AdDTO> findAdByPage(int page, int count) {
		List<AdDTO> list = systemService.findAdvertisementByPage(page, count);
		return list;
	}

	// @RequestMapping("system_to_adList1")
	// public String goAdList1(Model model){
	// //查询所有的广告
	// List<AdDTO> adList = systemService.findAllAdvertisement();
	// model.addAttribute("adList", adList);
	// return "system/adList3";
	// }
	/**
	 * 2017-3-22 maoxy 跳转至修改广告列表
	 * 
	 * @param adId
	 * @return
	 */
	@RequestMapping("system_to_updataAd")
	public String goUpdataAd(String adId, Model model) {
		AdDTO ad = systemService.findAdByAdId(adId);
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		// String provinceAndCity = ad.getProvinceAndCity();
		// String substring = provinceAndCity.substring(0,
		// provinceAndCity.lastIndexOf("-"));
		// List<Project> projects = systemService.findProByCity(substring);
		// model.addAttribute("projects", projects);
		Map<String, String> projectAddress = systemService.findProjectAddressById(ad.getProjectId());
		// 项目的省Id
		String pProvince = projectAddress.get("provinceId");
		// 项目的市名称
		String pCityName = projectAddress.get("cityName");
		model.addAttribute("ad", ad);
		model.addAttribute("provinces", allProvince);
		model.addAttribute("pProvince", pProvince);
		model.addAttribute("pCity", pCityName);
		return "system/alterAd";
	}

	/**
	 * 根据市的
	 * 
	 * @param cityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findProjectByCity")
	public List<Project> findProjectByCity(String cityId) {
		List<Project> projects = systemService.findProjectByCity(cityId);
		return projects;
	}

	/**
	 * 2017-3-15 maoxy 跳转至项目列表
	 * 
	 * @return
	 */
	@RequestMapping("system_to_projectList")
	public String goProjectList(Model model) {
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		model.addAttribute("provinces", allProvince);
		return "system/projectList";
	}

	@ResponseBody
	@RequestMapping(value = "get_project_list")
	public void getProjectList(Integer start, Integer limit, String province, String market, String area) {
		StringBuffer addr = new StringBuffer();
		if (!StringUtils.isEmpty(province)) {
			addr.append(province);
		}
		if (!StringUtils.isEmpty(market)) {
			addr.append("-" + market);
		}
		if (!StringUtils.isEmpty(area)) {
			addr.append("-" + area);
		}
		String address = addr.toString();
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		systemService.findAllProjectAndUsersToPage(page, address);
		this.outPageString(page);

	}

	/**
	 * 2017-3-18 maoxy 显示log列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("showloadLog")
	public String showLogsList(Model model) {
		List<String> names = new ArrayList<>();
		String p = File.separator;
		String realPath = this.request.getSession().getServletContext().getRealPath(p);
		String path = realPath + "static" + p + "logs";
		try {
			File file = new File(path);
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				String name = f.getName();
				names.add(name);
			}
			model.addAttribute("logsData", names);

		} catch (Exception e) {
			try {
				this.response.getWriter().write(new String(e.getMessage().getBytes(), "ISO8859-1"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return "system/logsList";

	}

	@RequestMapping("downloadLog")
	public ResponseEntity<byte[]> downloadLog(String name) {
		try {
			String p = File.separator;
			String realPath = this.request.getSession().getServletContext().getRealPath(p);
			File file = new File(realPath + p + "static" + p + "logs" + p + name);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", name);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			try {
				e.printStackTrace();
				this.response.getWriter().write(new String("下载出错，请稍后重试".getBytes(), "ISO8859-1"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * 2017-3-21 maoxy 跳转至增加合伙人页面
	 * 
	 * @return
	 */
	@RequestMapping("system_toAddPartner")
	public String toAddPartner() {
		return "system/addPartner";
	}

	/**
	 * 2017-3-21 maoxy 新增合伙人
	 * 
	 * @param user
	 * @param isSave
	 * @return
	 */

	@RequestMapping("system_addPartner")
	public String addPartner(User user, String isSave) {
		boolean b = systemService.addPartner(user);
		if (b) {
			if ("提交后继续新增".equals(isSave)) {
				return "redirect:system_toAdPartner";
			} else {
				return "redirect:toPartnerList";
			}
		} else {
			try {
				this.response.getWriter().write(new String("新增出错，请稍后重试".getBytes(), "ISO8859-1"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * 账号管理页面的跳转
	 * 
	 * @return
	 */
	@RequestMapping("all_user_info_page")
	public String allUserInfoPage() {
		return "system/all_user_system";
	}

	/**
	 * 
	 * @param status
	 * @param page
	 *            当前页
	 * @param count
	 *            一页显示条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("system_all_user")
	public List<Map<String, Object>> findAllUser(String status, int page, int count) {
		return userService.findAllUserByPage(status, page, count);
	}

	@ResponseBody
	@RequestMapping("system_all_user_num")
	public Map<String, Integer> findAllUserNum(String status) {
		Map<String, Integer> map = new HashMap<>();
		int total = userService.findAllUserNum(status);
		int count = 0;
		if (total != 0) {
			// 5表示一页显示条数
			if (total % 10 == 0) {
				count = total / 10;
			} else {
				count = total / 10 + 1;
			}
		}
		map.put("num", count);
		return map;
	}

	/**
	 * 
	 * 账号管理页面的用户信息编辑
	 * 
	 * @param userId
	 * @param model
	 * @return 跳转页面
	 */
	@RequestMapping("to_edit_user_page")
	public String toEditUserPage(String userId, Integer roleId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("phone", user.getPhone());
		model.addAttribute("userCaption", user.getUserCaption());
		model.addAttribute("roleId", roleId);
		model.addAttribute("idCard", user.getIdCard());
		return "system/edit_user_page";
	}

	/**
	 * 更改账号信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("update_user_info")
	public String updateUserSystemInfo(User user, String doSign, String role) {
		User u = (User) this.request.getSession().getAttribute("userInfo");
		userService.updateSystemUser(u, user, doSign, role); // reset表示重置密码
		return "redirect:all_user_info_page";
	}

	/**
	 * 2017-3-31 maoxy 转向标签管理页面
	 * 
	 * @return
	 */
	@RequestMapping("system_tagManage")
	public String toTagMmanager(Model model) {
		// List<TagLib> tagLibs = tagService.showTagLib(0);
		// model.addAttribute("tagLibs", tagLibs);
		return "system/tagmanager2";
	}

	// 跳转到对账单页面
	@RequestMapping("to_staement_page")
	public String toStatementPage(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/statement_count";
	}

	/**
	 * ajax异步查找对账单中的数据
	 * 
	 * @param city
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("system_statement_count")
	public List<ProjectsAndShopDTO> getStatementOfAccount(String city, String startTime, String endTime) {
		List<ProjectsAndShopDTO> list = systemService.findStatementOfAccount(city, startTime, endTime);
		return list;
	}

	/**
	 * 确认和取消到款
	 * 
	 * @param houseNum
	 * @param isConfirm
	 * @param isSystemPayConfirm
	 * @return
	 */
	@RequestMapping("edit_commission_statement")
	public ModelAndView editCommissionStatement(Integer houseNum, Integer isConfirm, Integer isSystemPayConfirm) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		boolean flag = systemService.updateCommissionForm(houseNum, isConfirm, user);
		if (flag) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", 200);
			mv.setViewName("redirect:to_staement_page");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", 202);
			mv.setViewName("redirect:to_staement_page");
			return mv;
		}

	}

	/**
	 * 进行门店报表页面的跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("system_shop_report")
	public String gotoShopReportPage(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/shop_report";
	}

	/**
	 * ajax异步获取门店的报表
	 * 
	 * @param city
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("system_shop_report_forms")
	public List<Map<String, Object>> getShopReportForms(String city, String startTime, String endTime, int page,
			int count) {
		List<Map<String, Object>> list = systemService.findShopReportForms(city, startTime, endTime, page, count);
		return list;
	}

	/**
	 * 获取门店总页数
	 * 
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("table_shop_list_num")
	public int getShopRepCount(String city, String startTime, String endTime) {
		int total = systemService.getShopRepCount(city, startTime, endTime);
		int count = 0;
		if (total != 0) {
			// 10表示一页显示条数
			if (total % 10 == 0) {
				count = total / 10;
			} else {
				count = total / 10 + 1;
			}
		}
		return count;
	}

	@RequestMapping("system_medi_report")
	public String gotoMediResportPage(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/medi_report";
	}

	/**
	 * ajax异步获取门店的报表
	 * 
	 * @param city
	 * @param startTime
	 * @param endTime
	 */
	@ResponseBody
	@RequestMapping("system_medi_report_forms")
	public List<Map<String, Object>> getMediReportForms(String city, String startTime, String endTime, int page,
			int count) {
		List<Map<String, Object>> list = systemService.findMediReportForms(city, startTime, endTime, page, count);
		return list;
	}

	/**
	 * 获取门店报表页数
	 * 
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("table_medi_list_num")
	public int getMediRepCount(String city, String startTime, String endTime) {
		int total = systemService.getMediRepCount(city, startTime, endTime);
		int count = 0;
		if (total != 0) {
			// 5表示一页显示条数
			if (total % 10 == 0) {
				count = total / 10;
			} else {
				count = total / 10 + 1;
			}
		}
		return count;
	}

	/**
	 * 
	 * 进行项目报表页面的跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("system_poroject_report")
	public String gotoProjectReportPage(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/project_report";
	}

	@RequestMapping("table_project")
	public String toTabProject(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "system/table_project";
	}

	/**
	 * ajax异步获取项目报表
	 * 
	 * @param city
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping("system_project_report_forms")
	public List<Map<String, Object>> getProjectReportForms(String city, String startTime, String endTime, int page,
			int count) {
		List<Map<String, Object>> list = systemService.findProjectReportForms(city, startTime, endTime, page, count);
		return list;
	}

	/**
	 * 获取页数
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("table_project_list_num")
	public int getProjectRepCount(String city, String startTime, String endTime) {
		int total = systemService.getProjectRepCount(city, startTime, endTime);
		int count = 0;
		if (total != 0) {
			// 5表示一页显示条数
			if (total % 10 == 0) {
				count = total / 10;
			} else {
				count = total / 10 + 1;
			}
		}
		return count;
	}

	/**
	 * 跳转到添加广告页面 2017-3-6 maoxy
	 * 
	 * @return
	 */
	@RequestMapping("/toAddAd")
	public String toAddAd(Model model) {
		// 查询所有项目
		List<Project> projects = projectService.findAllBySystem();
		model.addAttribute("projects", projects);
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		model.addAttribute("provinces", allProvince);
		return "/system/addAdManage2";
	}

	/**
	 * 添加广告 2017-3-6 maoxy
	 * 
	 * @param ad
	 * @param province
	 * @param market
	 * @param area
	 * @param type
	 * @return
	 */

	@RequestMapping("/add_advertisement")
	public String addAds(Advertisement ad, String province, String market, MultipartFile picFile, Model model,
			String open) {
		ad.setAdCity(province + "-" + market);
		if ("on".equals(open)) {
			ad.setState(1);
		} else {
			ad.setState(0);
		}
		try {
			advertisementService.addAds(ad, picFile);
		} catch (Exception e) {
			model.addAttribute("data", "保存出错");
			return "forward:/toAddAd";
		}
		return "redirect:/system_to_adList";
	}

	@RequestMapping("/ad_update_by_mxy")
	public String updateAd(Advertisement ad, String province, String market, MultipartFile picFile, String open)
			throws Exception {
		if ("on".equals(open)) {
			ad.setState(1);
		} else {
			ad.setState(0);
		}
		if (picFile != null) {
			if (!picFile.isEmpty()) {
				String rename = SysContent.getFileRename(picFile.getOriginalFilename());
				// 将图片存入云
				String upload = PicUploadToYun.upload(rename, picFile, SmsContext.AD_PIC);
				ad.setAdUrl(upload);
			}
		}
		ad.setAdCity(province + "-" + market);
		systemService.updateAd(ad);
		return "redirect:/system_to_adList";
	}

	/**
	 * 广告修改
	 * 
	 * @param ad
	 * @return
	 */
	// @RequestMapping("/ad_update_by_grl")
	// public String alterAd(Advertisement ad,String province, String
	// market,MultipartFile picFile,String open) throws Exception {
	// // 获取session中的用户信息
	// User u = (User) this.request.getSession().getAttribute("userInfo");
	// // 获取持久化用户对象
	// User user = userService.findById(u.getUserId());
	// if("on".equals(open)){
	// ad.setState(1);
	// }else{
	// ad.setState(0);
	// }
	// ad.setAdCity(province + "-" + market);
	// try {
	// advertisementService.updateAdvertisement(u,ad,picFile);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "redirect:/system_to_adList";
	// }

	/**
	 * 进入案场客户管理页面
	 * 
	 * @return
	 */
	@RequestMapping("to_system_project_customer_page")
	public String intoCustomerPage() {
		return "system/project_customer_info";
	}

	/**
	 * 类目页面________________
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToFatherPage")
	public String toGoToFatherPage() {
		return "system/analysisCategory_manager";
	}

	/**
	 * 增
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToAddFatherPage")
	public String toGoToAddFatherPage() {
		return "system/addAnalysisCategory_manager";
	}

	/**
	 * 改
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToUpdateFatherPage")
	public String toGoToUpdateFatherPage() {
		return "system/updateAnalysisCategory_manager";
	}

	/**
	 * 类目儿子页面
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToSonPage")
	public String toGoToSonPage() {
		return "system/manaerChartData_manager";
	}

	@RequestMapping("to_goAlertSonPage")
	public String toAlertSonPage(String chartDataId, Model model) {
		ManaerChartData mc = userService.findManaerChartById(chartDataId);
		model.addAttribute("mc", mc);
		return "system/alertManaerChartData_manager";
	}

	/**
	 * 增
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToAddSonPage")
	public String toGoToAddSonPage() {
		return "system/addManaerChartData_manager";
	}

	/**
	 * 改
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToUpdateSonPage")
	public String toGoToUpdateSonPage() {
		return "system/updateManaerChartData_manager";
	}

	/**
	 * 新增分析标签_______________________________________________________________________________________________
	 * 
	 * @param mcd
	 * @throws ParseException
	 */
	@RequestMapping("/to_addManaerChartData")
	public String addManaerChartData(ManaerChartData mcd, String[] relationLists, MultipartFile file) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (relationLists != null && relationLists.length > 0) {
			String string = Arrays.toString(relationLists);
			String substring = string.substring(1, string.length() - 1);
			mcd.setRelationList(substring);
		}
		userService.addManaerChartData(mcd, user, file);
		return "system/manaerChartData_manager";
	}

	/**
	 * 经理_分析标签集合___________________
	 * 
	 * @param mcd
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/to_getAnalysisCategoryList")
	public List<AnalysisCategory> getAnalysisCategoryList() throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<AnalysisCategory> acList = userService.findAnalysisCategoryList(user);
		return acList;
	}

	@ResponseBody
	@RequestMapping("/to_getManaerChartDataList")
	public List<ManaerChartData> getManaerChartDataList(String isUseful) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<ManaerChartData> mcdList = userService.findManaerChartDataList(user, isUseful);
		return mcdList;
	}

	/**
	 * 经理_分析标签__修改状态___
	 * 
	 * @param chartDataId
	 * @throws ParseException
	 */
	@RequestMapping("/to_deleteManaerChartData")
	public void deleteManaerChartData(String chartDataId) throws ParseException {

		userService.updateManaerChartData(chartDataId);
	}

	/**
	 * 获取所有项目id____________________________________________________
	 */
	@ResponseBody
	@RequestMapping("/to_getProjectIdList")
	public List<Project> getProjectIdList() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<Project> proList = userService.findProjectIdList(user);
		return proList;
	}

	/**
	 * 新增经理_分析_类目_____________________
	 * 
	 * @param categoryName
	 * @param chartDataIdArr
	 * @param projectId
	 * @param priority
	 * @param isMade
	 * @throws ParseException
	 */
	@RequestMapping("/to_addAnalysisCategory1")
	public String addAnalysisCategory(String categoryName, String chartDataIdArr, String projectId, String priority,
			String isMade) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		userService.addAnalysisCategory(categoryName, chartDataIdArr, projectId, priority, isMade, user);
		return "system/analysisCategory_manager";
	}

	/**
	 * 更新类目__________________________
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @param haveLabel
	 * @param projectId
	 * @param priority
	 * @param isMade
	 * @throws ParseException
	 */
	@RequestMapping("/to_updateAnalysisCategory")
	public void updateAnalysisCategory(Integer categoryId, String categoryName, String haveLabel, String projectId,
			String priority, String isMade) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		userService.updateAnalysisCategory(categoryId, categoryName, haveLabel, projectId, priority, isMade);
	}

	/**
	 * 更新儿子____________________________________________
	 * 
	 * @param chartDataId
	 * @param mcd
	 * @throws ParseException
	 */
	@RequestMapping("/to_updateManaerChartData")
	public String updateManaerChartData(ManaerChartData mcd, MultipartFile file, String[] relationLists)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (relationLists != null && relationLists.length > 0) {
			String string = Arrays.toString(relationLists);
			String substring = string.substring(1, string.length() - 1);
			mcd.setRelationList(substring);
		}
		userService.updateManaerChartData(mcd, file);
		return "redirect:/to_goToSonPage";
	}

	/********************************************************* 新增平台地图模块 ***********************************************************************/

	/**
	 * 地图显示页面
	 */
	@RequestMapping("/mapInfo")
	public String forMapInfo() {
		return "login/otherMap";
	}

	/**
	 * 有案场和门店的信息
	 */
	@ResponseBody
	@RequestMapping("/getProAndShopInfo")
	public Object showroAndShopOnMapInfo(String prvo, String city, String qu) {
		String pcq = null;
		if (prvo != null) {
			pcq = prvo;
		}
		if (prvo != null && city != null) {
			pcq += "-" + city;
		}
		if (prvo != null && city != null && qu != null) {
			pcq += "-" + qu;
		}
		Map mapList = projectService.findAllProAndShopInfo(pcq);
		return mapList;
	}

	/**
	 * 项目 条件获取项目 地图项目列表(及项目详情 标签筛选)
	 * 
	 * @param maxLongitudes
	 * @param minLongitudes
	 * @param maxLatitudes
	 * @param minLatitudes
	 * @param tags
	 * @param houseTags
	 * @param projectId
	 * @param start
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_project_by_property")
	public Object getProsOrShopsData(String maxLongitudes, String minLongitudes, String maxLatitudes,
			String minLatitudes, String[] tags, String[] houseTags, String projectId, Integer start, Integer limit) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectService.findAllProOrShopsByProperties(maxLongitudes, minLongitudes, maxLatitudes, minLatitudes, tags,
				houseTags, projectId, page);
		return page;
	}

	/**
	 * 项目 条件获取项目 地图项目列表(及项目详情 地区选择)
	 * 
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_project_by_city")
	public Object getProsOrShopsData(String city) {
		String areaId = shopServie.findAddressByIpOrCityName(city, this.request, houseService);
		// 通过市获取所有区
		List<Map<String, String>> lmList = houseService.findCityAreaByShi(areaId);
		return shopServie.findProjectListInArea(lmList);
	}

	/**
	 * 项目 获取当前项目下的可售房源列表(及房源详情)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_current_project_of_house_data")
	public Object getCurrentProOfHouse(String projectId, String[] houseTags, Integer status, Integer start,
			Integer limit) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		shopServie.findHouseList(projectId, houseTags, page, status);
		return page;
	}

	/**
	 * 门店 获取区域门店
	 * 
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_area_shops_data")
	public Object getAreaShops(String city) {
		String address = this.request.getRemoteAddr();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (city != null && !"".equals(city)) {
			String cityId = projectService.findCityIdByCityName(city);
			return projectService.findNewShopsList(user, cityId, address);
		} else {
			return projectService.findNewShopsList(user, null, address);
		}
	}

	/**
	 * 门店 获取门店集合
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
	@RequestMapping("/get_shops_list_data")
	public Object getShopsList(String addressOne, String addressTwo, Integer start, Integer limit, String quId,
			String sign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String address = this.request.getRemoteAddr();
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectService.findOneAreaShopsList(user, addressOne, addressTwo, page, quId, sign, address);
		return page;
	}

	/**
	 * 单个门店信息
	 * 
	 * @param shopId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_shop_data")
	public List<NewOneShopDTO> getOneShopsList(Integer shopId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		return projectService.findOneShop(user, shopId);
	}

	@RequestMapping("/system_project_register")
	public String toProjectRegister() {
		return "system/projectRegister";
	}

	/**
	 * 获取申请注册的案场
	 */
	@ResponseBody
	@RequestMapping("/get_registerProject")
	public Map<String, Object> getRegisterProject(int page, int count) {
		return systemService.findRegisterProjectByPage(page, count);
	}

	/**
	 * 审核案场
	 */
	@ResponseBody
	@RequestMapping("/check_ProjectRegister")
	public Map<String,Object> checkProjectRegister(String projectId,int agree){
		Map<String,Object> result = new HashMap<>();
		try {
			systemService.updateProjectAndUser(projectId,agree);
			result.put("status", 200);
			if(agree==1){
				result.put("msg", "项目审核已经通过");
				return result;
			}
			if(agree==0){
				result.put("msg", "项目审核已经拒绝");
				return result;
			}
			result.put("msg", "参数错误,请稍后重试");
			return result;
		} catch (Exception e) {
			result.put("status", 202);
			result.put("msg", e.getMessage());
			return result;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/get_report", method=RequestMethod.POST)
	public Object getReportToSave(String projectId, String startTime, String endTime){
		
		List<String> dateCount = DateUtil.getTwoDateEveryDay(startTime, endTime);
		
		String url = "";
		if(dateCount.size() <= 27){
			url = "/report/week/" + projectId + "/" + startTime + "/" + endTime;
			
		}else{
			
			url = "/report/quarter/" + projectId + "/" + startTime + "/" + endTime;
		}
		JSON obj = (JSON) getHttpClientData(url);
		ReportResult rr = JSON.parseObject(obj.toJSONString(), ReportResult.class);
		boolean flag = systemService.addReportExcelToCloud(rr);
		if(flag){
			return "success";
		}else{
			return "error";
		}
	}
	
	/**
	 * 案场数据报的下载
	 * @param url
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/download_report",method = RequestMethod.POST)
	public Object toDownLoadReportExcel(String projectId, String reportName, String startTime, String endTime){
		return systemService.findDownloadReportExcel(projectId, reportName, startTime, endTime);
	}
	
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

}
