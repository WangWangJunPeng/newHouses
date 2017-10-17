package com.sc.tradmaster.service.flowRecords.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.ContractRecordsFlowRecord;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectGuideFlowRecord;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.flowRecords.FlowRecordsService;
import com.sc.tradmaster.service.project.impl.dto.ContractRecordsFR;
import com.sc.tradmaster.service.projectGuide.impl.dto.CreatNewProjectGuide;
import com.sc.tradmaster.utils.DateUtil;

import net.sf.json.JSONObject;

@Service("flowRecordsService")
public class FlowRecordsServiceImpl implements FlowRecordsService {
	
	@Resource(name="baseDao")
	private BaseDao baseDao;

	//案场带看业务定义流水记录
	@Override
	public void addProjectGuideFR(User user, Integer operateSort, CreatNewProjectGuide oldpg, ProjectGuide pg) {
		String historyRecord = JSONObject.fromObject(oldpg).toString();
		String newRecord = JSONObject.fromObject(pg).toString();
		ProjectGuideFlowRecord pgf = new ProjectGuideFlowRecord();
		pgf.setOperateSort(operateSort);
		pgf.setOperateTime(DateUtil.format(new Date()));
		pgf.setOperateUserId(user.getUserId());
		pgf.setOperateUserName(user.getUserCaption());
		pgf.setOperateUserPhone(user.getPhone());
		pgf.setHistoryRecord(historyRecord);
		pgf.setNewRecord(newRecord);
		baseDao.saveOrUpdate(pgf);
	}

	//订单成交流水记录
	@Override
	public void addOdersFR(User u, int operateSort, int orderSort, ContractRecordsFR crFr, ContractRecords crs) {
		String historyRecord = JSONObject.fromObject(crFr).toString();
		String newRecord = JSONObject.fromObject(crs).toString();
		ContractRecordsFlowRecord pgf = new ContractRecordsFlowRecord();
		pgf.setOperateSort(operateSort);
		pgf.setOrderSort(orderSort);
		pgf.setOperateTime(DateUtil.format(new Date()));
		pgf.setOperateUserId(u.getUserId());
		pgf.setOperateUserName(u.getUserCaption());
		pgf.setOperateUserPhone(u.getPhone());
		pgf.setHistoryRecord(historyRecord);
		pgf.setNewRecord(newRecord);
		pgf.setRecordNo(crs.getRecordNo());
		pgf.setProjectId(crs.getProjectId());
		baseDao.saveOrUpdate(pgf);
	}
	
}
