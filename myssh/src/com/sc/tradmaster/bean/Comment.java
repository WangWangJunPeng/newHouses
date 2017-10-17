package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 2017-06-19
 * @author wjp
 *
 */
@Entity
@Table(name="t_comment")
public class Comment {
	//id
	private Integer commentId;
	//时间
	private String commentTime;
	//客户id
	private String customerId;
	//用户id;
	private String userId;
	//客户点评
	private String customerComment;
	//客户描述1-5
	private String customerDescribe;
	//提交速度1-5
	private String commitSpeed;
	//客户意愿
	private String customerAspiration;
	//客户信息
	private String customerInfo;
	//经理判客0:不同意,1:同意
	private String isAgree;
	//预留的说明字段
	private String descprition;
	//预留的标签字段
	private String tags;
	//到访编号
	private Integer visitNo;
	
	@Id
	@GeneratedValue
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCustomerComment() {
		return customerComment;
	}
	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}
	
	public String getCustomerDescribe() {
		return customerDescribe;
	}
	public void setCustomerDescribe(String customerDescribe) {
		this.customerDescribe = customerDescribe;
	}
	public String getCommitSpeed() {
		return commitSpeed;
	}
	public void setCommitSpeed(String commitSpeed) {
		this.commitSpeed = commitSpeed;
	}
	public String getCustomerAspiration() {
		return customerAspiration;
	}
	public void setCustomerAspiration(String customerAspiration) {
		this.customerAspiration = customerAspiration;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public String getIsAgree() {
		return isAgree;
	}
	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}
	public String getDescprition() {
		return descprition;
	}
	public void setDescprition(String descprition) {
		this.descprition = descprition;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public Integer getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentTime=" + commentTime + ", customerId=" + customerId
				+ ", userId=" + userId + ", customerComment=" + customerComment + ", customerDescribe="
				+ customerDescribe + ", commitSpeed=" + commitSpeed + ", customerAspiration=" + customerAspiration
				+ ", customerInfo=" + customerInfo + ", isAgree=" + isAgree + ", descprition=" + descprition + ", tags="
				+ tags + ", visitNo=" + visitNo + "]";
	}
}
