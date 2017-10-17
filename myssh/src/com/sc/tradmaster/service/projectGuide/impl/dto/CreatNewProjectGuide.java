package com.sc.tradmaster.service.projectGuide.impl.dto;

import com.sc.tradmaster.bean.ProjectGuide;

public class CreatNewProjectGuide {
	// 案场id
	private String projectId;
	// 是否上货架
	private Integer isAvailable;
	// 有效天数
	private Integer validDays;
	// 0:先付,1:后付固定金额,2:后付百分比
	private Integer payType;
	// 成交后奖励金额
	private Double rewardMoney;
	// 成交后奖励成交金额的百分比
	private Double rewardpercent;
	// 说明
	private String description;
	// 是否支持带看,0:不支持,1:支持
	private Integer isDaiKan;
	// 是否支持异地,0:不支持,1:支持
	private Integer isYiDi;
	// 异地销售佣金
	private Double yiDiSalesCommission;
	private String yiDiSalesCommissionStr;
	// 异地有效天数
	private String yiDiValidity;
	// 是否支持快速结佣,0:不支持,1:支持
	private Integer isFast;
	// 带看佣金
	private Double daiKanMoney;
	private String daiKanMoneyStr;
	// 分销佣金
	private Double fenXiaoMoney;
	private String fenXiaoMoneyStr;
	// 客户保护期
	private String custormerProtectDays;
	
	public static ProjectGuide creatNewPg(ProjectGuide oldpg,ProjectGuide pg){
		oldpg.setProjectId(pg.getProjectId());
		oldpg.setIsAvailable(pg.getIsAvailable());
		oldpg.setValidDays(pg.getValidDays());
		oldpg.setPayType(pg.getPayType());
		oldpg.setRewardMoney(pg.getRewardMoney());
		oldpg.setRewardpercent(pg.getRewardpercent());
		oldpg.setDescription(pg.getDescription());
		oldpg.setIsDaiKan(pg.getIsDaiKan());
		oldpg.setIsYiDi(pg.getIsYiDi());
		oldpg.setYiDiSalesCommission(pg.getYiDiSalesCommission());
		oldpg.setYiDiValidity(pg.getYiDiValidity());
		oldpg.setIsFast(pg.getIsFast());
		oldpg.setDaiKanMoney(pg.getDaiKanMoney());
		oldpg.setFenXiaoMoney(pg.getFenXiaoMoney());
		oldpg.setCustormerProtectDays(pg.getCustormerProtectDays());
		return oldpg;
	}
	
	public CreatNewProjectGuide creatNewPg(ProjectGuide pg){
		this.projectId = pg.getProjectId();
		this.isAvailable = pg.getIsAvailable();
		this.validDays = pg.getValidDays();
		this.payType = pg.getPayType();
		this.rewardMoney = pg.getRewardMoney();
		this.rewardpercent = pg.getRewardpercent();
		this.description = pg.getDescription();
		this.isDaiKan = pg.getIsDaiKan();
		this.isYiDi = pg.getIsYiDi();
		this.yiDiSalesCommission = pg.getYiDiSalesCommission();
		this.yiDiValidity = pg.getYiDiValidity();
		this.isFast = pg.getIsFast();
		this.daiKanMoney = pg.getDaiKanMoney();
		this.fenXiaoMoney = pg.getFenXiaoMoney();
		this.custormerProtectDays = pg.getCustormerProtectDays();
		return this;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Integer getValidDays() {
		return validDays;
	}

	public void setValidDays(Integer validDays) {
		this.validDays = validDays;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Double getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(Double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}

	public Double getRewardpercent() {
		return rewardpercent;
	}

	public void setRewardpercent(Double rewardpercent) {
		this.rewardpercent = rewardpercent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsDaiKan() {
		return isDaiKan;
	}

	public void setIsDaiKan(Integer isDaiKan) {
		this.isDaiKan = isDaiKan;
	}

	public Integer getIsYiDi() {
		return isYiDi;
	}

	public void setIsYiDi(Integer isYiDi) {
		this.isYiDi = isYiDi;
	}

	public Double getYiDiSalesCommission() {
		return yiDiSalesCommission;
	}

	public void setYiDiSalesCommission(Double yiDiSalesCommission) {
		this.yiDiSalesCommission = yiDiSalesCommission;
	}

	public String getYiDiSalesCommissionStr() {
		return yiDiSalesCommissionStr;
	}

	public void setYiDiSalesCommissionStr(String yiDiSalesCommissionStr) {
		this.yiDiSalesCommissionStr = yiDiSalesCommissionStr;
	}

	public String getYiDiValidity() {
		return yiDiValidity;
	}

	public void setYiDiValidity(String yiDiValidity) {
		this.yiDiValidity = yiDiValidity;
	}

	public Integer getIsFast() {
		return isFast;
	}

	public void setIsFast(Integer isFast) {
		this.isFast = isFast;
	}

	public Double getDaiKanMoney() {
		return daiKanMoney;
	}

	public void setDaiKanMoney(Double daiKanMoney) {
		this.daiKanMoney = daiKanMoney;
	}

	public String getDaiKanMoneyStr() {
		return daiKanMoneyStr;
	}

	public void setDaiKanMoneyStr(String daiKanMoneyStr) {
		this.daiKanMoneyStr = daiKanMoneyStr;
	}

	public Double getFenXiaoMoney() {
		return fenXiaoMoney;
	}

	public void setFenXiaoMoney(Double fenXiaoMoney) {
		this.fenXiaoMoney = fenXiaoMoney;
	}

	public String getFenXiaoMoneyStr() {
		return fenXiaoMoneyStr;
	}

	public void setFenXiaoMoneyStr(String fenXiaoMoneyStr) {
		this.fenXiaoMoneyStr = fenXiaoMoneyStr;
	}

	public String getCustormerProtectDays() {
		return custormerProtectDays;
	}

	public void setCustormerProtectDays(String custormerProtectDays) {
		this.custormerProtectDays = custormerProtectDays;
	}
}
