package com.sc.tradmaster.service.group.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sc.tradmaster.bean.Group;
import com.sc.tradmaster.bean.GroupStatementModule;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.group.GroupService;
import com.sc.tradmaster.service.group.impl.dto.GroupModuleHelper;

/**
 * 集团服务层
 * @author cdh 2017-10-10
 *
 */
@Service(value="groupService")
public class GroupServiceImpl implements GroupService{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public boolean addNewModuleByGroup(String groupId, String projectIds, String moduleName) {
		
		if(StringUtils.isEmpty(moduleName))
			return false;
		if(StringUtils.isEmpty(projectIds))
			return false;
		if(StringUtils.isEmpty(groupId))
			return false;
		
		try{
			
			GroupStatementModule gs = new GroupStatementModule();
			gs.setGroupId(groupId);
			gs.setModuleName(moduleName);
			gs.setProjectIds(projectIds);
			gs.setStatus(GroupModuleHelper.STATUS_DEFAULT);
			
			baseDao.save(gs);
			
			return true;
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<Project> findProjectsByGroupId(String groupId) {
		
		List<Project> list = new LinkedList<>();
		
		if(StringUtils.isEmpty(groupId))
			return list;
		Group group = findGroupById(groupId);
		String[] projectIds = group.getProjectIds().split(",");
		for (int i = 0; i < projectIds.length; i++) {
			Project project = (Project) baseDao.findByHql("from Project where projectId = '" + projectIds[i] + "'");
			list.add(project);
		}
		
		return list;
	}

	@Override
	public Group findGroupById(String groupId) {
		
		if(StringUtils.isEmpty(groupId))
			return null;
		
		String hql = "from Group where groupId = '" + groupId + "'";
		
		Group group = (Group) baseDao.findByHql(hql);
		
		return group;
	}

	@Override
	public boolean deleteModuleProjectByModuleIdAndProjectId(String moduleId, String projectId) {
		/**
		 * 实现思路：
		 * 1.首先根据板块id获取板块
		 * 2.将板块中的projectId串取出来
		 * 3.遍历并将目标id去除然后重新组装
		 * 4.更新
		 */
		if(StringUtils.isEmpty(moduleId))
			return false;
		if(StringUtils.isEmpty(projectId))
			return false;
		
		//获取板块
		String hql = "from GroupStatementModule where id = " + moduleId;
		GroupStatementModule gs = (GroupStatementModule) baseDao.findByHql(hql);
		if(gs == null)
			return false;
		//取出projectId串
		String[] projectIds = gs.getProjectIds().split(",");
		String tags = "";
		//遍历并去除目标id
		for (int i = 0; i < projectIds.length; i++) {
			if(!projectIds[i].equals(projectId)){
				tags += projectIds[i];
				if(i!=projectIds.length-1){
					tags += ",";
				}
			}
		}
		
		//重新组装并更新
		gs.setProjectIds(tags);
		baseDao.saveOrUpdate(gs);
		
		
		return true;
	}

	@Override
	public boolean deleteModuleByModuleId(String moduleId) {
		if(StringUtils.isEmpty(moduleId))
			return false;
		
		String hql = "from GroupStatementModule where id = '" + moduleId + "'";
		GroupStatementModule gs = (GroupStatementModule) baseDao.findByHql(hql);
		gs.setStatus(GroupModuleHelper.STATUS_DELETE);
		baseDao.saveOrUpdate(gs);
		return true;
	}
	
	



	
}
