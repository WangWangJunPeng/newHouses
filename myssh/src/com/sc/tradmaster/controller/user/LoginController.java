package com.sc.tradmaster.controller.user;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBuilding;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.bean.WeChatServer;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.contractRecords.ContractRecordsService;
import com.sc.tradmaster.service.guideRecords.GuideRecordsService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.housetype.HouseTypeService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.projectcustomer.ProjectCustomerService;
import com.sc.tradmaster.service.shop.ShopService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.service.visitRecords.VisitRecordsService;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ExcelType;
import com.sc.tradmaster.utils.HttpClientService;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SystemRecordUtils;
import com.sc.tradmaster.utils.TripleDES;
import com.sc.tradmaster.utils.config.alipay.AlipayConfig;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 2017-01-02
 * 
 * @author Administrator
 *
 */
@Controller
public class LoginController extends BaseController {
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Resource(name = "userService") // 指定名称注入，testDao对应注入对象指定的名称
	private UserService userService;

	@Resource(name="houseTypeService")
	private HouseTypeService houseTypeService;
	
	@Resource(name = "houseService")
	private HouseService houseService;

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "shopService")
	private ShopService shopServie;
	
	@Resource(name = "guideRecordsService")
	private GuideRecordsService guideRecordsService;

	@Resource(name = "agentVisitRecordService")
	private AgentVisitRecordService agentVisitRecordService;
	
	@Resource(name = "contractRecordsService")
	private ContractRecordsService contractRecordsService;
	
	@Resource(name = "projectCustomerService")
	private ProjectCustomerService projectCustomerService;
	
	@Resource(name = "visitRecordsService")
	private VisitRecordsService visitRecordsService;
	
	@Resource(name="httpClientService")
	private HttpClientService httpClientService;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	/**
	 * 微信服务接口	注册页面
	 * @param weChatNum
	 * @return
	 */
	@RequestMapping("/wechat_server")
	public ModelAndView toWeChatServerPage(String weChatNum){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("");
		mv.addObject("weChatNum", weChatNum);
		return mv;
	}
	
	public Object weChatSerRegister(WeChatServer wcs,String code){
		Map map = new HashMap<>();
		
		return map;
	}
	
	// 支付宝接口测试
	@RequestMapping("/alipaytest")
	public void test111(String biaohao, String name, String amount, String body) throws IOException {
		// 销售码
		String product_code = "QUICK_WAP_PAY";
		/**********************/
		// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
		// 调用RSA签名方式
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
				AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGNTYPE);
		AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

		// 封装请求支付信息
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(biaohao);
		model.setSubject(name);
		model.setTotalAmount(amount);
		model.setBody(body);
		model.setTimeoutExpress(body);
		model.setProductCode(product_code);
		alipay_request.setBizModel(model);
		// 设置异步通知地址
		alipay_request.setNotifyUrl(AlipayConfig.notify_url);
		// 设置同步地址
		alipay_request.setReturnUrl(AlipayConfig.return_url);

		// form表单生产
		String form = "";
		try {
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
			response.getWriter().write(form);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/exception")
	public void exception() {
		userService.getArray();
	}

	/**
	 * 首页页面控制器
	 */
	@RequestMapping("/index")
	public String forBasePage() {
		return "../../index";
	}

	@RequestMapping("/tologinPage")
	public String toLoginPage() {
		return "login/login";
	}

	/**
	 * 案场助理登录页面控制器
	 * 
	 * @return
	 */
	@RequestMapping("/engin_login")
	public String forMediToLogin() {
		// ModelAndView mv = new ModelAndView();
		return "login/engin_login";
	}

	/**
	 * 店长登录页面控制器
	 * 
	 * @return
	 */
	@RequestMapping("/store_login")
	public String forStoreToLogin() {
		return "login/store_login";
	}

	/**
	 * 获取当前版本的版本号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/return_editionNo")
	public Object returnEditionNo(String type) {
		String date = SmsContext.VERTION_DATE;
		String editionNo = SmsContext.VERTION_NO;
		Map map = new HashMap<>();
		map.put("date", date);
		map.put("editionNo", editionNo);
		if (type != null && !type.equals("") && type.equals("jsonp")) {
			return this.getJsonpString(map);
		} else {
			return map;
		}
	}

	/**
	 * jsonp登录app
	 * 
	 * @param item
	 * @param user
	 * @param sign
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/jsonp_for_login_app")
	public Object jsonpLogin(Integer item, User user, String sign) {
		Map map = new HashMap<>();
		if (item.equals(1)) {
			map = (Map) forAppToLogin(user, sign);
		} else if (item.equals(0)) {

		} else {
			map.put("returenCode", "登录信息有误...");
		}
		return this.getJsonpString(map);
	}

	/**
	 * session超时用userTooken登录
	 * 
	 * @return
	 */
	@RequestMapping("/tooken_to_login")
	public String sessionOutToUserTookenPageLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies){
			if(c.getName().equals("userSign")){
				if(c.getValue().equals("director")){
					return "app/director/getCookies";
				}else{
					return "redirect:/app/tologin";
				}
			}
		}
		return null;
	}

	/**
	 * app客户端登录页面控制器
	 * 
	 * @return
	 */
	@RequestMapping("/app/tologin")
	public String forApplogin() {
		return "app/login/app_login";
	}
	/**
	 * 跨域登入
	 * @return
	 */
	@RequestMapping("/app/tologin2")
	public String forApplogin1(String phone,String password,String sign,Model model) {
		model.addAttribute("phone", phone);
		model.addAttribute("password", password);
		model.addAttribute("sign", sign);
		return "app/login/app_login2";
	}

	/**
	 * app端案场经理登录页面控制器
	 * 
	 * @return
	 */
	@RequestMapping("/director_login")
	public String forDirectorAppLogin() {
		return "app/director/login";
	}

	/**
	 * app登录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applogin")
	public Object forAppToLogin(User user, String sign) {
		User u = userService.addUserTokenAndlogin(user);
		Map<String, Object> map = new HashMap<>();
		if (u != null) {
			map.put("userToken", u.getUserToken());
			this.request.getSession().setAttribute("userInfo", u);
			this.request.getSession().setAttribute("loginSign", "app");
			this.request.getSession().setAttribute("userSign", sign);
			String cookieStr = u.getPhone() + "," + user.getPassword();
			Cookie cookie = new Cookie("token", u.getUserToken());
			Cookie userSignCookie = new Cookie("userSign", sign);
			// 设置Maximum Age
			cookie.setMaxAge(30 * 24 * 60 * 60);
			cookie.setPath("/");
			this.response.addCookie(cookie);
			userSignCookie.setMaxAge(12*60*60);
			userSignCookie.setPath("/");
			this.response.addCookie(userSignCookie);
			Set<Role> rSet = u.getRoleId();
			String roleName = null;
			for (Role role : rSet) {
				roleName = role.getRoleName();
			}
			// 经理
			if (roleName.equals("director") && sign.equals("director")) {
				map.put("returenCode", "200");
				map.put("skipURL", "to_director_index");// 暂时跳转到职业顾问页面
			}
			// 店长/中介
			else if ((roleName.equals("shopowner") || roleName.equals("medi")) && "undirector".equals(sign)) {
				map.put("returenCode", "200");
				map.put("skipURL", "to_goToChoice");
				// 存城市session
				CitySessionDTO csdto = userService.findCityIntoSession(u);
				this.request.getSession().setAttribute("csdto", csdto);
			}
			// 置业顾问
			else if (roleName.equals("agent") && "undirector".equals(sign)){
				map.put("returenCode", "200");
				map.put("skipURL", "to_my_task_page");
			} else {
				map.put("returenCode", "401");
				map.put("msg", "您没有此登录权限!");
			}
			map.put("cookie", cookie);
		} else {
			map.put("returenCode", "402");
			map.put("msg", "你的用户名或密码错误!");
		}
		return map;
	}

	/**
	 * app记住密码时调用接口
	 * 
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/form_userToken_to_my_task_page")
	public Object chectUserToken(String userToken,String roleId) {
		Map map = new HashMap<>();
		if (userToken != null && !userToken.equals("")) {
			User u = userService.findByUserToken(userToken);
			if (u != null) {
				this.request.getSession().setAttribute("userInfo", u);
				this.request.getSession().setAttribute("loginSign", "app");
				Set<Role> role = u.getRoleId();
				for (Role r : role) {
					if (r.getRoleId() == 3 && !roleId.equals("7")) {
						map.put("returenCode", "200");
						map.put("skipURL", "to_my_task_page");
					} else if ((r.getRoleId() == 1 || r.getRoleId() == 2) && !roleId.equals(7)) {
						map.put("returenCode", "200");
						map.put("skipURL", "to_goToChoice");
						// 存城市session
						CitySessionDTO csdto = userService.findCityIntoSession(u);
						this.request.getSession().setAttribute("csdto", csdto);
					} else if (r.getRoleId() == 7 && roleId.equals("7")) {
						map.put("returenCode", "200");
						map.put("skipURL", "to_director_index");
					}
				}
			} else {
				map.put("returenCode", 403);
				map.put("msg", "用户信息失效，请重新登录");
			}
		}
		return map;
	}

	/**
	 * 陈冬华2017-03-06 当用户点击首页门店注册进行跳转
	 * 
	 * @return
	 */
	@RequestMapping("/shop_regs")
	public String shopReg(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "login/shop_regs";
	}

	/**
	 * cdh2017-03-27 注册门店失败跳转页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/shop_error")
	public String shopErrorView(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		model.addAttribute("data", "注册出错，请重试");
		return "/shop_regs";
	}

	/**
	 * 登录验证控制器
	 * 
	 * @param user
	 *            用户
	 * @param sign
	 *            电脑登录/手机登录
	 * @param r_sign
	 *            中介/店长/职业顾问/案场助理
	 *            (Web:店长shopowner、案场助理engineer；App：职业顾问agent、中介经纪人medi)
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(User user, String sign, String r_sign) {
		User u = userService.addUserTokenAndlogin(user);
		if (u != null) {
			this.request.getSession().setAttribute("userInfo", u);
			this.request.getSession().setAttribute("loginSign", "web");
			Set<Role> rSet = u.getRoleId();
			String roleName = null;
			ModelAndView mv = new ModelAndView();
			if (sign != null && sign.equals("app")) {
				mv.setViewName("redirect:/app/tologin");
			}
			for (Role role : rSet) {
				roleName = role.getRoleName();
			}
			// 案场经理
			if (roleName.equals("director") && sign.equals("web") && r_sign.equals("engineer")) {
				mv.setViewName("directorWeb/managerHome");
				mv.addObject("data", u);
			}
			// 案场助理
			else if (roleName.equals("engineer") && sign.equals("web") && r_sign.equals("engineer")) {
				mv.setViewName("project/projectIndex");
				mv.addObject("data", u);
			}
			// 店长web端
			else if (roleName.equals("shopowner") && sign.equals("web") && r_sign.equals("shopowner")) {
				mv.setViewName("publicpage/shopsPublicPage");
				mv.addObject("data", u);
			}
			// 店长app端
			/*
			 * else if(roleName.equals("shopowner") && sign.equals("app")){
			 * mv.setViewName("app/ad/choice"); mv.addObject("data", u); }
			 */
			// 中介经纪人
			/*
			 * else if(roleName.equals("medi") && sign.equals("app")){
			 * mv.setViewName("app/ad/choice"); mv.addObject("data", u); }
			 */
			// 置业顾问
			/*
			 * else if(roleName.equals("agent") && sign.equals("app")){
			 * mv.setViewName("redirect:/to_my_task_page"); mv.addObject("data",
			 * u); }
			 */else {
				mv.addObject("data", "您没有此登录权限！！！");
				if (r_sign != null && r_sign.equals("engineer")) {
					mv.setViewName("login/login");
				} else if (r_sign != null && r_sign.equals("shopowner") && sign.equals("web")) {
					mv.setViewName("login/login");
				}
			}
			return mv;
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("data", "该用户不存在");
			if (r_sign != null && r_sign.equals("engineer")) {
				mv.setViewName("login/login");
			} else if (r_sign != null && r_sign.equals("shopowner")) {
				mv.setViewName("login/login");
			} else if (sign != null && sign.equals("app")) {
				mv.setViewName("redirect:/app/tologin");
			}
			return mv;
		}
	}

	/**
	 * Machine 连接访问方法
	 */
	@ResponseBody
	@RequestMapping("/SystemTime")
	public Object machineConnection() {
		Map map = new HashMap<>();
		map.put("ReturnCode", 200);
		Date date = new Date();
		//Date date = DateUtil.parse("2017-08-07 15:28:00");
		map.put("systemTime",DateUtil.format(date));
		return map;
		//this.outMachineObjectString(map, null);
	}

	/**
	 * Machine 登录方法方法
	 * 
	 * @param loginName
	 * @param Password
	 * @throws IOException
	 */
	@RequestMapping("/base.login")
	public void machineLogin(@RequestParam(value = "phone") String loginName,
			@RequestParam(value = "password") String Password) throws IOException {
		Map map = new HashMap<>();
		User user = new User();
		user.setPhone(TripleDES.SimpleDecript(loginName));
		//user.setPhone(loginName);
		user.setPassword(TripleDES.SimpleDecript(Password));
		//user.setPassword(Password);
		User u = userService.addUserTokenAndlogin(user);
		if (u != null) {
			this.request.getSession().setAttribute("token", u.getUserToken());
			//this.request.getSession().setAttribute("userInfo", u);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
			jsonConfig.setExcludes(new String[] { "roleId", "password" });
			JSONObject jsonObject = JSONObject.fromObject(u, jsonConfig);
			map.put("ReturnCode", 200);
			map.put("User", jsonObject);
			map.put("token", u.getUserToken());
		} else {
			map.put("ReturnCode", 400);
			map.put("User", "你的用户名或密码错误!");
		}
		this.outMachineObjectString(map, null);
	}
	
	/**
	 * 立屏机自动登录
	 * @param userToken
	 * @throws IOException
	 */
	@RequestMapping("/base.login_by_token")
	public void machineLoginByUsertoken(String userToken) throws IOException {
		Map map = new HashMap<>();
		if (userToken != null && !userToken.equals("")) {
			User u = userService.findByUserToken(userToken);
			if (u != null) {
				this.request.getSession().setAttribute("userInfo", u);
				this.request.getSession().setAttribute("token", u.getUserToken());
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
				jsonConfig.setExcludes(new String[] { "roleId", "password" });
				JSONObject jsonObject = JSONObject.fromObject(u, jsonConfig);
				map.put("ReturnCode", 200);
				map.put("User", jsonObject);
				map.put("token", u.getUserToken());
			} else {
				map.put("ReturnCode", 400);
				map.put("User", "您的用户名或密码错误!");
			}
		}else{
			map.put("ReturnCode", 400);
			map.put("User", "登录信息失效，请重新登录...");
		}
		this.outMachineObjectString(map, null);
	}
	
	@RequestMapping("/new_base.login")
	public void machineWebLogin(String loginName,String Password) throws IOException {
		Map map = new HashMap<>();
		User user = new User();
		//user.setPhone(TripleDES.SimpleDecript(loginName));
		user.setPhone(loginName);
		//user.setPassword(TripleDES.SimpleDecript(Password));
		user.setPassword(Password);
		User u = userService.addUserTokenAndlogin(user);
		if (u != null) {
			this.request.getSession().setAttribute("token", u.getUserToken());
			this.request.getSession().setAttribute("userInfo", u);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
			jsonConfig.setExcludes(new String[] { "roleId", "password" });
			JSONObject jsonObject = JSONObject.fromObject(u, jsonConfig);
			map.put("ReturnCode", 200);
			map.put("User", jsonObject);
			map.put("token", u.getUserToken());
		} else {
			map.put("ReturnCode", 400);
			map.put("User", "您的用户名或密码错误!");
		}
		this.outMachineObjectString(map, null);
	}
	
	

	/**
	 * 2017-3-6 maoxy 平台页面登入控制器
	 * 
	 * @return
	 */
	@RequestMapping("/system_login")
	public String forSystemToLogin() {
		return "login/system_login";
	}

	/**
	 * 2017-3-6 maoxy 处理平台用户登入
	 * 
	 * @param u
	 * @param model
	 * @return
	 */

	@RequestMapping("system.login")
	public String systemLogin(User u, Model model) {
		if (u == null) {
			return "redirect:system_login";
		}
		String password = u.getPassword();
		// 密码加密后进行比对
		// u.setPassword(SysContent.md5(password));
		User user = userService.addUserTokenAndlogin(u);
		if (user != null) {
			Set<Role> roles = user.getRoleId();
			int roleId = -1;
			for (Role r : roles) {
				roleId = r.getRoleId();
			}
			if (roleId != 5) {
				model.addAttribute("data", "没有权限登入");
				return "/login/system_login";
			}
			// 平台用户
			this.session.setAttribute("userInfo", user);
			this.request.getSession().setAttribute("loginSign", "web");
			model.addAttribute("data", user);
			return "/system/all_count_count";

		}
		model.addAttribute("data", "密码或用户名错误");
		return "/login/system_login";
	}

	/**
	 * 校验前台注册的手机号码是否已经注册过
	 */
	@ResponseBody
	@RequestMapping(value="find_only_phone",method=RequestMethod.POST)
	public Map<String, Object> findExistPhone(String phoneNum) {

		String regEx = "1(3|5|7|8|4)\\d{9}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(phoneNum.trim());
		Map<String, Object> map = new HashMap<>();
		if (matcher.matches()) {

			if (phoneNum != null && !phoneNum.equals("")) {
				if (userService.findExistPhoneNum(phoneNum)) {
					map.put("status", 200);// 200代表数据库的手机号码没有重复
					map.put("message", "该号码可以使用");
					return map;
				} else {
					map.put("status", 202);// 202代表手机号码重复
					map.put("message", "该号码已经注册，请更换手机号码");
					return map;
				}
			}
		}
		map.put("status", 202);
		map.put("message", "请输入正确的号码");
		return map;
	}

	/**
	 * 陈冬华 2017-03-06 门店注册
	 * 
	 * @param shop
	 * @return
	 */
	@RequestMapping("/shop_register")
	public ModelAndView shopRegister(Shops shop, MultipartFile photoPic, MultipartFile licensePic, String province,
			String market, String area) {
		ModelAndView mv = new ModelAndView();
		try {
			userService.addShop(shop, photoPic, licensePic, province, market, area);
		} catch (Exception e) {
			log.error(e.getMessage());
			mv.setViewName("redirect:shop_error");
			return mv;
		}
		mv.addObject("data", "注册成功，请等候审核通过");
		mv.setViewName("login/login");
		return mv;
	}

	/**
	 * 通过ajax异步获取所有的省份
	 */
	@RequestMapping("/get_all_prov")
	public void getProvinceByAjax() {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		this.outListString(findAllProvince);
	}

	/**
	 * 市区联动动态菜单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menu_list_city_area")
	public void getCityAreaMenu(String shengOrShi) {
		List<Map<String, String>> lmList = houseService.findCityAreaByShi(shengOrShi);
		this.outListString(lmList);
	}

	/**
	 * 2013-3-14 maoxy 异步加载加载所有的省
	 */
	@RequestMapping("/menu_list_province_first")
	public void getAllProvince() {
		List<CountryProvinceInfo> allProvince = houseService.findAllProvince();
		this.outListString(allProvince);
	}

	/**
	 * App 二维码下载
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("downloadApp/{name}")
	public String downLoad(@PathVariable(value = "name") String name) {
		return "app/apk/download";
	}
	/**
	 * 案场经理app下载
	 * @return
	 */
	@RequestMapping("downloadApp_projectManager")
	public String downLoad2() {
		return "app/apk/downloadProjectManager";
	}

	/**
	 * 跳转到案场和门店在地图上展示信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/to_pro_shop_map_info")
	public String toProAndShopOnMapInfo() {
		return "project/proAndShopOnMapInfo";
	}
	
	// public ResponseEntity<byte[]> downloadLog(@PathVariable(value="name")
	// String name){
	// String p = File.separator;
	// name = name+".apk";
	// String realPath =
	// request.getSession().getServletContext().getRealPath("");
	// String path = realPath.substring(0, realPath.lastIndexOf(p));
	// File file=new File(path+p+"App"+p+name);
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentDispositionFormData("attachment", name);
	// headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	// try {
	// return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
	// headers, HttpStatus.CREATED);
	// } catch (IOException e) {
	// e.printStackTrace();
	// log.error(e.getMessage());
	// return null;
	// }
	// }

	// 数据导入工具页面
	@RequestMapping("/to_system_importData_page")
	public String toImportDataPage() {
		return "system/importData";
	}

	// 单独文件导入
	@RequestMapping("/one_file_importData_page")
	public Object importOneFile() {
		return "system/importDataOneFile";
	}
	
	// 数据导入工具
	@ResponseBody
	@RequestMapping("/import_txtData_to_table")
	public Object importDataUtil(@RequestParam(value = "txtFile") MultipartFile[] txtFile, String city, String proId) {
		Map<String, Object> map = new HashMap<>();
		try {
			map = projectService.addorUpdateByTxtFile(txtFile, city, proId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "导入失败");
		}
		return map;
	}
	
	/**
	 * 新版门店注册
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shop_reg_page", method = RequestMethod.GET)
	public String toRegisterPage(Model model) {
		List<CountryProvinceInfo> findAllProvince = houseService.findAllProvince();
		model.addAttribute("provinces", findAllProvince);
		return "project/newShopRegister";
	}
	
	
	
	@RequestMapping(value="perfect_shop_info",method=RequestMethod.POST)
	public String perfectShopInfo(Shops shop, MultipartFile photoPic, MultipartFile licensePic){
		Integer flag = shopServie.addShopInfo(shop, photoPic, licensePic);
		if(flag == 1){
			return "完善成功";
		}else{
			
			return "失败";
		}
	}
	
	

	/**
	 * 门店注册完成立即登录，跳转到指定的页面
	 * 
	 * @param shop
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shop_reg_and_login", method = RequestMethod.POST)
	public Object shopRegAndLogin(Shops shop) {
		Integer flag = shopServie.addNewShop(shop);
		if (flag == 1) {
			return "200";
		} else {
			return "202";
		}
	}

	/**
	 * ajax异步获取区内的项目信息-门店未审核查看
	 * @param area 下拉框选的城市列表名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_project_in_area_nor", method = RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	public List<Map<String, Object>> getProjectInAreaData(String city) {
		
		String areaId = shopServie.findAddressByIpOrCityName(city, this.request, houseService);
		// 通过市获取所有区
		List<Map<String, String>> lmList = houseService.findCityAreaByShi(areaId);

		List<Map<String, Object>> list = shopServie.findProjectListInArea(lmList);
		Collections.sort(list, new Comparator<Map<String, Object>>() {

            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer s1 = (Integer) o1.get("projectCount");
                Integer s2 = (Integer) o2.get("projectCount");

                return s2 - s1;
            }

        });
		return list;
	}

	/**
	 * ajax获取项目列表- 未审核门店查看
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
	@RequestMapping(value = "/get_project_info_nor", method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	public Object getProjectsInfo(String maxLongitudes, String minLongitudes, String maxLatitudes, String minLatitudes,
			String[] tags, String[] houseTags, String projectId, Integer start, Integer limit) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		shopServie.findProjectListByAddressAndTag(maxLongitudes, minLongitudes, maxLatitudes, minLatitudes, tags,
				houseTags, projectId, page);

		return page;
	}
	
	

	//获取所有的项目——动态菜单
	@ResponseBody
	@RequestMapping("/import_data_pro_menu")
	public Object proMenu(){
		List<Map<String,Object>> proList = projectService.findAllProForMenu();
		return proList;
	}
	
	//判断该案场是否有案场助理，有则获取登录帐号
	@ResponseBody
	@RequestMapping("/checkProHaveEngineer")
	public Object getProEngineer(String proId){
		Map map = userService.findEngineerByProId(proId);
		return map;
	}
	
	//给导入案场设置案场助理
	@ResponseBody
	@RequestMapping("/add_engineer")
	public Object addEngineer(User user){
		User u = new User();
		u.setParentId(user.getParentId());
		String rightSign = "engineer";
		Map map = userService.addOrUpdateUser(u, user, rightSign);
		return map;
	}
	
	//导入数据删除工具
	@ResponseBody
	@RequestMapping("/delete_import_data")
	public Object deleteImportData(String start,String end,String proId) throws Exception{
		Map map = new HashMap<>();
		try {
			map = userService.dropImportDataByTime(start,end,proId);
		} catch (Exception e) {
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	/**************************************************客户端数据模拟********************************************************/
	@ResponseBody
	@RequestMapping("/client_new_project.sign")
	public Object newSignInOrSignOutFun(Integer type,String projectId,String agentPhone,String time,Integer checkTeam) {
		Map map = new HashMap<>();
		User user = userService.findUserByProperty(agentPhone, projectId);
		if(user!=null){
			VisitRecords vr = agentVisitRecordService.findCurrentAgentIsReplete(projectId,user.getUserId());
			if(type.equals(1) && vr!=null){//1签退，并且vr==null没有接访时才能签退
				map.put("returncode", 201);
			}else{
				agentVisitRecordService.addSignInAndUpdateSignOutNew(user,type,projectId,user.getUserId(),time,checkTeam);
				map.put("returncode", 200);
			}
		}else{
			map.put("returncode", 201);
		}
		return map;
	}
	
	
	/**
	 * 添加User
	 * @param user
	 * @param role
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/client_add_agent")
	public Object clientAddAgent(User user,String role,String shopName) throws Exception{
		Map mapMsg = new HashMap<>();
		//String userStr = JSONObject.fromObject(user).toString();
		//SystemRecordUtils.writeInfoTofile(null,userStr);
		if(shopName!=null && !shopName.equals("")){
			shopName = new String(shopName.getBytes("ISO8859-1"),"utf-8");
		}
		if(user.getUserCaption()!=null && !user.getUserCaption().equals("")){
			String userName = new String(user.getUserCaption().getBytes("ISO8859-1"),"utf-8");
			user.setUserCaption(userName);
			Map map = userService.addOrUpdateUserFromClient(user,role,shopName);
			return map;
		}else{
			mapMsg.put("code", 201);
			return mapMsg;
		}
		
	}
	
	/**
	 * 添加户型
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/client_add_houseType")
	public Object clientAddHouseType(HouseType ht,MultipartFile pic,String proId) throws Exception{
		User user = new User();
		user.setParentId(proId);
		houseTypeService.addOrupdateHouseType(ht, user,pic);
		Map map = new HashMap();
		map.put("code", 200);
		map.put("msg", "添加成功");
		return map;
	}
	
	/**
	 * 添加房源
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/client_add_house")
	public Object clientAddHouse(House h,String houseTypeName,String preSellNum) throws Exception{
		if(houseTypeName!=null && !houseTypeName.equals("")){
			houseTypeName = new String(houseTypeName.getBytes("ISO8859-1"),"utf-8");
		}
		Map pMap = new HashMap<>();
		pMap.put("houseTypeName", houseTypeName);
		pMap.put("preSellNum", preSellNum);
		String mapStr = JSONObject.fromObject(pMap).toString();
		String userStr = JSONObject.fromObject(h).toString();
		SystemRecordUtils.writeInfoTofile(null,userStr+mapStr);
		Map map = houseService.addOrUpdateFromClient(h,houseTypeName,preSellNum);
		map.put("code", 200);
		return map;
	}
	
	/**
	 * 添加备案
	 * @param shopAgentName
	 * @param customerName
	 * @param phone	客户电话
	 * @param proId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/client_add_guideRecords")
	public Object clientAddGuideRecords(String shopAgentName,String shopAgentPhone,String customerName,String phone,String proId) throws Exception{
		if(shopAgentName!=null && !shopAgentName.equals("")){
			shopAgentName = new String(shopAgentName.getBytes("ISO8859-1"),"utf-8");
		}
		if(customerName!=null && !customerName.equals("")){
			customerName = new String(customerName.getBytes("ISO8859-1"),"utf-8");
		}
		Map pMap = new HashMap<>();
		pMap.put("shopAgentName", shopAgentName);
		pMap.put("shopAgentPhone", shopAgentPhone);
		pMap.put("customerName", customerName);
		pMap.put("phone", phone);
		pMap.put("proId", proId);
		String mapStr = JSONObject.fromObject(pMap).toString();
		SystemRecordUtils.writeInfoTofile(null,mapStr);
		Map map = new HashMap<>();
		String[] projectId = {proId};
		User user = userService.findUserByName(shopAgentName,shopAgentPhone,null);
		if(user!=null && !user.equals("")){
			guideRecordsService.addGuideRecordsForClient(user, customerName, phone, projectId);
			map.put("code", 200);
			map.put("msg", "添加成功");
		}else{
			map.put("code", 201);
			map.put("msg", shopAgentName+"不存在");
		}
		return map;
	}
	
	/**
	 * 添加到访和送别
	 * @param vrs
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/client_add_visit_and_farewell")
	public Object clientAddVisitOrFareWell(VisitRecords vrs,String agentName,String agentPhone,String appointUserName,String appointAgentPhone,String proId) throws Exception{
		Map map = new HashMap();
		boolean isVisit = true;
		if(vrs!=null){
			if(agentName!=null && !agentName.equals("")){
				String cusName = new String(vrs.getCustomerName().getBytes("ISO8859-1"),"utf-8");
				vrs.setCustomerName(cusName);
			}
			if(vrs.getArriveTime()!=null && !vrs.getArriveTime().equals("")){
				String vt = DateUtil.format(DateUtil.parse(vrs.getArriveTime(),DateUtil.PATTERN_GRACE_MD));
				vrs.setArriveTime(vt);
				vrs.setReceptTime(vt);
			}
			if(vrs.getLeaveTime()!=null && !vrs.getLeaveTime().equals("")){
				String lt = DateUtil.format(DateUtil.parse(vrs.getLeaveTime(),DateUtil.PATTERN_GRACE_MD));
				vrs.setLeaveTime(lt);
				isVisit = false;
			}
		}
		if(agentName!=null && !agentName.equals("")){
			agentName = new String(agentName.getBytes("ISO8859-1"),"utf-8");
		}
		if(appointUserName!=null && !appointUserName.equals("")){
			appointUserName = new String(appointUserName.getBytes("ISO8859-1"),"utf-8");
		}
		if(appointUserName!=null && !appointUserName.equals("")){
			User apointU = userService.findUserByName(appointUserName,appointAgentPhone,proId);
			if(apointU!=null){
				vrs.setAppointUserId(apointU.getUserId());
			}
		}
		vrs.setProjectId(proId);
		User user = userService.findUserByName(agentName,agentPhone,proId);
		if(isVisit){//到访
			vrs.setUserId(user.getUserId());
			Integer visitNo = agentVisitRecordService.addOrUpdateVisitReocrdFromExcel(vrs,0);//模拟到访
			agentVisitRecordService.addOrUpdateAgentInsertCustomerInfo(user, vrs.getCustomerName(), vrs.getPhone(),null, visitNo);// 模拟填写客户信息
			map.put("code", 200);
			map.put("msg", "添加成功");
			map.put("visitNo", visitNo);
		}else{//送别
			if(vrs!=null && !vrs.getVisitNo().equals("")){
				//通过id获取该送别的到访记录
				VisitRecords vrData = visitRecordsService.findVisitById(vrs.getVisitNo());
				vrs.setUserId(vrData.getUserId());
				Integer visitNo = agentVisitRecordService.addOrUpdateVisitReocrdFromExcel(vrs,0);//模拟到访
				map.put("code", 200);
				map.put("msg", "添加成功");
				map.put("visitNo", visitNo);
			}else{
				map.put("code", 201);
			}
		}
		return map;
	}
	
	/**
	 * 添加认购
	 * @param userName		订单认购发起顾问名
	 * @param orderProperty	订单性质(0:自购,1:代购)
	 * @param isAlreadyRead	是否阅读全部条款(0:未读,1:已读)
	 * @param payStyle		支付方式 0线上 1线下
	 * @param accountStyle	结算方式 5一次性1公积金2商贷按揭3公积金+商贷4其他
	 * @param benefitInfo	优惠条款信息
	 * @param buyPrice		购买价格
	 * @param rengourenIdCard	认购人身份正号
	 * @param realCustomerId	真实认购人身份证号
	 * @param dposit		定金
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/client_add_Orders_enterBuy")
	public Object clientAddOrderEnterBuy(String userName,String userPhone,Integer orderProperty, Integer isAlreadyRead, Integer payStyle,Integer accountStyle, String benefitInfo,
			String buildingNo,String unit,Integer floor,String houseNo , String buyPrice, String customerPhone,String rengourenIdCard, 
			String dposit,String proId) throws Exception{
		if(userName!=null && !userName.equals("")){
			userName = new String(userName.getBytes("ISO8859-1"),"utf-8");
		}
		Map pMap = new HashMap<>();
		pMap.put("userName", userName);
		pMap.put("userPhone", userPhone);
		pMap.put("orderProperty", orderProperty);
		pMap.put("isAlreadyRead", isAlreadyRead);
		pMap.put("payStyle", payStyle);
		pMap.put("accountStyle", accountStyle);
		pMap.put("benefitInfo", benefitInfo);
		pMap.put("unit", unit);
		pMap.put("floor", floor);
		pMap.put("houseNo", houseNo);
		pMap.put("buyPrice", buyPrice);
		pMap.put("customerPhone", customerPhone);
		pMap.put("rengourenIdCard", rengourenIdCard);
		pMap.put("dposit", dposit);
		pMap.put("proId", proId);
		String mapStr = JSONObject.fromObject(pMap).toString();
		SystemRecordUtils.writeInfoTofile(null,mapStr);
		Map map = new HashMap<>();
		User user = userService.findUserByName(userName,userPhone,proId);
		Set<Role> rId = user.getRoleId();
		House h = houseService.findHouseByProperty(proId,buildingNo,unit,floor,houseNo);
		Integer houseNum = h.getHouseNum();
		ProjectCustomers pc = projectCustomerService.findProjectCustomersByPhone(customerPhone);
		contractRecordsService.addNewContractRecord(user, orderProperty, isAlreadyRead, payStyle, accountStyle,
				benefitInfo, houseNum, buyPrice, pc.getProjectCustomerId(), rengourenIdCard, null, dposit);
		map.put("code", 200);
		map.put("msg", "添加成功");
		return map;
	}
	
	/**
	 * 添加打款
	 * @param enterBuyAgentName
	 * @param enterBuyCusPhone
	 * @param enterBuyApplyTime
	 * @param enterBuyHouseNum
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/client_add_orders_pay")
	public Object clientAddOrderOfPay(String enterBuyAgentName,String enterBuyAgentPhone,String enterBuyCusPhone,String enterBuyApplyTime,String proId,
			String buildingNo,String unit,Integer floor,String houseNo) throws Exception{
		if(enterBuyAgentName!=null && !enterBuyAgentName.equals("")){
			enterBuyAgentName = new String(enterBuyAgentName.getBytes("ISO8859-1"),"utf-8");
		}
		Map pMap = new HashMap<>();
		pMap.put("enterBuyAgentName", enterBuyAgentName);
		pMap.put("enterBuyAgentPhone", enterBuyAgentPhone);
		pMap.put("enterBuyCusPhone", enterBuyCusPhone);
		pMap.put("enterBuyApplyTime", enterBuyApplyTime);
		pMap.put("proId", proId);
		pMap.put("buildingNo", buildingNo);
		pMap.put("unit", unit);
		pMap.put("floor", floor);
		pMap.put("houseNo", houseNo);
		String mapStr = JSONObject.fromObject(pMap).toString();
		SystemRecordUtils.writeInfoTofile(null,mapStr);
		Map map = new HashMap<>();
		User user = userService.findUserByName(enterBuyAgentName,enterBuyAgentPhone,proId);
		House h = houseService.findHouseByProperty(proId,buildingNo,unit,floor,houseNo);
		if(h!=null){
			Integer enterBuyHouseNum = h.getHouseNum();
			contractRecordsService.addPayByClient(user,enterBuyCusPhone,enterBuyApplyTime,proId,enterBuyHouseNum);
			map.put("code", 200);
		}else{
			map.put("code", 201);
		}
		return map;
	}
	
	/**
	 * 添加签约
	 * @param checkedAgentName
	 * @param enterBuyAgentName
	 * @param enterBuyCusPhone
	 * @param proId
	 * @param buildingNo
	 * @param unit
	 * @param floor
	 * @param houseNo
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/client_add_sign")
	public Object clientAddOrderOfSign(String checkedAgentName,String checkedAgentPhone,String enterBuyAgentName,String enterBuyAgentPhone,String enterBuyCusPhone,String proId,
			String buildingNo,String unit,Integer floor,String houseNo) throws Exception{
		if(checkedAgentName!=null && !checkedAgentName.equals("")){
			checkedAgentName = new String(checkedAgentName.getBytes("ISO8859-1"),"utf-8");
		}
		if(enterBuyAgentName!=null && !enterBuyAgentName.equals("")){
			enterBuyAgentName = new String(enterBuyAgentName.getBytes("ISO8859-1"),"utf-8");
		}
		Map pMap = new HashMap<>();
		pMap.put("checkedAgentName", checkedAgentName);
		pMap.put("checkedAgentPhone", checkedAgentPhone);
		pMap.put("enterBuyAgentName", enterBuyAgentName);
		pMap.put("enterBuyAgentPhone", enterBuyAgentPhone);
		pMap.put("enterBuyCusPhone", enterBuyCusPhone);
		pMap.put("proId", proId);
		pMap.put("buildingNo", buildingNo);
		pMap.put("unit", unit);
		pMap.put("floor", floor);
		pMap.put("houseNo", houseNo);
		String mapStr = JSONObject.fromObject(pMap).toString();
		SystemRecordUtils.writeInfoTofile(null,mapStr);
		Map map = new HashMap<>();
		User agent = userService.findUserByName(checkedAgentName,checkedAgentPhone,proId);
		User user = userService.findUserByName(enterBuyAgentName,enterBuyAgentPhone,proId);
		House h = houseService.findHouseByProperty(proId,buildingNo,unit,floor,houseNo);
		if(h!=null){
			Integer enterBuyHouseNum = h.getHouseNum();
			projectService.addorUpdateContractRecordsForSign(agent,user,enterBuyCusPhone,proId,enterBuyHouseNum);
			map.put("code", 200);
		}else{
			map.put("code", 201);
		}
		return map;
	}
	
	/**
	 * 签到签退
	 * @param type
	 * @param agentPhone
	 * @param proId
	 * @param time
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/client_add_signInOrOut")
	public Object clientAddSignInOrSignOut(Integer type ,String agentPhone,String proId,String time){
		
		Map pMap = new HashMap<>();
		pMap.put("type", type);
		pMap.put("agentPhone", agentPhone);
		pMap.put("proId", proId);
		pMap.put("time", time);
		String mapStr = JSONObject.fromObject(pMap).toString();
		SystemRecordUtils.writeInfoTofile(null,mapStr);
		User user = userService.findUserByProperty(agentPhone, proId);
		Map map = new HashMap<>();
		agentVisitRecordService.addSignInAndUpdateSignOut(user, type, proId, user.getUserId(), time);
		map.put("returncode", 200);
		this.outMachineObjectString(map, null);
		return map;
	}
	
	/**
	 * 根据经纬度查找地图上存在的项目列表
	 * 
	 * @param maxLongitudes
	 * @param minLongitudes
	 * @param maxLatitudes
	 * @param minLatitudes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_project_list_for_map_and_shop", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public List<Project> getProjectListForMap() {
		return shopServie.findProjectListForMap();
	}
	/**  maoxy
	 * 转向注册页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/toRegisterProject")
	public String toRegisterProject(Model model) throws IOException{
//		String ip = CusAccessObjectUtil.getIpAddress(request);
//		System.out.println(ip);
//		if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)){
//			ip = "125.120.210.76";
//		}
//		// ip = "220.181.16.0";
//		String[] str = (String[]) GetIPXY.getIPXY(ip);
//		model.addAttribute("ipxy", str);
		return "login/newProjectRegister";		//TODO 项目注册页面
	}
	
	/**maoxy
	 * 申请添加项目
	 * @param project
	 * 
	 * (developer 公司名称)
	 * (projectName 项目名称)
	 * (propertyAddress 项目地址/物业地址)
	 * 
	 * @param user (联系人userCaption,联系方式phone)
	 * @param province 省
	 * @param market 市
	 * @param area 区
	 * @throws Exception 
	 */
	@RequestMapping("/register_project")
	public void registerProject(Project project,String province, String market, String area,User user) throws Exception{
		project.setCity(province + "-" + market + "-" + area);
		String pId = projectService.addRegisterProject(project,user);
		httpClientService.doPost(SmsContext.httpClientURL+"/create/database/auto/"+pId, null);
	}
	
	
	@ResponseBody
	@RequestMapping("/to_getOneShopsListInMapByOnLogin")
	public List<Shops> getOneShopsListInMap() {
//		String address = this.request.getRemoteAddr();
		// 获取session中的用户信息
//		User u = (User) this.request.getSession().getAttribute("userInfo");
//		// 获取持久化用户对象
//		User user = userService.findById(u.getUserId());
		List<Shops> list = projectService.findOneAreaShopsListInMap();
		return list;
	}
	
	//machine登陆页面____________________
	@RequestMapping("/to_goToMachineLogin")
	public String goToMachineLogin(){
		return "machine/index";
	}
//__________________________________________________________________________
	@RequestMapping("/totototo")
	public String totototo(){
		return "machine/index33";
	}
	
	
	@ResponseBody
	@RequestMapping("/get_announcement")
	public Set<String> RandomAnnouncement(){
		Set<String> list = projectService.findRandAnnouncement();
		return list;
	}
	
	
	@RequestMapping("/sss")
	public void satyss(){
		try {
			String doGet = httpClientService.doPost(SmsContext.httpClientURL+"/charts/visit/10000/2017-06-01/2017-08-01", null);
			System.out.println(doGet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/webpack")
	public String webpack(){
		return "house/creathouse";	
	}
	
	@RequestMapping("/new_e")
	public String newImportExcelTest(){
		return "test/excel";
	}
	
	@ResponseBody
	@RequestMapping("/import_new")
	public Object newImportExcel(MultipartFile file) throws Exception{
		String name = file.getOriginalFilename();
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
	 * 导出房源
	 * @param buildingId
	 */
	//@ResponseBody
	/*@RequestMapping("/manage_getHousesExcelByBuildingId")
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
//		row2.createCell(ii++).setCellValue("区位号");
//		row2.createCell(ii++).setCellValue("楼栋号");
//		row2.createCell(ii++).setCellValue("单元号");
//		row2.createCell(ii++).setCellValue("楼层号");
//		row2.createCell(ii++).setCellValue("房号");
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
//			row2.createCell(z2++).setCellValue(house.getDirect());
//			row2.createCell(z2++).setCellValue(house.getBuildingNo());
//			row2.createCell(z2++).setCellValue(house.getUnit());
//			row2.createCell(z2++).setCellValue(house.getFloor());
//			row2.createCell(z2++).setCellValue(house.getHouseNo());
			
			if (house.getBuildingId() != null && !"".equals(house.getBuildingId())){
				is.add(house.getBuildingId());
			}
			if (house.getUnitId() != null && !"".equals(house.getUnitId())){
				is2.add(house.getUnitId());
			}
			
			//projectId = house.getProjectId();
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
//		row5.createCell(z5).setCellValue(projectId);
		
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
	}*/
	
	
}
