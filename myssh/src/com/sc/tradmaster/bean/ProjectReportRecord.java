package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 项目周报列表
 * @author cdh 2017-09-14
 *
 */
@Entity
@Table(name="t_projectreportrecord")
public class ProjectReportRecord {

	//主键
	private Integer id;
	//项目id
	private String projectId;
	//项目名称
	private String projectName;
	//数据报的类别：week:周报 month月报 quarter 季报 half 半年报 year年报  other 阶段报
	private String reportName;
	//excel文件链接
	private String url;
	//创建时间
	private String createTime;
	//数据开始时间
	private String startTime;
	//数据结束时间
	private String endTime;
	
	@Id
    @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "ProjectReportRecord [id=" + id + ", projectId=" + projectId + ", projectName=" + projectName
				+ ", reportName=" + reportName + ", url=" + url + ", createTime=" + createTime + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	
	
}
