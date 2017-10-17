package com.sc.tradmaster.service.agent.impl.visitDTO;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.VisitRecords;

public class AgentMoreCustomerInputDTO {
	//记录号,自动增长
		private Integer visitNo;
		//到访时间
		private String arriveTime;
		//到访人数
		private Integer customerCount;
		//到访批次标识
		private Integer batchVisitNo;
		//接访方式(指定、顺序)
		private String receptType;
		//案场客户id
		private String projectCusId;
		//客户姓名
		private String cusName;
		//客户电话
		private String cusPhone;
		
		//封装到访信息
		public AgentMoreCustomerInputDTO createVisitObj(VisitRecords v,ProjectCustomers pc){
			this.visitNo = v.getVisitNo();
			this.arriveTime = v.getArriveTime();
			this.customerCount = v.getCustomerCount();
			this.batchVisitNo = v.getBatchVisitNo();
			if(v.getAppointUserId()!=null && !v.getAppointUserId().equals("")){
				this.receptType = "指定";
			}else{
				this.receptType = "顺序";
			}
			return this;
		}
		
		public AgentMoreCustomerInputDTO createBatchVisitObj(VisitRecords v){
			this.visitNo = v.getVisitNo();
			if(v.getCustomerId()!=null && !v.getCustomerId().equals("")){
				this.projectCusId = v.getCustomerId();
			}
			if(v.getCustomerName()!=null && !v.getCustomerName().equals("")){
				this.cusName = v.getCustomerName();
			}else{
				this.cusName = "未知";
			}
			this.cusPhone = v.getPhone();
			return this;
		}
		
		
		public Integer getVisitNo() {
			return visitNo;
		}
		public void setVisitNo(Integer visitNo) {
			this.visitNo = visitNo;
		}
		public String getArriveTime() {
			return arriveTime;
		}
		public void setArriveTime(String arriveTime) {
			this.arriveTime = arriveTime;
		}
		public Integer getCustomerCount() {
			return customerCount;
		}
		public void setCustomerCount(Integer customerCount) {
			this.customerCount = customerCount;
		}
		public Integer getBatchVisitNo() {
			return batchVisitNo;
		}
		public void setBatchVisitNo(Integer batchVisitNo) {
			this.batchVisitNo = batchVisitNo;
		}
		public String getReceptType() {
			return receptType;
		}
		public void setReceptType(String receptType) {
			this.receptType = receptType;
		}
}
