package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIR_SKILL")
public class DirSkill implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SKILL_ID")
	private Long skillId;

	@Column(name = "SKILL_NAME")
	private String skillName;
	
	@Column(name = "DISPLAY_NAME")
	private String displayName;
	
	@Column(name = "ABBREVIATION")
	private String abbreviation;

	@Column(name = "LITE_INDEX")
	private Integer liteIndex;
	
	public DirSkill() {
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
