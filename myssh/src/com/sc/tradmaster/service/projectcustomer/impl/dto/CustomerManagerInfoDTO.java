package com.sc.tradmaster.service.projectcustomer.impl.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.utils.DateTime;

/**
 * 该类封装用户详情(案场经理个人中心)
 * @author maoxy
 *
 */
public class CustomerManagerInfoDTO {
	//客户名称
	private String projectCustomerName;
	//客户电话,唯一性
	private String projectCustomerPhone;
	//当前归属的职业顾问用户编码   由案场经理分配
	private String ownerUserId;
	private String ownerUserName;
	//性别,0:未知,1:male,2;famle
	private Integer sex;
	//身份证号码
	private String idCard;
	//职业
	private String job;
	//经理点评
	private String managerEvaluate;
	//客户意向
	private String intention;
	//年龄
	private String age;
	//客户标签
	private Map<String,List<Tag>> ownerTags;
	//客户轨迹
	private List<Track> tracks;
	//来访次数
	private Integer arriveTimes;
	
	
	
	public Integer getArriveTimes() {
		return arriveTimes;
	}
	public void setArriveTimes(Integer arriveTimes) {
		this.arriveTimes = arriveTimes;
	}
	public String getOwnerUserName() {
		return ownerUserName;
	}
	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
	public List<Track> getTracks() {
		return tracks;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	public Map<String, List<Tag>> getOwnerTags() {
		return ownerTags;
	}
	public void setOwnerTags(Map<String, List<Tag>> ownerTags) {
		this.ownerTags = ownerTags;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIntention() {
		return intention;
	}
	public void setIntention(String intention) {
		this.intention = intention;
	}
	public String getProjectCustomerName() {
		return projectCustomerName;
	}
	public void setProjectCustomerName(String projectCustomerName) {
		this.projectCustomerName = projectCustomerName;
	}
	public String getProjectCustomerPhone() {
		return projectCustomerPhone;
	}
	public void setProjectCustomerPhone(String projectCustomerPhone) {
		this.projectCustomerPhone = projectCustomerPhone;
	}
	public String getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
		if("未知".equals(idCard) || idCard.length()!=18){
			this.age = "未知";
		}else{
			String borthYear = idCard.substring(6, 10);
			String string1 = DateTime.toString1(new Date());
			String now = string1.substring(0, 4);
			int parseInt = Integer.parseInt(now);
			int parseInt2 = Integer.parseInt(borthYear);
			this.age = (parseInt-parseInt2)+"";
		}
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getManagerEvaluate() {
		return managerEvaluate;
	}
	public void setManagerEvaluate(String managerEvaluate) {
		this.managerEvaluate = managerEvaluate;
	}
	
	

}
