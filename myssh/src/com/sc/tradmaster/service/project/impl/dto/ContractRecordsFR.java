package com.sc.tradmaster.service.project.impl.dto;

import com.sc.tradmaster.bean.ContractRecords;

public class ContractRecordsFR {
	// 记录号,自动增长
	private Integer recordNo;
	// 申请人用户编码
	private String userId;
	// 案场编码
	private String projectId;
	// 房源编码,唯一性
	private Integer houseNum;
	// 客户姓名
	private String customerName;
	// 客户身份证号码
	private String customerIDCard;
	// 客户电话
	private String customerPhone;
	// 优惠条款列表(集合),按顺序计算
	private String benefitlds;
	// 成交价格
	private Double price;
	// 状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
	private Integer recordStatus;
	// 申请时间
	private String applyTime;
	// 审核时间
	private String auditingTime;
	// 审核用户编码
	private String auditionUserId;
	// 审核说明
	private String auditionReson;
	// 汇款单号
	private String remitNo;
	// 订购金到款确认时间
	private String remitConfirmTime;
	// 订购金到款确认用户编码
	private String remitConfirmUserId;
	// 签约确认时间
	private String contractConfirmTime;
	// 签约确认用户编码
	private String contractconfirmUseerId;
	// 是否中介结款(0：未结款；1已结款;2已到款)
	private Integer isShopPayConfirm;
	// 中介结账确认时间
	private String shopPayConfirmTime;
	// 中介结账确认用户编码
	private String shopPayConfirmUserId;
	// 中介结款说明
	private String shopPayConfirmDesc;
	// 取消中介结款说明
	private String cancelShopPayConfirmDesc;
	// 是否平台结账(0：未结款；1已结款；2已到款)
	private Integer isSystemPayConfirm;
	// 平台结账确认时间
	private String systemPayConfirmTime;
	// 平台结账确认用户编码
	private String systemPayConfirmUserId;
	// 平台结款说明
	private String systemPayConfirmDesc;
	// 取消平台结款说明
	private String cancelSystemPayConfirmDesc;
	// 中介到款确认时间
	private String shopReceiveConfirmTime;
	// 中介到款确认用户编码
	private String shopReceiveConfirmUserId;
	// 中介取消到款说明
	private String shopCancelReceiveConfirmDesc;
	// 平台到款确认时间
	private String systemReceiveConfirmTime;
	// 平台到款确认用户编码
	private String systemReceiveConfirmUserId;
	// 预留的标签字段
	private String tags;
	// 预留的说明字段
	private String description;
	// 凭证类型,0:网银汇款,1:现场交款,2:支付宝
	private Integer credentialsType;
	private String credentialsNum;
	// 凭证照片url
	private String credentialsPhoto;
	// 购买价格
	private Double buyPrice;
	// 凭证上传时间
	private String voucherUploadTime;
	// 到款审核说明
	private String getMoneyDesc;
	// 案场客户id
	private String projectCustomerId;
	// 是否打款超期
	private String isOut;
	// 支付方式 0线上 1线下
	private Integer payStyle;
	// 订单性质(0:自购,1:代购)
	private Integer orderProperty;
	// 是否阅读全部条款(0:未读,1:已读)
	private Integer isAlreadyRead;
	// 认购关系0,1,2.....
	private Integer enterBuyRelation;
	// 结算方式 5一次性1公积金2商贷按揭3公积金+商贷4其他
	private Integer accountStyle;
	// 撤单原因(单选框选择..........)
	private String killTheOrderReason;
	// 撤单备注
	private String revokeOrderNotes;
	// 撤单时间
	private String revokeTime;
	// 优惠条款信息
	private String benefitInfo;
	// 订单号
	private String orderNum;
	// 认购认购人id
	private String shopCustomerId;
	// 真实认购人id信息
	private String realCustomerId;
	// 定金
	private String haveToPayMoney;
	// 带看主键编号
	private String guideId;

	public ContractRecordsFR creatNewCrDTO(ContractRecords cr){
		if (cr != null) {
			this.userId = cr.getUserId();
			if(cr.getAccountStyle()!=null && !cr.getAccountStyle().equals("")){
				this.accountStyle = cr.getAccountStyle();
			}
			if(cr.getApplyTime()!=null && !cr.getApplyTime().equals("")){
				this.applyTime = cr.getApplyTime();
			}
			if(cr.getAuditingTime()!=null && !cr.getAuditingTime().equals("")){
				this.auditingTime = cr.getAuditingTime();
			}
			if(cr.getAuditionReson()!=null && !cr.getAuditionReson().equals("")){
				this.auditionReson = cr.getAuditionReson();
			}
			if(cr.getAuditionUserId()!=null && !cr.getAuditionUserId().equals("")){
				this.auditionUserId = cr.getAuditionUserId();
			}
			if(cr.getBenefitInfo()!=null && !cr.getBenefitInfo().equals("")){
				this.benefitInfo = cr.getBenefitInfo();
			}
			if(cr.getBenefitlds()!=null && !cr.getBenefitlds().equals("")){
				this.benefitlds = cr.getBenefitlds();
			}
			if(cr.getBuyPrice()!=null && !cr.getBuyPrice().equals("")){
				this.buyPrice = cr.getBuyPrice();
			}
			if(cr.getCancelShopPayConfirmDesc()!=null && !cr.getCancelShopPayConfirmDesc().equals("")){
				this.cancelShopPayConfirmDesc = cr.getCancelShopPayConfirmDesc();
			}
			if(cr.getCancelSystemPayConfirmDesc()!=null && !cr.getCancelSystemPayConfirmDesc().equals("")){
				this.cancelSystemPayConfirmDesc = cr.getCancelSystemPayConfirmDesc();
			}
			if(cr.getContractConfirmTime()!=null && !cr.getContractConfirmTime().equals("")){
				this.contractConfirmTime = cr.getContractConfirmTime();
			}
			if(cr.getContractconfirmUseerId()!=null && !cr.getContractconfirmUseerId().equals("")){
				this.contractconfirmUseerId = cr.getContractconfirmUseerId();
			}
			if(cr.getCredentialsNum()!=null && !cr.getCredentialsNum().equals("")){
				this.credentialsNum = cr.getCredentialsNum();
			}
			if(cr.getCredentialsPhoto()!=null && !cr.getCredentialsPhoto().equals("") ){
				this.credentialsPhoto = cr.getCredentialsPhoto();
			}
			if(cr.getCredentialsType()!=null && !cr.getCredentialsType().equals("")){
				this.credentialsType = cr.getCredentialsType();
			}
			if(cr.getCustomerIDCard()!=null && !cr.getCustomerIDCard().equals("")){
				this.customerIDCard = cr.getCustomerIDCard();
			}
			if(cr.getCustomerName()!=null && !cr.getCustomerName().equals("")){
				this.customerName = cr.getCustomerName();
			}
			if(cr.getCustomerPhone()!=null && !cr.getCustomerPhone().equals("")){
				this.customerPhone = cr.getCustomerPhone();
			}
			if(cr.getDescription()!=null && !cr.getDescription().equals("")){
				this.description = cr.getDescription();
			}
			if(cr.getEnterBuyRelation()!=null && !cr.getEnterBuyRelation().equals("")){
				this.enterBuyRelation = cr.getEnterBuyRelation();
			}
			if(cr.getGetMoneyDesc()!=null && !cr.getGetMoneyDesc().equals("")){
				this.getMoneyDesc = cr.getGetMoneyDesc();
			}
			if(cr.getGuideId()!=null && !cr.getGuideId().equals("")){
				this.guideId = cr.getGuideId();
			}
			if(cr.getHaveToPayMoney()!=null && !cr.getHaveToPayMoney().equals("")){
				this.haveToPayMoney = cr.getHaveToPayMoney();
			}
			if(cr.getHouseNum()!=null && !cr.getHouseNum().equals("")){
				this.houseNum = cr.getHouseNum();
			}
			if(cr.getIsAlreadyRead()!=null && !cr.getIsAlreadyRead().equals("")){
				this.isAlreadyRead = cr.getIsAlreadyRead();
			}
			if(cr.getIsOut()!=null && !cr.getIsOut().equals("")){
				this.isOut = cr.getIsOut();
			}
			if(cr.getIsShopPayConfirm()!=null && !cr.getIsShopPayConfirm().equals("")){
				this.isShopPayConfirm = cr.getIsShopPayConfirm();
			}
			if(cr.getIsSystemPayConfirm()!=null && !cr.getIsSystemPayConfirm().equals("")){
				this.isSystemPayConfirm = cr.getIsSystemPayConfirm();
			}
			if(cr.getKillTheOrderReason()!=null && !cr.getKillTheOrderReason().equals("")){
				this.killTheOrderReason = cr.getKillTheOrderReason();
			}
			if(cr.getOrderNum()!=null && !cr.getOrderNum().equals("")){
				this.orderNum = cr.getOrderNum();
			}
			if(cr.getOrderProperty()!=null && !cr.getOrderProperty().equals("")){
				this.orderProperty = cr.getOrderProperty();
			}
			if(cr.getPayStyle()!=null && !cr.getPayStyle().equals("")){
				this.payStyle = cr.getPayStyle();
			}
			if(cr.getPrice()!=null && !cr.getPrice().equals("")){
				this.price = cr.getPrice();
			}
			if(cr.getProjectCustomerId()!=null && !cr.getProjectCustomerId().equals("")){
				this.projectCustomerId = cr.getProjectCustomerId();
			}
			if(cr.getProjectId()!=null && !cr.getProjectId().equals("")){
				this.projectId = cr.getProjectId();
			}
			if(cr.getRealCustomerId()!=null && !cr.getRealCustomerId().equals("")){
				this.realCustomerId = cr.getRealCustomerId();
			}
			if(cr.getRecordNo()!=null && !cr.getRecordNo().equals("")){
				this.recordNo = cr.getRecordNo();
			}
			if(cr.getRecordStatus()!=null && !cr.getRecordStatus().equals("")){
				this.recordStatus = cr.getRecordStatus();
			}
			if(cr.getRemitConfirmTime()!=null && !cr.getRemitConfirmTime().equals("")){
				this.remitConfirmTime = cr.getRemitConfirmTime();
			}
			if(cr.getRemitConfirmUserId()!=null && !cr.getRemitConfirmUserId().equals("")){
				this.remitConfirmUserId = cr.getRemitConfirmUserId();
			}
			if(cr.getRemitNo()!=null && !cr.getRemitNo().equals("")){
				this.remitNo = cr.getRemitNo();
			}
			if(cr.getRevokeOrderNotes()!=null && !cr.getRevokeOrderNotes().equals("")){
				this.revokeOrderNotes = cr.getRevokeOrderNotes();
			}
			if(cr.getRevokeTime()!=null && !cr.getRevokeTime().equals("")){
				this.revokeTime = cr.getRevokeTime();
			}
			if(cr.getShopCancelReceiveConfirmDesc()!=null && !cr.getShopCancelReceiveConfirmDesc().equals("")){
				this.shopCancelReceiveConfirmDesc = cr.getShopCancelReceiveConfirmDesc();
			}
			if(cr.getShopCustomerId()!=null && !cr.getShopCustomerId().equals("")){
				this.shopCustomerId = cr.getShopCustomerId();
			}
			if(cr.getShopPayConfirmDesc()!=null && !cr.getShopPayConfirmDesc().equals("")){
				this.shopPayConfirmDesc = cr.getShopPayConfirmDesc();
			}
			if(cr.getShopPayConfirmTime()!=null && !cr.getShopPayConfirmTime().equals("")){
				this.shopPayConfirmTime = cr.getShopPayConfirmTime();
			}
			if(cr.getShopPayConfirmUserId()!=null && !cr.getShopPayConfirmUserId().equals("")){
				this.shopPayConfirmUserId = cr.getShopPayConfirmUserId();
			}
			if(cr.getShopReceiveConfirmTime()!=null && !cr.getShopReceiveConfirmTime().equals("")){
				this.shopReceiveConfirmTime = cr.getShopReceiveConfirmTime();
			}
			if(cr.getShopReceiveConfirmUserId()!=null && !cr.getShopReceiveConfirmUserId().equals("")){
				this.shopReceiveConfirmUserId = cr.getShopReceiveConfirmUserId();
			}
			if(cr.getSystemPayConfirmDesc()!=null && !cr.getSystemPayConfirmDesc().equals("")){
				this.systemPayConfirmDesc = cr.getSystemPayConfirmDesc();
			}
			if(cr.getSystemPayConfirmTime()!=null && !cr.getSystemPayConfirmTime().equals("")){
				this.systemPayConfirmTime = cr.getSystemPayConfirmTime();
			}
			if(cr.getSystemPayConfirmUserId()!=null && !cr.getSystemPayConfirmUserId().equals("")){
				this.systemPayConfirmUserId = cr.getSystemPayConfirmUserId();
			}
			if(cr.getSystemReceiveConfirmTime()!=null && !cr.getSystemReceiveConfirmTime().equals("")){
				this.systemReceiveConfirmTime = cr.getSystemReceiveConfirmTime();
			}
			if(cr.getSystemReceiveConfirmUserId()!=null && !cr.getSystemReceiveConfirmUserId().equals("")){
				this.systemReceiveConfirmUserId = cr.getSystemReceiveConfirmUserId();
			}
			if(cr.getTags()!=null && !cr.getTags().equals("")){
				this.tags = cr.getTags();
			}
			if(cr.getVoucherUploadTime()!=null && !cr.getVoucherUploadTime().equals("")){
				this.voucherUploadTime = cr.getVoucherUploadTime();
			}
		}
		return this;
	}

	public Integer getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerIDCard() {
		return customerIDCard;
	}

	public void setCustomerIDCard(String customerIDCard) {
		this.customerIDCard = customerIDCard;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getBenefitlds() {
		return benefitlds;
	}

	public void setBenefitlds(String benefitlds) {
		this.benefitlds = benefitlds;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getAuditingTime() {
		return auditingTime;
	}

	public void setAuditingTime(String auditingTime) {
		this.auditingTime = auditingTime;
	}

	public String getAuditionUserId() {
		return auditionUserId;
	}

	public void setAuditionUserId(String auditionUserId) {
		this.auditionUserId = auditionUserId;
	}

	public String getAuditionReson() {
		return auditionReson;
	}

	public void setAuditionReson(String auditionReson) {
		this.auditionReson = auditionReson;
	}

	public String getRemitNo() {
		return remitNo;
	}

	public void setRemitNo(String remitNo) {
		this.remitNo = remitNo;
	}

	public String getRemitConfirmTime() {
		return remitConfirmTime;
	}

	public void setRemitConfirmTime(String remitConfirmTime) {
		this.remitConfirmTime = remitConfirmTime;
	}

	public String getRemitConfirmUserId() {
		return remitConfirmUserId;
	}

	public void setRemitConfirmUserId(String remitConfirmUserId) {
		this.remitConfirmUserId = remitConfirmUserId;
	}

	public String getContractConfirmTime() {
		return contractConfirmTime;
	}

	public void setContractConfirmTime(String contractConfirmTime) {
		this.contractConfirmTime = contractConfirmTime;
	}

	public String getContractconfirmUseerId() {
		return contractconfirmUseerId;
	}

	public void setContractconfirmUseerId(String contractconfirmUseerId) {
		this.contractconfirmUseerId = contractconfirmUseerId;
	}

	public Integer getIsShopPayConfirm() {
		return isShopPayConfirm;
	}

	public void setIsShopPayConfirm(Integer isShopPayConfirm) {
		this.isShopPayConfirm = isShopPayConfirm;
	}

	public String getShopPayConfirmTime() {
		return shopPayConfirmTime;
	}

	public void setShopPayConfirmTime(String shopPayConfirmTime) {
		this.shopPayConfirmTime = shopPayConfirmTime;
	}

	public String getShopPayConfirmUserId() {
		return shopPayConfirmUserId;
	}

	public void setShopPayConfirmUserId(String shopPayConfirmUserId) {
		this.shopPayConfirmUserId = shopPayConfirmUserId;
	}

	public String getShopPayConfirmDesc() {
		return shopPayConfirmDesc;
	}

	public void setShopPayConfirmDesc(String shopPayConfirmDesc) {
		this.shopPayConfirmDesc = shopPayConfirmDesc;
	}

	public String getCancelShopPayConfirmDesc() {
		return cancelShopPayConfirmDesc;
	}

	public void setCancelShopPayConfirmDesc(String cancelShopPayConfirmDesc) {
		this.cancelShopPayConfirmDesc = cancelShopPayConfirmDesc;
	}

	public Integer getIsSystemPayConfirm() {
		return isSystemPayConfirm;
	}

	public void setIsSystemPayConfirm(Integer isSystemPayConfirm) {
		this.isSystemPayConfirm = isSystemPayConfirm;
	}

	public String getSystemPayConfirmTime() {
		return systemPayConfirmTime;
	}

	public void setSystemPayConfirmTime(String systemPayConfirmTime) {
		this.systemPayConfirmTime = systemPayConfirmTime;
	}

	public String getSystemPayConfirmUserId() {
		return systemPayConfirmUserId;
	}

	public void setSystemPayConfirmUserId(String systemPayConfirmUserId) {
		this.systemPayConfirmUserId = systemPayConfirmUserId;
	}

	public String getSystemPayConfirmDesc() {
		return systemPayConfirmDesc;
	}

	public void setSystemPayConfirmDesc(String systemPayConfirmDesc) {
		this.systemPayConfirmDesc = systemPayConfirmDesc;
	}

	public String getCancelSystemPayConfirmDesc() {
		return cancelSystemPayConfirmDesc;
	}

	public void setCancelSystemPayConfirmDesc(String cancelSystemPayConfirmDesc) {
		this.cancelSystemPayConfirmDesc = cancelSystemPayConfirmDesc;
	}

	public String getShopReceiveConfirmTime() {
		return shopReceiveConfirmTime;
	}

	public void setShopReceiveConfirmTime(String shopReceiveConfirmTime) {
		this.shopReceiveConfirmTime = shopReceiveConfirmTime;
	}

	public String getShopReceiveConfirmUserId() {
		return shopReceiveConfirmUserId;
	}

	public void setShopReceiveConfirmUserId(String shopReceiveConfirmUserId) {
		this.shopReceiveConfirmUserId = shopReceiveConfirmUserId;
	}

	public String getShopCancelReceiveConfirmDesc() {
		return shopCancelReceiveConfirmDesc;
	}

	public void setShopCancelReceiveConfirmDesc(String shopCancelReceiveConfirmDesc) {
		this.shopCancelReceiveConfirmDesc = shopCancelReceiveConfirmDesc;
	}

	public String getSystemReceiveConfirmTime() {
		return systemReceiveConfirmTime;
	}

	public void setSystemReceiveConfirmTime(String systemReceiveConfirmTime) {
		this.systemReceiveConfirmTime = systemReceiveConfirmTime;
	}

	public String getSystemReceiveConfirmUserId() {
		return systemReceiveConfirmUserId;
	}

	public void setSystemReceiveConfirmUserId(String systemReceiveConfirmUserId) {
		this.systemReceiveConfirmUserId = systemReceiveConfirmUserId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(Integer credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getCredentialsNum() {
		return credentialsNum;
	}

	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum;
	}

	public String getCredentialsPhoto() {
		return credentialsPhoto;
	}

	public void setCredentialsPhoto(String credentialsPhoto) {
		this.credentialsPhoto = credentialsPhoto;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getVoucherUploadTime() {
		return voucherUploadTime;
	}

	public void setVoucherUploadTime(String voucherUploadTime) {
		this.voucherUploadTime = voucherUploadTime;
	}

	public String getGetMoneyDesc() {
		return getMoneyDesc;
	}

	public void setGetMoneyDesc(String getMoneyDesc) {
		this.getMoneyDesc = getMoneyDesc;
	}

	public String getProjectCustomerId() {
		return projectCustomerId;
	}

	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}

	public String getIsOut() {
		return isOut;
	}

	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}

	public Integer getPayStyle() {
		return payStyle;
	}

	public void setPayStyle(Integer payStyle) {
		this.payStyle = payStyle;
	}

	public Integer getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(Integer orderProperty) {
		this.orderProperty = orderProperty;
	}

	public Integer getIsAlreadyRead() {
		return isAlreadyRead;
	}

	public void setIsAlreadyRead(Integer isAlreadyRead) {
		this.isAlreadyRead = isAlreadyRead;
	}

	public Integer getEnterBuyRelation() {
		return enterBuyRelation;
	}

	public void setEnterBuyRelation(Integer enterBuyRelation) {
		this.enterBuyRelation = enterBuyRelation;
	}

	public Integer getAccountStyle() {
		return accountStyle;
	}

	public void setAccountStyle(Integer accountStyle) {
		this.accountStyle = accountStyle;
	}

	public String getKillTheOrderReason() {
		return killTheOrderReason;
	}

	public void setKillTheOrderReason(String killTheOrderReason) {
		this.killTheOrderReason = killTheOrderReason;
	}

	public String getRevokeOrderNotes() {
		return revokeOrderNotes;
	}

	public void setRevokeOrderNotes(String revokeOrderNotes) {
		this.revokeOrderNotes = revokeOrderNotes;
	}

	public String getRevokeTime() {
		return revokeTime;
	}

	public void setRevokeTime(String revokeTime) {
		this.revokeTime = revokeTime;
	}

	public String getBenefitInfo() {
		return benefitInfo;
	}

	public void setBenefitInfo(String benefitInfo) {
		this.benefitInfo = benefitInfo;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getShopCustomerId() {
		return shopCustomerId;
	}

	public void setShopCustomerId(String shopCustomerId) {
		this.shopCustomerId = shopCustomerId;
	}

	public String getRealCustomerId() {
		return realCustomerId;
	}

	public void setRealCustomerId(String realCustomerId) {
		this.realCustomerId = realCustomerId;
	}

	public String getHaveToPayMoney() {
		return haveToPayMoney;
	}

	public void setHaveToPayMoney(String haveToPayMoney) {
		this.haveToPayMoney = haveToPayMoney;
	}

	public String getGuideId() {
		return guideId;
	}

	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	
	
}
