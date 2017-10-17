package com.sc.tradmaster.service.agent;

import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.service.agent.impl.visitDTO.AgentMoreCustomerInputDTO;

/**
 * 2017-10-10
 * @author grl
 *
 */
public interface AgentMoreCustomerInputService {

	/**
	 * 获取任务信息
	 * @param user
	 * @param dateStr
	 * @return
	 */
	List<AgentMoreCustomerInputDTO> findVisitDataByUser(User user, String dateStr);

	/**
	 * 获取同批次到访客户列表
	 * @param batchVisitNo
	 * @return
	 */
	List<AgentMoreCustomerInputDTO> findBatchVisitDataByBatchVisitNo(Integer batchVisitNo);

	/**
	 * 多客户录入    号码录入确认操作
	 * @param user 
	 * @param phone
	 * @param visitNo
	 * @return
	 * @throws Exception 
	 */
	Map addOrUpdateVisitAndProCus(User user, String phone, Integer visitNo) throws Exception;

}
