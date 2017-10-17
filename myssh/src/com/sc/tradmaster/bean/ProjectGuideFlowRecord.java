package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_projectguideFR")
public class ProjectGuideFlowRecord {
	//流水记录主键
	private Integer fr_pgId;
	//操作类别(添加：0,删除：1；修改：2)
	private Integer operateSort;
	//操作时间
	private String operateTime;
	//操作人id
	private String operateUserId;
	//操作人姓名
	private String operateUserName;
	//操作人手机号
	private String operateUserPhone;
	//历史记录
	private String historyRecord;
	//更新后的记录
	private String newRecord;
	
	@GeneratedValue
	@Id
	public Integer getFr_pgId() {
		return fr_pgId;
	}
	public void setFr_pgId(Integer fr_pgId) {
		this.fr_pgId = fr_pgId;
	}
	public Integer getOperateSort() {
		return operateSort;
	}
	public void setOperateSort(Integer operateSort) {
		this.operateSort = operateSort;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	public String getOperateUserPhone() {
		return operateUserPhone;
	}
	public void setOperateUserPhone(String operateUserPhone) {
		this.operateUserPhone = operateUserPhone;
	}
	public String getHistoryRecord() {
		return historyRecord;
	}
	public void setHistoryRecord(String historyRecord) {
		this.historyRecord = historyRecord;
	}
	public String getNewRecord() {
		return newRecord;
	}
	public void setNewRecord(String newRecord) {
		this.newRecord = newRecord;
	}
}
