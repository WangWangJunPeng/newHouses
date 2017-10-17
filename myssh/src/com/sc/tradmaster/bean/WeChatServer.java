package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信服务
 * @author grl
 *
 */
@Entity
@Table(name="t_wechatserver")
public class WeChatServer {
	//服务Id，唯一标识
	private Integer serverId;
	//微信帐号
	private String weChatNum;
	//公司名称
	private String companyName;
	//门店名称
	private String shopName;
	//省
	private String province;
	//市
	private String town;
	//地址
	private String shopAddress;
	//申请人职位
	private Integer roleId;
	//联系人
	private String linkMan;
	//联系方式
	private String linkManPhone;
	//验证码
	private String code;
	//已阅读并同意协议
	private String isReadSheet;
	
	
	@Id
	@GeneratedValue
	public Integer getServerId() {
		return serverId;
	}
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public String getWeChatNum() {
		return weChatNum;
	}
	public void setWeChatNum(String weChatNum) {
		this.weChatNum = weChatNum;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkManPhone() {
		return linkManPhone;
	}
	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}
	public String getIsReadSheet() {
		return isReadSheet;
	}
	public void setIsReadSheet(String isReadSheet) {
		this.isReadSheet = isReadSheet;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
