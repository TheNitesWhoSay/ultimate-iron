package org.iron.ultimate.model;

import java.io.Serializable;
import java.util.Map;

public class MultiHiscoreEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long skillId;
	private String skillName;
	private String displayName;
	private String abbreviation;
	private Integer liteIndex;
	private Long level;
	private Long experience;
	private Map<String, Long> accountTypeRanks;
	private Map<String, Long> pastAccountTypeLevels;
	private Map<String, Long> pastAccountTypeExperience;
	
	public MultiHiscoreEntry() {
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
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getExperience() {
		return experience;
	}
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	public Map<String, Long> getAccountTypeRanks() {
		return accountTypeRanks;
	}
	public void setAccountTypeRanks(Map<String, Long> accountTypeRanks) {
		this.accountTypeRanks = accountTypeRanks;
	}
	public Map<String, Long> getPastAccountTypeLevels() {
		return pastAccountTypeLevels;
	}
	public void setPastAccountTypeLevels(Map<String, Long> pastAccountTypeLevels) {
		this.pastAccountTypeLevels = pastAccountTypeLevels;
	}
	public Map<String, Long> getPastAccountTypeExperience() {
		return pastAccountTypeExperience;
	}
	public void setPastAccountTypeExperience(Map<String, Long> pastAccountTypeExperience) {
		this.pastAccountTypeExperience = pastAccountTypeExperience;
	}
	
}
