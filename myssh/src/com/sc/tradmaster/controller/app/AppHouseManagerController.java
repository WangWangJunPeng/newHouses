package com.sc.tradmaster.controller.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseDetails;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.house.impl.dto.SalesHouseDTO;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.projectGuide.ProjectGuideService;
import com.sc.tradmaster.service.user.UserService;

import net.sf.json.JSONObject;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class AppHouseManagerController extends BaseController {
	
	@Resource(name = "agentVisitRecordService")
	private AgentVisitRecordService agentVisitRecordService;

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "houseService")
	private HouseService houseService;
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "projectGuideService")
	private ProjectGuideService projectGuideService;
	
	/**
	 * 房源对比 agentHouseCompetion	houseCompetion
	 */
	@RequestMapping("/house_comparison")
	public String houseCompetion(Integer houseNumOne,Integer houseNumTwo){
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Set<Role> role = user.getRoleId();
		Integer roleId = null;
		for(Role r :role){
			roleId = r.getRoleId();
		}
		if(roleId==3){
			//调用置业顾问后台Service
			return "app/house/agentHouseCompetion";
		}else{//roleId==1 || roleId==2
			//调用中介后台Service
			return "app/house/houseCompetion";
		}
	}
	/**
	*房源详细信息	agentHouseMoreDeatil	houseMoreDeatil
	*/
	@RequestMapping("/house_detail_info")
	public String houseDetailInfo(Integer houseNum,Model model){
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		HouseDetails houseDet = projectService.findHouseJvtiInfo(houseNum);
		House house = projectService.findHouseById(houseNum);
		model.addAttribute("data", house);
		model.addAttribute("details", houseDet);
		Set<Role> role = user.getRoleId();
		Integer roleId = null;
		for(Role r :role){
			roleId = r.getRoleId();
		}
		if(roleId==3){
			//调用置业顾问后台Service
			return "app/house/agentHouseMoreDeatil";
		}else{//roleId==1 || roleId==2
			//调用中介后台Service
			return "app/house/houseMoreDeatil";
		}
	}
	
	/**
	 * 房源详细对比信息	agentHouseMoreCompetion	houseMoreCompetion
	 * @param houseNum
	 * @return
	 */
	@RequestMapping("/house_detail_comparison")
	public String houseDetailInfoComparison(Integer houseNumOne,Integer houseNumTwo){
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Set<Role> role = user.getRoleId();
		Integer roleId = null;
		for(Role r :role){
			roleId = r.getRoleId();
		}
		if(roleId==3){
			//调用置业顾问后台Service
			return "app/house/agentHouseMoreCompetion";
		}else{//roleId==1 || roleId==2
			//调用中介后台Service
			return "app/house/houseMoreCompetion";
		}
	}
	
	/**
	 * //房源关联交付类型再关联交付详情
	 *	毛坯交付详情查看	agentMpeiDetail	mpeiDetail
	 */
	@RequestMapping("/house_coarse_delivery_detail")
	public String houseCoarseDeliveryDetailInfo(Integer houseNum){
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Set<Role> role = user.getRoleId();
		Integer roleId = null;
		for(Role r :role){
			roleId = r.getRoleId();
		}
		if(roleId==3){
			//调用置业顾问后台Service
			return "app/house/agentMpeiDetail";
		}else{//roleId==1 || roleId==2
			//调用中介后台Service
			return "app/house/mpeiDetail";
		}
	}
	
	/**
	 * 精装交付详情查看	agentJzhuangDetail	jzhuangDetail
	 */
	@RequestMapping("/house_exquisite_delivery_detail")
	public String houseExquisiteDeliveryDetailInfo(Integer houseNum){
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Set<Role> role = user.getRoleId();
		Integer roleId = null;
		for(Role r :role){
			roleId = r.getRoleId();
		}
		if(roleId==3){
			//调用置业顾问后台Service
			return "app/house/agentJzhuangDetail";
		}else{//roleId==1 || roleId==2
			//调用中介后台Service
			return "app/house/jzhuangDetail";
		}
	}
	
	
	@RequestMapping("/house_toAgentHouseCompetionList")
	public String toAgentHouseCompetionList(){
		return "app/house/agentHouseCompetionList";
	}
	
	@RequestMapping("/house_toAgentHouseComCanChooseList")
	public String toAgentHouseComCanChooseList(){
		return "app/house/agentHouseComCanChooseList";
	}
	
	
}
