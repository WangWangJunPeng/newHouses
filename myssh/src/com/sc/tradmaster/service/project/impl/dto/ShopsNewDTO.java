package com.sc.tradmaster.service.project.impl.dto;

public class ShopsNewDTO {
	private String quyu;
	private String quyuId;
	private Integer shopsNum;
	private Integer agentNum;
	private Integer guideRecordsNum;
	private Integer haveDealNum;
	private Integer haveApproveNum;
	private String photoUrl;
	
	public String getQuyu() {
		return quyu;
	}
	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}
	
	public String getQuyuId() {
		return quyuId;
	}
	public void setQuyuId(String quyuId) {
		this.quyuId = quyuId;
	}
	public Integer getAgentNum() {
		return agentNum;
	}
	public void setAgentNum(Integer agentNum) {
		this.agentNum = agentNum;
	}
	public Integer getGuideRecordsNum() {
		return guideRecordsNum;
	}
	public void setGuideRecordsNum(Integer guideRecordsNum) {
		this.guideRecordsNum = guideRecordsNum;
	}
	public Integer getHaveDealNum() {
		return haveDealNum;
	}
	public void setHaveDealNum(Integer haveDealNum) {
		this.haveDealNum = haveDealNum;
	}
	
	public Integer getShopsNum() {
		return shopsNum;
	}
	public void setShopsNum(Integer shopsNum) {
		this.shopsNum = shopsNum;
	}
	
	public Integer getHaveApproveNum() {
		return haveApproveNum;
	}
	public void setHaveApproveNum(Integer haveApproveNum) {
		this.haveApproveNum = haveApproveNum;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	@Override
	public String toString() {
		return "ShopsNewDTO [quyu=" + quyu + ", quyuId=" + quyuId + ", shopsNum=" + shopsNum + ", agentNum=" + agentNum
				+ ", guideRecordsNum=" + guideRecordsNum + ", haveDealNum=" + haveDealNum + ", haveApproveNum="
				+ haveApproveNum + ", photoUrl=" + photoUrl + "]";
	}
}
