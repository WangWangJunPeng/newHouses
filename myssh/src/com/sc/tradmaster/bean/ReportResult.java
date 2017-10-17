package com.sc.tradmaster.bean;

/**
 * 周报月报季报对象
 * @author cdh 2017-09-11
 *
 */
public class ReportResult {
	
	//周报名称（Excel标题）
	private String reportName;
	//项目id
	private String projectId;
	//项目名称
	private String projectName;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//接访总数
	private Integer visitCount;
	//有效接访率
	private String validVisitRate;
	//首访有效率
	private String validNewCuVisitRate;
	//老客户接访占比
	private String oldCuVisitRate;
	//新增储客
	private Integer newCuCount;
	// 累计老客户
	private Integer totalOldCuCount;
	// 累计总储客
	private Integer totalCuCount;
	// 本季新增新增认购套数
	private Integer subscribeHouseCount;
	// 认购套数比较上季度的增长/减少
	private String subscribeHouseRate;
	// 本季新增认购金额
	private Long subscribeMoney;
	// 认购金额比较上季度的增长/减少
	private String subscribeMoneyRate;
	// 本季签约套数
	private Integer signCount;
	// 本季签约套数比较上季度增长/减少
	private String signRate;
	// 本季度签约金额
	private Long signHouseMoney;
	// 本季度签约金额比较上季度的增长/减少
	private String signHouseMoneyRate;
	// 本季度新客户接访签约率
	private String newCustomerSignedRate;
	// 储客签约率
	private String momeryCustomerSignedRate;
	// 老客户签约率
	private String oldCustomerSignedRate;
	// 认购客户签约率
	private String contratCuSignedRate;
	
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public String getValidVisitRate() {
		return validVisitRate;
	}
	public void setValidVisitRate(String validVisitRate) {
		this.validVisitRate = validVisitRate;
	}
	public String getValidNewCuVisitRate() {
		return validNewCuVisitRate;
	}
	public void setValidNewCuVisitRate(String validNewCuVisitRate) {
		this.validNewCuVisitRate = validNewCuVisitRate;
	}
	public String getOldCuVisitRate() {
		return oldCuVisitRate;
	}
	public void setOldCuVisitRate(String oldCuVisitRate) {
		this.oldCuVisitRate = oldCuVisitRate;
	}
	public Integer getNewCuCount() {
		return newCuCount;
	}
	public void setNewCuCount(Integer newCuCount) {
		this.newCuCount = newCuCount;
	}
	public Integer getTotalOldCuCount() {
		return totalOldCuCount;
	}
	public void setTotalOldCuCount(Integer totalOldCuCount) {
		this.totalOldCuCount = totalOldCuCount;
	}
	public Integer getTotalCuCount() {
		return totalCuCount;
	}
	public void setTotalCuCount(Integer totalCuCount) {
		this.totalCuCount = totalCuCount;
	}
	public Integer getSubscribeHouseCount() {
		return subscribeHouseCount;
	}
	public void setSubscribeHouseCount(Integer subscribeHouseCount) {
		this.subscribeHouseCount = subscribeHouseCount;
	}
	public String getSubscribeHouseRate() {
		return subscribeHouseRate;
	}
	public void setSubscribeHouseRate(String subscribeHouseRate) {
		this.subscribeHouseRate = subscribeHouseRate;
	}
	public Long getSubscribeMoney() {
		return subscribeMoney;
	}
	public void setSubscribeMoney(Long subscribeMoney) {
		this.subscribeMoney = subscribeMoney;
	}
	public String getSubscribeMoneyRate() {
		return subscribeMoneyRate;
	}
	public void setSubscribeMoneyRate(String subscribeMoneyRate) {
		this.subscribeMoneyRate = subscribeMoneyRate;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public String getSignRate() {
		return signRate;
	}
	public void setSignRate(String signRate) {
		this.signRate = signRate;
	}
	public Long getSignHouseMoney() {
		return signHouseMoney;
	}
	public void setSignHouseMoney(Long signHouseMoney) {
		this.signHouseMoney = signHouseMoney;
	}
	public String getSignHouseMoneyRate() {
		return signHouseMoneyRate;
	}
	public void setSignHouseMoneyRate(String signHouseMoneyRate) {
		this.signHouseMoneyRate = signHouseMoneyRate;
	}
	public String getNewCustomerSignedRate() {
		return newCustomerSignedRate;
	}
	public void setNewCustomerSignedRate(String newCustomerSignedRate) {
		this.newCustomerSignedRate = newCustomerSignedRate;
	}
	public String getMomeryCustomerSignedRate() {
		return momeryCustomerSignedRate;
	}
	public void setMomeryCustomerSignedRate(String momeryCustomerSignedRate) {
		this.momeryCustomerSignedRate = momeryCustomerSignedRate;
	}
	public String getOldCustomerSignedRate() {
		return oldCustomerSignedRate;
	}
	public void setOldCustomerSignedRate(String oldCustomerSignedRate) {
		this.oldCustomerSignedRate = oldCustomerSignedRate;
	}
	public String getContratCuSignedRate() {
		return contratCuSignedRate;
	}
	public void setContratCuSignedRate(String contratCuSignedRate) {
		this.contratCuSignedRate = contratCuSignedRate;
	}
	@Override
	public String toString() {
		return "ReportResult [projectName=" + projectName + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", visitCount=" + visitCount + ", validVisitRate=" + validVisitRate + ", validNewCuVisitRate="
				+ validNewCuVisitRate + ", oldCuVisitRate=" + oldCuVisitRate + ", newCuCount=" + newCuCount
				+ ", totalOldCuCount=" + totalOldCuCount + ", totalCuCount=" + totalCuCount + ", subscribeHouseCount="
				+ subscribeHouseCount + ", subscribeHouseRate=" + subscribeHouseRate + ", subscribeMoney="
				+ subscribeMoney + ", subscribeMoneyRate=" + subscribeMoneyRate + ", signCount=" + signCount
				+ ", signRate=" + signRate + ", signHouseMoney=" + signHouseMoney + ", signHouseMoneyRate="
				+ signHouseMoneyRate + ", newCustomerSignedRate=" + newCustomerSignedRate
				+ ", momeryCustomerSignedRate=" + momeryCustomerSignedRate + ", oldCustomerSignedRate="
				+ oldCustomerSignedRate + ", contratCuSignedRate=" + contratCuSignedRate + "]";
	}

}
