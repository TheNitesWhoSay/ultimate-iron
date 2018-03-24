package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HISCORE_SNAPSHOT_SKILL")
public class HiscoreSnapshotSkill implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HiscoreSnapshotSkillPk hiscoreSnapshotSkillPk;

	@Column(name = "SKILL_LEVEL")
	private Long skillLevel;
	
	@Column(name = "SKILL_EXPERIENCE")
	private Long skillExperience;
	
	public HiscoreSnapshotSkill() {
		super();
	}
	
	public HiscoreSnapshotSkillPk getHiscoreSnapshotSkillPk() {
		return hiscoreSnapshotSkillPk;
	}
	public void setHiscoreSnapshotSkillPk(HiscoreSnapshotSkillPk hiscoreSnapshotSkillPk) {
		this.hiscoreSnapshotSkillPk = hiscoreSnapshotSkillPk;
	}
	public Long getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(Long skillLevel) {
		this.skillLevel = skillLevel;
	}
	public Long getSkillExperience() {
		return skillExperience;
	}
	public void setSkillExperience(Long skillExperience) {
		this.skillExperience = skillExperience;
	}
	
}
