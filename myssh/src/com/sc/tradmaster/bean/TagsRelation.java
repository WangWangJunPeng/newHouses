package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name="t_tagsrelation")
public class TagsRelation {
	
	private Integer relationId;//主键，自增
	private String targetId;//目标Id
	private String tags;//标签ID的集合
	private String originalTags;//原始标签集合
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getOriginalTags() {
		return originalTags;
	}
	public void setOriginalTags(String originalTags) {
		this.originalTags = originalTags;
	}
}
