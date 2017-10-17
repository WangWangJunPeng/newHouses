package com.sc.tradmaster.service.group;

import java.util.List;

import com.sc.tradmaster.bean.Group;
import com.sc.tradmaster.bean.Project;

/**
 * 集团服务层
 * @author cdh 2017-10-10
 *
 */
public interface GroupService {

	/**
	 * 报表 -- 新建板块
	 * @param groupId
	 * @param projectIds
	 * @param moduleName
	 * @return
	 */
	boolean addNewModuleByGroup(String groupId, String projectIds, String moduleName);
	
	/**
	 * 根据集团groupId查找项目集合
	 * @param groupId
	 * @return
	 */
	List<Project> findProjectsByGroupId(String groupId);
	
	
	/**
	 * 根据groupId查找group
	 * @param groupId
	 * @return
	 */
	Group findGroupById(String groupId);
	
	/**
	 * 报表 -- 删除板块中的单个项目
	 * @param moduleId
	 * @param projectId
	 * @return
	 */
	boolean deleteModuleProjectByModuleIdAndProjectId(String moduleId, String projectId);
	
	
	/**
	 *	删除某个板块
	 * @param moduleId
	 * @return
	 */
	boolean deleteModuleByModuleId(String moduleId);
	
}
