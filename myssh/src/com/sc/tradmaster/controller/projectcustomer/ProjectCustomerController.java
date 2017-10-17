package com.sc.tradmaster.controller.projectcustomer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.TagType;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.projectcustomer.ProjectCustomerService;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerAnalyze;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerManagerInfoDTO;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.Page;

/**
 * 2017-02-08
 * @author grl
 *
 */
@Controller("projectCustomerController")
public class ProjectCustomerController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "projectCustomerService")
	private ProjectCustomerService projectCustomerService;
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "tagService")
	private TagService tagService;

	/**
	 * 客户管理-页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/pro_customer_manager_page_info")
	public String toCustomerManagePage() {
		return "projectCustomer/customerManagement";
	}
	
	/**
	 * 中介店长客户管理-页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/shop_customer_manager_page_info")
	public String toShopCustomerManagePage() {
		return "projectCustomer/shopCustomerManager";
	}

	/**
	 * 客户管理 list
	 * @param start
	 * @param limit
	 * @param selectValue
	 */
	@ResponseBody
	@RequestMapping("/list_pro_customers_info")
	public void listCustomerInfo(Integer start, Integer limit, String selectValue) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user != null) {
			projectCustomerService.findProCustomersByUser(user, selectValue, page);
			this.outPageString(page);
		}
	}

	/**
	 * 客户管理 修改归属置业顾问 菜单
	 */
	@ResponseBody
	@RequestMapping("/grl_get_agents_menu")
	public void getAgentsMenu() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user.getParentId() != null && !user.getParentId().equals("")) {
			List<Map<String, String>> agMenuList = projectCustomerService.findAgentsToMenu(user.getParentId());
			this.outListString(agMenuList);
		}
	}
	
	/**
	 * 客户职业顾问归属设置
	 * @param proCursId
	 * @param agentId
	 * @return
	 */
	@RequestMapping("/update_pro_customers_info")
	public String setUpNewAgentForProCustomer(String[] proCursId , String agentId){
		if(proCursId!=null){
			projectCustomerService.addOrUpdateProCustomerownerAgent(proCursId,agentId);
		}
		return "redirect:/pro_customer_manager_page_info";
	}
	
	/**
	 * shop 客户管理 list
	 * @param start
	 * @param limit
	 * @param selectValue
	 */
	@ResponseBody
	@RequestMapping("/list_shop_customers_info")
	public void listShopCustomerInfo(Integer start, Integer limit, String selectValue) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (user != null) {
			projectCustomerService.findShopCustomersByUser(user, selectValue, page);
			this.outPageString(page);
		}
	}
	
	/**
	 * 进入用户信息的页面
	 * @return
	 */
	@RequestMapping("to_customer_info_page")
	public ModelAndView toCustomerInfoPage(ProjectCustomers customer){
		
		if(customer == null){
			ModelAndView mv = new ModelAndView();
			mv.addObject("edata", 202);//当phone为空
			mv.setViewName("forword:/pro_customer_manager_page_info");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView();
			System.out.println(customer);
			mv.addObject("customerInfo", customer);
			mv.setViewName("");//跳转的页面，后续有页面补上
			return mv;
		}
	}
	
	
	/**
	 * ajax异步获取客户的详细信息包括标签
	 * @param customer
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_customer_tag_info")
	public Map<String, Object> getCustomerInfo(ProjectCustomers customer){
		
		//根据当前登录的用户获取当前的案场
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		Project pro = projectService.findProjectById(user.getParentId());
		
		Map<String, Object> map = projectCustomerService.findCustomerCAndVInfo(customer.getProjectCustomerPhone(), pro.getProjectId());
		
		//获取客户的动态
		List<Mydynamic> dList = projectCustomerService.findCustomerDynamicByCustomerId(customer.getProjectCustomerId(), "projectCustomerId");
		
		map.put("dynamic", dList);
		
		//查找所有用户的标签
		//首先要根据用户的phone查找用户的id
		ProjectCustomers cust = projectCustomerService.findProjectCustomersByPhone(customer.getProjectCustomerPhone());
		
		List<TagLib> tagLibList = tagService.showTagLib(null, pro.getProjectId(), null, null);
		Integer tagTypeId = 0;
		for(TagLib t : tagLibList){
			if("客户标签".equals(t.getTagTypeName())){
				tagTypeId = t.getTagTypeId();
			}
		}
		//DONE 查找客户选中的所有的标签和标签类目
		List<TagLib> list = tagService.showTagLib_use(cust.getProjectCustomerId(), tagTypeId, pro.getProjectId());
		
		map.put("customerTag", list);
		
		return map;
	}
	
	/**
	 * 案场经理个人中心---------客户管理跳转控制器 maoxy
	 * @return
	 */
	@RequestMapping("/to_pCustomerManager")
	public String toProjectCustomerManager(){
		return "directorWeb/clientManagement";
	}
	
	/**
	 * 案场经理个人中心---------根据筛选条件筛选案场客户maoxy
	 * @param coustomerStatus 客户状态条件(1-已到访,2-认购,3-付款,4-签约,5-认购否决,6-退款,7-撤单)
	 * @param visitNum 到访次数条件(1-一次,2-两次,3-三次以上)
	 * @param projectAgentId 接待顾问Id条件
	 * @param tagIds 标签id条件
	 * @param page 当前页
	 * @param num 一页显示条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search_projectCustomer")
	public Map<String,Object> searchProjectCustomers(Integer[] coustomerStatus,Integer[] visitNum,String[] projectAgentId,Integer[] tagIds,int page,int num){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		return projectCustomerService.searchProjectCoustomers(coustomerStatus, visitNum, projectAgentId, tagIds, projectId, page, num);
	}
	/**
	 * 案场经理个人中心---------根据姓名或者电话模糊匹配客户并且分页maoxy 用于首页搜索
	 * @param srarchCondition
	 * @param page
	 * @param num
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search_projectCustomerOnLike")
	public Map<String,Object> srarchProjectAsLike(String srarchCondition,int page,int num){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		return projectCustomerService.searchProjectCoustomers(projectId,srarchCondition,page,num);
		
		
	}
	/**
	 * 案场经理个人中心---------根据姓名或者电话模糊匹配客户maoxy 用于转换归属
	 * @param srarchCondition
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search_projectCustomerOnChangeOwner")
	public List<ProjectCustomers> searchProjectCustomerAll(String srarchCondition){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		return projectCustomerService.searchProjectCustomer(projectId,srarchCondition);
	}
	/**
	 * 案场经理个人中心---------获取标签筛选条件maoxy
	 * @return
	 */
	@RequestMapping("/get_tagSearch")
	@ResponseBody
	public Map<String,List<Tag>> getTagSearch(){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		List<TagType> showRootTagType = tagService.showRootTagType(projectId, 0);
		Integer tagTypeId = -1;
		for(TagType tt : showRootTagType){
			if("客户标签".equals(tt.getTagTypeName())){
				tagTypeId = tt.getTagTypeId();
			}
		}
		Map<String,List<Tag>> result = tagService.findTagTypeAndTag2Map(tagTypeId,projectId);
		return result;
	}
	
	
	/**
	 * 案场经理个人中心---------按顾问调整归属maoxy
	 * @param originalId
	 * @param neoId
	 * @return
	 */
	@RequestMapping("/changeOwnerByAgent")
	@ResponseBody
	public Map<String,Object> updateProjectCustomerOwner(String originalId,String neoId){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			int a = projectCustomerService.update_projectCustomerOwner(originalId, neoId);
			result.put("status", 200);
			result.put("message", a);
		} catch (Exception e) {
			result.put("status", 202);
			result.put("message", e.getMessage());
		}
		return result;
	}
	/**
	 * 案场经理个人中心---------按客户调整归属maoxy
	 * @param projectCustomerId
	 * @param agentId
	 * @return
	 */
	@RequestMapping("/changeOwnerByCustomerId")
	@ResponseBody
	public Map<String,Object> updateProjectCustomerOwner2(String[] projectCustomerId,String agentId){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			projectCustomerService.update_projectCustomerOwner(projectCustomerId, agentId);
			result.put("status", 200);
			result.put("message", "更改成功");
		} catch (Exception e) {
			result.put("status", 202);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	/**
	 * 案场经理个人中心---------客户信息详情maoxy
	 * @param projectCustomerId
	 * @param model
	 * @return
	 */
	@RequestMapping("/projectCustomerInfoManager")
	public String getProjectCustomerInfo(String projectCustomerId,Model model){
		CustomerManagerInfoDTO dto = projectCustomerService.findOneCustomerInfo(projectCustomerId);
		model.addAttribute("customerInfo", dto);
		return "directorWeb/clientMessage";
		
	}
	/**
	 * 案场经理个人中心---------客户分析maoxy
	 * @param model
	 * @return
	 */
	@RequestMapping("/customerAnalyze")
	public String toAnalyzePage(Model model){
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		CustomerAnalyze analyze = projectCustomerService.getAnalyze(projectId);
		model.addAttribute("analyze", analyze);
		return "directorWeb/clientAnalyze";
	}
	/**
	 * 查询第二个号码是否重复
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/matchCustomerPhone")
	public Map<String,Object> matchProjectCustomerPhone(String phone){
		Map<String,Object> result = new HashMap<String,Object>();
		User user = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = user.getParentId();
		try{
			projectCustomerService.matchPhone(phone,projectId);
			result.put("status", 200);
			result.put("message", "该号码可以使用");
		}catch(Exception e){
			result.put("status", 201);
			result.put("message", "号码已存在");
		}
		return result;
	}
	
	
	
	
	

}
