package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 房源收藏
 * @author Administrator
 *
 */
@Entity
@Table(name="t_housecollect")
public class HouseCollect {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer collectId;
	//目标Id
	private String targetId;
	//收藏的房源Id集合，以“，” 隔开
	private String collectHouseId;
	public Integer getCollectId() {
		return collectId;
	}
	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getCollectHouseId() {
		return collectHouseId;
	}
	public void setCollectHouseId(String collectHouseId) {
		this.collectHouseId = collectHouseId;
	}
	
	
	
}
