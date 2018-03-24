package org.iron.ultimate.model;

import java.io.Serializable;

public class SkillDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long skillId;
	private String skillName;
	private String displayName;
	private String abbreviation;
	private Integer liteIndex;
	
	public SkillDTO() {
		super();
	}

	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public Integer getLiteIndex() {
		return liteIndex;
	}
	public void setLiteIndex(Integer liteIndex) {
		this.liteIndex = liteIndex;
	}
	
}
