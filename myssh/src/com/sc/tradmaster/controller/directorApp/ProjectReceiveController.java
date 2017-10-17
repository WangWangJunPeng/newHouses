package com.sc.tradmaster.controller.directorApp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.HttpClientService;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

/**
 * 2017-05-22
 * 
 * @author grl
 *
 */
// 案场接访
@Controller("ProjectReceiveController")
public class ProjectReceiveController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "projectReceiveService")
	private ProjectReceiveService projectReceiveService;

	@Resource(name="httpClientService")
	private HttpClientService httpClientService;
	
	// 跳转今日接访首页
	@RequestMapping("/to_director_today_receiver_index")
	public String toTodayReceiveFirstPage() {
		return "app/director/index";
	}

	//接访数据页面控制器
	@RequestMapping("/to_director_liberatingPage")
	public String toLiberatingPage(){
		return "app/director/liberatingData";
	}
	
	//成交数据跳转控制器
	@RequestMapping("/to_director_successDataPage")
	public String toSuccessDataPage(){
		return "app/director/successData";
	}
	
	//顾问状态详情
	@RequestMapping("/to_agent_info_page")
	public String toAgentInfoPage(Model model,String userId){
		model.addAttribute("userId", userId);
		return "app/director/gwStatusDeatils";
	}
	
	// 获取今日接访首页数据(1 / 1.2 / 1.2.1)
	@ResponseBody
	@RequestMapping("/get_today_receiver_data")
	public Object getTodayReceiveFirstPageData() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = projectReceiveService.findTodayReceiveFirstPageData(user);

		return map;
	}

	// 获取顾问状态数据(1.1.2)
	@ResponseBody
	@RequestMapping("/get_agent_status_data")
	public Object getAgentStatusData(String userId, String startDate, String endDate, String isCompare) {
		Map map = new HashMap<>();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			//如果起始时间不为空，走归集方法，否则走原有方法
			if(SmsContext.toGuiJiFun /*&& startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")*/){
				//String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//String startDayStr = DateUtil.format(DateUtil.parse(startDate), DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//如果起始时间不是当天，走归集方法，否则走原有方法；
				//if(!startDayStr.equals(todayStr)){
					//获取顾问信息
					Map aMap = projectReceiveService.findAgentStatusDataById(user, userId, startDate, endDate);
					//获取归集数据
					String url = SmsContext.httpClientURL+"/get_agent_status_data";
					Map cMap = new HashMap<>();
					cMap.put("proId",user.getParentId());
					cMap.put("startDate", startDate);
					cMap.put("endDate", endDate);
					cMap.put("agentId", userId);
					String doGet = httpClientService.doPost(url, cMap);
					if(doGet!=null){
						map = JSON.parseObject(doGet, HashMap.class);
					}
					map.putAll(aMap);
				//}else{
					//原有方法
					//map = projectReceiveService.findAgentStatusDataById(user, userId, startDate, endDate, isCompare);
				//}
			}else{
				//原有方法
				map = projectReceiveService.findAgentStatusDataById(user, userId, startDate, endDate, isCompare);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 202);
		}
		
		return map;
	}

	/*// 跳转详细接访数据页面
	@RequestMapping("/to_toDay_detail_Receive_page")
	public String toToDayDetailedReceivePage() {
		return "app/director/detailedReceive.jsp";
	}*/

	// 获取详细接访数据(1.1)
	@ResponseBody
	@RequestMapping("/get_toDay_detail_Receive_data")
	public Map getToDayDetailedReceiveData(String startDate, String endDate) throws Exception {
		Map map = new HashMap<>();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			//如果起始时间不为空，走归集方法，否则走原有方法
			if(SmsContext.toGuiJiFun /*&& startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")*/){
				//String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//String startDayStr = DateUtil.format(DateUtil.parse(startDate), DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//如果起始时间不是当天，走归集方法，否则走原有方法；
				//if(!startDayStr.equals(todayStr)){
					String url = SmsContext.httpClientURL+"/get_toDay_detail_Receive_data";
					Map cMap = new HashMap<>();
					cMap.put("proId",user.getParentId());
					cMap.put("startDate", startDate);
					cMap.put("endDate", endDate);
					String doGet = httpClientService.doPost(url, cMap);
					if(doGet!=null){
						map = JSON.parseObject(doGet, HashMap.class);
					}
				/*}else{
					//原有方法
					map = projectReceiveService.findToDayDetailedReceiveDataByTime(user, startDate, endDate);
				}*/
			}else{
				//原有方法
				map = projectReceiveService.findToDayDetailedReceiveDataByTime(user, startDate, endDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 202);
		}
		return map;
	}

	// 跳转成交数据页面
	@RequestMapping("/to_deal_data_page")
	public String toDealDataPage() {
		return "app/director/DealDataPage.jsp";
	}
	
	// 跳转到今日案场顾问状态
	@RequestMapping("/to_today_agent_status_page")
	public String toTodayAgentStatusPage() {
		return "app/director/anChGwStatus";
	}

	// 获取详细成交数据(1.2.2)
	@ResponseBody
	@RequestMapping("/get_deal_data")
	public Object getDealDataData(String startDate, String endDate) {
		Map map = new HashMap<>();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		try {
			//如果起始时间不为空，走归集方法，否则走原有方法
			if(SmsContext.toGuiJiFun /*&& startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")*/){
				//String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//String startDayStr = DateUtil.format(DateUtil.parse(startDate), DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//如果起始时间不是当天，走归集方法，否则走原有方法；
				//if(!startDayStr.equals(todayStr)){
					String url = SmsContext.httpClientURL+"/get_deal_data";
					Map cMap = new HashMap<>();
					cMap.put("proId",user.getParentId());
					cMap.put("startDate", startDate);
					cMap.put("endDate", endDate);
					String doGet = httpClientService.doPost(url, cMap);
					if(doGet!=null){
						map = JSON.parseObject(doGet, HashMap.class);
					}
				/*}else{
					//原有方法
					map = projectReceiveService.findDealDataByTime(user, startDate, endDate);
				}*/
			}else{
				//原有方法
				map = projectReceiveService.findDealDataByTime(user, startDate, endDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 202);
		}
		return map;
	}

	// 获取今日案场顾问状态 热力图数据(1.1.1)
	@ResponseBody
	@RequestMapping("/get_twodays_visit_data")
	public Object getTwoDaysVisitData() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = projectReceiveService.findTwoDaysVisitData(user);
		return map;
	}

	//获取今日案场顾问状态  顾问接访信息(1.1.1)
	@ResponseBody
	@RequestMapping("/get_today_agent_status_data")
	public Object getToTodayAgentStatusDate(String sortSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map<String,Object> map = projectReceiveService.findToTodayAgentStatusData(user,sortSign);
		return map;
	}

}
