package org.iron.ultimate.model;

import java.io.Serializable;

public class HiscoreEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long skillId;
	private String skillName;
	private String displayName;
	private String abbreviation;
	private Integer liteIndex;
	private Long rank;
	private Long level;
	private Long experience;
	
	public HiscoreEntry() {
		super();
	}
	public HiscoreEntry(SkillDTO skillDto, Long rank, Long level, Long experience) {
		super();
		if ( skillDto != null ) {
			this.skillId = skillDto.getSkillId();
			this.skillName = skillDto.getSkillName();
			this.displayName = skillDto.getDisplayName();
			this.abbreviation = skillDto.getAbbreviation();
			this.liteIndex = skillDto.getLiteIndex();
		}
		this.rank = rank;
		this.level = level;
		this.experience = experience;
	}
	public HiscoreEntry(Long skillId, String skillName, String displayName, String abbreviation, Integer liteIndex,
			Long level, Long experience) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.displayName = displayName;
		this.abbreviation = abbreviation;
		this.liteIndex = liteIndex;
		this.level = level;
		this.experience = experience;
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
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
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
	
}
