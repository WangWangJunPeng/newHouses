package com.sc.tradmaster.service.flowRecords;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.project.impl.dto.ContractRecordsFR;
import com.sc.tradmaster.service.projectGuide.impl.dto.CreatNewProjectGuide;

public interface FlowRecordsService {
	/**
	 * 添加案场带看业务定流水业务
	 * @param user
	 * @param operateSort 
	 * @param oldpg
	 * @param pg
	 */
	void addProjectGuideFR(User user, Integer operateSort, CreatNewProjectGuide oldpg, ProjectGuide newpg);

	/**
	 * 订单成交流水
	 * @param u
	 * @param operateSort 操作类别
	 * @param orderSort	  订单流水状态
	 * @param crFr
	 * @param crs
	 */
	void addOdersFR(User u, int operateSort, int orderSort, ContractRecordsFR crFr, ContractRecords crs);
}
