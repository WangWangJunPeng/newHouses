package com.sc.tradmaster.service.user.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.ManaerChartData;

public class AnalyzeTagsDTO {
	
	private Integer categoryId;
	private String categoryName;
	private List<ManaerChartData> mcdList;
	//项目id
	private String projectId;
	//优先级
	private String priority;
	//是否是定制(0:非定制、1:定制)
	private String isMade;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ManaerChartData> getMcdList() {
		return mcdList;
	}
	public void setMcdList(List<ManaerChartData> mcdList) {
		this.mcdList = mcdList;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIsMade() {
		return isMade;
	}
	public void setIsMade(String isMade) {
		this.isMade = isMade;
	}
	@Override
	public String toString() {
		return "AnalyzeTagsDTO [categoryId=" + categoryId + ", categoryName=" + categoryName + ", mcdList=" + mcdList
				+ ", projectId=" + projectId + ", priority=" + priority + ", isMade=" + isMade + "]";
	}
}
