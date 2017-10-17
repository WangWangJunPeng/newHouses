package com.sc.tradmaster.service.director.impl.dto;
/**
 * 
 * 总接访量
 * @author cdh - 2017-06-13
 *
 */
public class TotalVisitData {

	//有效接访
	private long effectiveCount;
	//无效接访
	private long invalidCount;
	//接访总量
	private long receptionCount;
	//累计接访总量（所有）
	private long totalReceptionCount;
	//新客户通道
	private long newCustomerPassageWay;
	//新客户通道累计（所有）
	private long totalCustomerPassageWay;
	//新客户通道流失率
	private Integer newCustomerPassageWayLose;
	//新客户通道流失率平均（所有）
	private Integer totalNewCustomerPassageWayLose;
	//老客户通道
	private long oldCustomerPassageWay;
	//老客户通道累计（所有）
	private long totalOldCustomerPassageWay;
	//老客户通道流失率
	private Integer oldCustomerPassageWayLose;
	//老客户通道流失率平均（所有）
	private Integer totalOldCustomerPassageWayLose;
	//指定接访
	private long appointVisit;
	//指定接访累计（所有）
	private long totalAppointVisit;
	//指定接访流失率
	private Integer appointVisitLose;
	//平均指定接访流失率（所有）
	private Integer totalAppointVisitLose;
	//替接
	private long replaceVisit;
	//累计替接（所有）
	private long totalReplaceVisit;
	//平台导客
	private long guidanceCustomer;
	//累计平台导客(所有)
	private long totalGuidanceCustomer;
	//平均接待时长
	private Integer averageReceptionTime;
	//所有平均接待时长（所有）
	private Integer totalAverageReceptionTime;
	
	
	
	
	public long getEffectiveCount() {
		return effectiveCount;
	}
	public void setEffectiveCount(long effectiveCount) {
		this.effectiveCount = effectiveCount;
	}
	public long getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(long invalidCount) {
		this.invalidCount = invalidCount;
	}
	public long getReceptionCount() {
		return receptionCount;
	}
	public void setReceptionCount(long receptionCount) {
		this.receptionCount = receptionCount;
	}
	public long getTotalReceptionCount() {
		return totalReceptionCount;
	}
	public void setTotalReceptionCount(long totalReceptionCount) {
		this.totalReceptionCount = totalReceptionCount;
	}
	public long getNewCustomerPassageWay() {
		return newCustomerPassageWay;
	}
	public void setNewCustomerPassageWay(long newCustomerPassageWay) {
		this.newCustomerPassageWay = newCustomerPassageWay;
	}
	public long getTotalCustomerPassageWay() {
		return totalCustomerPassageWay;
	}
	public void setTotalCustomerPassageWay(long totalCustomerPassageWay) {
		this.totalCustomerPassageWay = totalCustomerPassageWay;
	}
	public Integer getNewCustomerPassageWayLose() {
		return newCustomerPassageWayLose;
	}
	public void setNewCustomerPassageWayLose(Integer newCustomerPassageWayLose) {
		this.newCustomerPassageWayLose = newCustomerPassageWayLose;
	}
	public Integer getTotalNewCustomerPassageWayLose() {
		return totalNewCustomerPassageWayLose;
	}
	public void setTotalNewCustomerPassageWayLose(Integer totalNewCustomerPassageWayLose) {
		this.totalNewCustomerPassageWayLose = totalNewCustomerPassageWayLose;
	}
	public long getOldCustomerPassageWay() {
		return oldCustomerPassageWay;
	}
	public void setOldCustomerPassageWay(long oldCustomerPassageWay) {
		this.oldCustomerPassageWay = oldCustomerPassageWay;
	}
	public long getTotalOldCustomerPassageWay() {
		return totalOldCustomerPassageWay;
	}
	public void setTotalOldCustomerPassageWay(long totalOldCustomerPassageWay) {
		this.totalOldCustomerPassageWay = totalOldCustomerPassageWay;
	}
	public Integer getOldCustomerPassageWayLose() {
		return oldCustomerPassageWayLose;
	}
	public void setOldCustomerPassageWayLose(Integer oldCustomerPassageWayLose) {
		this.oldCustomerPassageWayLose = oldCustomerPassageWayLose;
	}
	public Integer getTotalOldCustomerPassageWayLose() {
		return totalOldCustomerPassageWayLose;
	}
	public void setTotalOldCustomerPassageWayLose(Integer totalOldCustomerPassageWayLose) {
		this.totalOldCustomerPassageWayLose = totalOldCustomerPassageWayLose;
	}
	public long getAppointVisit() {
		return appointVisit;
	}
	public void setAppointVisit(long appointVisit) {
		this.appointVisit = appointVisit;
	}
	public long getTotalAppointVisit() {
		return totalAppointVisit;
	}
	public void setTotalAppointVisit(long totalAppointVisit) {
		this.totalAppointVisit = totalAppointVisit;
	}
	public Integer getAppointVisitLose() {
		return appointVisitLose;
	}
	public void setAppointVisitLose(Integer appointVisitLose) {
		this.appointVisitLose = appointVisitLose;
	}
	public Integer getTotalAppointVisitLose() {
		return totalAppointVisitLose;
	}
	public void setTotalAppointVisitLose(Integer totalAppointVisitLose) {
		this.totalAppointVisitLose = totalAppointVisitLose;
	}
	public long getReplaceVisit() {
		return replaceVisit;
	}
	public void setReplaceVisit(long replaceVisit) {
		this.replaceVisit = replaceVisit;
	}
	public long getTotalReplaceVisit() {
		return totalReplaceVisit;
	}
	public void setTotalReplaceVisit(long totalReplaceVisit) {
		this.totalReplaceVisit = totalReplaceVisit;
	}
	public long getGuidanceCustomer() {
		return guidanceCustomer;
	}
	public void setGuidanceCustomer(long guidanceCustomer) {
		this.guidanceCustomer = guidanceCustomer;
	}
	public long getTotalGuidanceCustomer() {
		return totalGuidanceCustomer;
	}
	public void setTotalGuidanceCustomer(long totalGuidanceCustomer) {
		this.totalGuidanceCustomer = totalGuidanceCustomer;
	}
	public Integer getAverageReceptionTime() {
		return averageReceptionTime;
	}
	public void setAverageReceptionTime(Integer averageReceptionTime) {
		this.averageReceptionTime = averageReceptionTime;
	}
	public Integer getTotalAverageReceptionTime() {
		return totalAverageReceptionTime;
	}
	public void setTotalAverageReceptionTime(Integer totalAverageReceptionTime) {
		this.totalAverageReceptionTime = totalAverageReceptionTime;
	}
	@Override
	public String toString() {
		return "TotalVisitData [effectiveCount=" + effectiveCount + ", invalidCount=" + invalidCount
				+ ", receptionCount=" + receptionCount + ", totalReceptionCount=" + totalReceptionCount
				+ ", newCustomerPassageWay=" + newCustomerPassageWay + ", totalCustomerPassageWay="
				+ totalCustomerPassageWay + ", newCustomerPassageWayLose=" + newCustomerPassageWayLose
				+ ", totalNewCustomerPassageWayLose=" + totalNewCustomerPassageWayLose + ", oldCustomerPassageWay="
				+ oldCustomerPassageWay + ", totalOldCustomerPassageWay=" + totalOldCustomerPassageWay
				+ ", oldCustomerPassageWayLose=" + oldCustomerPassageWayLose + ", totalOldCustomerPassageWayLose="
				+ totalOldCustomerPassageWayLose + ", appointVisit=" + appointVisit + ", totalAppointVisit="
				+ totalAppointVisit + ", appointVisitLose=" + appointVisitLose + ", totalAppointVisitLose="
				+ totalAppointVisitLose + ", replaceVisit=" + replaceVisit + ", totalReplaceVisit=" + totalReplaceVisit
				+ ", guidanceCustomer=" + guidanceCustomer + ", totalGuidanceCustomer=" + totalGuidanceCustomer
				+ ", averageReceptionTime=" + averageReceptionTime + ", totalAverageReceptionTime="
				+ totalAverageReceptionTime + "]";
	}
	
	
}
