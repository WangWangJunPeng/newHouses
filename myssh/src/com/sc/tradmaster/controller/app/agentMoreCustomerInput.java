package com.sc.tradmaster.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.agent.AgentMoreCustomerInputService;
import com.sc.tradmaster.service.agent.impl.visitDTO.AgentMoreCustomerInputDTO;
import com.sc.tradmaster.service.user.UserService;

@Controller
public class agentMoreCustomerInput extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "agentMoreCustomerInputService")
	private AgentMoreCustomerInputService agentMoreCustomerInputService;

	/**
	 * 任务列表 按批次列出
	 * 
	 * @param dateStr
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/agent_my_task_more_customer_input")
	public Object getMyTaskOfSelectDate(String dateStr) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List<AgentMoreCustomerInputDTO> visitList = agentMoreCustomerInputService.findVisitDataByUser(user, dateStr);
		return visitList;
	}

	/**
	 * 批次到访客户
	 * 
	 * @param batchVisitNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/agent_my_task_same_batch_customers")
	public Object getCurrentBatchVisit(Integer batchVisitNo) {
		List<AgentMoreCustomerInputDTO> visitList = agentMoreCustomerInputService.findBatchVisitDataByBatchVisitNo(batchVisitNo);
		return visitList;
	}

	/**
	 * 多客户录入 号码录入确认操作 添加客户，输入手机号码点击"确定"按钮的业务逻辑 更新主到访的到访信息和新增次到访的到访数据，新增或更新案场客户信息
	 * 
	 * @param phone
	 * @param visitNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/agent_my_task_fill_phone")
	public Object addOrupdateVisitAndProjectCus(String phone, Integer visitNo) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = new HashMap<>();
		try {
			map = agentMoreCustomerInputService.addOrUpdateVisitAndProCus(user,phone, visitNo);
		} catch (Exception e) {
			map.put("code", "异常");
		}
		return map;
	}

}
