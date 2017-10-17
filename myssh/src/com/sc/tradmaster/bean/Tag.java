package com.sc.tradmaster.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_tag")
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tagId;//自增主键
	private String tagName;//标签名
	private Integer tagTypeId;//所属标签类目
	private String dic;//对标签的描述
	private Integer parentTagId;//父标签的Id
	private String projectId; //项目ID，标准模板id为0000
	private Integer tagStatus;//0-删除，1-正常
	private Integer originalTag; //0-原始，1-自建
	private Integer originalTagId;//对应原始标签的Id
	@Transient
	private List<Tag> children;
	@Transient
	private Integer selected;//是否被选中 0-没有，1-选中
	public Integer getOriginalTag() {
		return originalTag;
	}
	public void setOriginalTag(Integer originalTag) {
		this.originalTag = originalTag;
	}
	public Integer getTagStatus() {
		return tagStatus;
	}
	public void setTagStatus(Integer tagStatus) {
		this.tagStatus = tagStatus;
	}
	public Integer getSelected() {
		return selected;
	}
	public void setSelected(Integer selected) {
		this.selected = selected;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Integer getTagTypeId() {
		return tagTypeId;
	}
	public void setTagTypeId(Integer tagTypeId) {
		this.tagTypeId = tagTypeId;
	}
	public String getDic() {
		return dic;
	}
	public void setDic(String dic) {
		this.dic = dic;
	}
	public Integer getParentTagId() {
		return parentTagId;
	}
	public void setParentTagId(Integer parentTagId) {
		this.parentTagId = parentTagId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public List<Tag> getChildren() {
		return children;
	}
	public void setChildren(List<Tag> children) {
		this.children = children;
	}
	public Integer getOriginalTagId() {
		return originalTagId;
	}
	public void setOriginalTagId(Integer originalTagId) {
		this.originalTagId = originalTagId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((dic == null) ? 0 : dic.hashCode());
		result = prime * result + ((originalTag == null) ? 0 : originalTag.hashCode());
		result = prime * result + ((originalTagId == null) ? 0 : originalTagId.hashCode());
		result = prime * result + ((parentTagId == null) ? 0 : parentTagId.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((selected == null) ? 0 : selected.hashCode());
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		result = prime * result + ((tagStatus == null) ? 0 : tagStatus.hashCode());
		result = prime * result + ((tagTypeId == null) ? 0 : tagTypeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (dic == null) {
			if (other.dic != null)
				return false;
		} else if (!dic.equals(other.dic))
			return false;
		if (originalTag == null) {
			if (other.originalTag != null)
				return false;
		} else if (!originalTag.equals(other.originalTag))
			return false;
		if (originalTagId == null) {
			if (other.originalTagId != null)
				return false;
		} else if (!originalTagId.equals(other.originalTagId))
			return false;
		if (parentTagId == null) {
			if (other.parentTagId != null)
				return false;
		} else if (!parentTagId.equals(other.parentTagId))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (selected == null) {
			if (other.selected != null)
				return false;
		} else if (!selected.equals(other.selected))
			return false;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		if (tagStatus == null) {
			if (other.tagStatus != null)
				return false;
		} else if (!tagStatus.equals(other.tagStatus))
			return false;
		if (tagTypeId == null) {
			if (other.tagTypeId != null)
				return false;
		} else if (!tagTypeId.equals(other.tagTypeId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagName=" + tagName + ", tagTypeId=" + tagTypeId + ", dic=" + dic
				+ ", parentTagId=" + parentTagId + ", projectId=" + projectId + ", tagStatus=" + tagStatus
				+ ", originalTag=" + originalTag + ", originalTagId=" + originalTagId + ", children=" + children
				+ ", selected=" + selected + "]";
	}

	

	

	
	
	
	
	
	
	
	
	


}
