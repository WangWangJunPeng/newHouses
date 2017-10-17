package com.sc.tradmaster.service.director.impl.dto;
/**
 * 成交数据
 * @author cdh  2017-06-13
 *
 */
public class DealData {
	//成交
	private long dealCount;
	//认购
	private long subscribedCount;
	//累计认购
	private long totalSubscribedCount;
	//签约
	private long signCount;
	//累计签约
	private long totalSignCount;
	//认购锁定房源货值
	private long subscribedHouseMoney;
	//累计认购锁定房源货值
	private long totalSubscribedHouseMoney;
	//认购到款定金
	private long subscribedDepositMoney;
	//累计认购到款定金
	private long TotalsubscribedDepositMoney;
	//总客户认购率
	private Integer customerSubscribed;
	//平均总客户认购率
	private Integer totalcustomerSubscribed;
	//老客户认购率
	private Integer oldCustomerSubscribed;
	//平均老客户认购率
	private Integer totalOldCustomerSubscribed;
	//已签约房源货值
	private long signHouseMoney;
	//累计已签约房源货值
	private long totalSignHouseMoney;
	//待签约房源房源货值
	private long waitForsignHouseMoney;
	//累计待签约房源房源货值
	private long totalWaitForsignHouseMoney;
	//来访成交比
	private Integer visitProportion;
	//平均来访成交比
	private Integer totalVisitProportion;
	//认购签约率
	private Integer subscribedAndSignProportion;
	//平均接待时长
	private Integer averageReceptionTime;
	//累计平均接待时长
	private Integer totalAverageReceptionTime;
	public long getDealCount() {
		return dealCount;
	}
	public void setDealCount(long dealCount) {
		this.dealCount = dealCount;
	}
	public long getSubscribedCount() {
		return subscribedCount;
	}
	public void setSubscribedCount(long subscribedCount) {
		this.subscribedCount = subscribedCount;
	}
	public long getTotalSubscribedCount() {
		return totalSubscribedCount;
	}
	public void setTotalSubscribedCount(long totalSubscribedCount) {
		this.totalSubscribedCount = totalSubscribedCount;
	}
	public long getSignCount() {
		return signCount;
	}
	public void setSignCount(long signCount) {
		this.signCount = signCount;
	}
	public long getTotalSignCount() {
		return totalSignCount;
	}
	public void setTotalSignCount(long totalSignCount) {
		this.totalSignCount = totalSignCount;
	}
	public long getSubscribedHouseMoney() {
		return subscribedHouseMoney;
	}
	public void setSubscribedHouseMoney(long subscribedHouseMoney) {
		this.subscribedHouseMoney = subscribedHouseMoney;
	}
	public long getTotalSubscribedHouseMoney() {
		return totalSubscribedHouseMoney;
	}
	public void setTotalSubscribedHouseMoney(long totalSubscribedHouseMoney) {
		this.totalSubscribedHouseMoney = totalSubscribedHouseMoney;
	}
	public long getSubscribedDepositMoney() {
		return subscribedDepositMoney;
	}
	public void setSubscribedDepositMoney(long subscribedDepositMoney) {
		this.subscribedDepositMoney = subscribedDepositMoney;
	}
	public long getTotalsubscribedDepositMoney() {
		return TotalsubscribedDepositMoney;
	}
	public void setTotalsubscribedDepositMoney(long totalsubscribedDepositMoney) {
		TotalsubscribedDepositMoney = totalsubscribedDepositMoney;
	}
	public Integer getCustomerSubscribed() {
		return customerSubscribed;
	}
	public void setCustomerSubscribed(Integer customerSubscribed) {
		this.customerSubscribed = customerSubscribed;
	}
	public Integer getTotalcustomerSubscribed() {
		return totalcustomerSubscribed;
	}
	public void setTotalcustomerSubscribed(Integer totalcustomerSubscribed) {
		this.totalcustomerSubscribed = totalcustomerSubscribed;
	}
	public Integer getOldCustomerSubscribed() {
		return oldCustomerSubscribed;
	}
	public void setOldCustomerSubscribed(Integer oldCustomerSubscribed) {
		this.oldCustomerSubscribed = oldCustomerSubscribed;
	}
	public Integer getTotalOldCustomerSubscribed() {
		return totalOldCustomerSubscribed;
	}
	public void setTotalOldCustomerSubscribed(Integer totalOldCustomerSubscribed) {
		this.totalOldCustomerSubscribed = totalOldCustomerSubscribed;
	}
	public long getSignHouseMoney() {
		return signHouseMoney;
	}
	public void setSignHouseMoney(long signHouseMoney) {
		this.signHouseMoney = signHouseMoney;
	}
	public long getTotalSignHouseMoney() {
		return totalSignHouseMoney;
	}
	public void setTotalSignHouseMoney(long totalSignHouseMoney) {
		this.totalSignHouseMoney = totalSignHouseMoney;
	}
	public long getWaitForsignHouseMoney() {
		return waitForsignHouseMoney;
	}
	public void setWaitForsignHouseMoney(long waitForsignHouseMoney) {
		this.waitForsignHouseMoney = waitForsignHouseMoney;
	}
	public long getTotalWaitForsignHouseMoney() {
		return totalWaitForsignHouseMoney;
	}
	public void setTotalWaitForsignHouseMoney(long totalWaitForsignHouseMoney) {
		this.totalWaitForsignHouseMoney = totalWaitForsignHouseMoney;
	}
	public Integer getVisitProportion() {
		return visitProportion;
	}
	public void setVisitProportion(Integer visitProportion) {
		this.visitProportion = visitProportion;
	}
	public Integer getTotalVisitProportion() {
		return totalVisitProportion;
	}
	public void setTotalVisitProportion(Integer totalVisitProportion) {
		this.totalVisitProportion = totalVisitProportion;
	}
	public Integer getSubscribedAndSignProportion() {
		return subscribedAndSignProportion;
	}
	public void setSubscribedAndSignProportion(Integer subscribedAndSignProportion) {
		this.subscribedAndSignProportion = subscribedAndSignProportion;
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
		return "DealData [dealCount=" + dealCount + ", subscribedCount=" + subscribedCount + ", totalSubscribedCount="
				+ totalSubscribedCount + ", signCount=" + signCount + ", totalSignCount=" + totalSignCount
				+ ", subscribedHouseMoney=" + subscribedHouseMoney + ", totalSubscribedHouseMoney="
				+ totalSubscribedHouseMoney + ", subscribedDepositMoney=" + subscribedDepositMoney
				+ ", TotalsubscribedDepositMoney=" + TotalsubscribedDepositMoney + ", customerSubscribed="
				+ customerSubscribed + ", totalcustomerSubscribed=" + totalcustomerSubscribed
				+ ", oldCustomerSubscribed=" + oldCustomerSubscribed + ", totalOldCustomerSubscribed="
				+ totalOldCustomerSubscribed + ", signHouseMoney=" + signHouseMoney + ", totalSignHouseMoney="
				+ totalSignHouseMoney + ", waitForsignHouseMoney=" + waitForsignHouseMoney
				+ ", totalWaitForsignHouseMoney=" + totalWaitForsignHouseMoney + ", visitProportion=" + visitProportion
				+ ", totalVisitProportion=" + totalVisitProportion + ", subscribedAndSignProportion="
				+ subscribedAndSignProportion + ", averageReceptionTime=" + averageReceptionTime
				+ ", totalAverageReceptionTime=" + totalAverageReceptionTime + "]";
	}
	
		
	
	
}
