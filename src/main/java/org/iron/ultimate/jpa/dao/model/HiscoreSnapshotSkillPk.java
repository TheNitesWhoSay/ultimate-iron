package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HiscoreSnapshotSkillPk implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "HIGHSCORE_SNAPSHOT_ID")
	private Long hiscoreSnapshotId;

	@Column(name = "SKILL_ID")
	private Long skillId;
	
	public HiscoreSnapshotSkillPk() {
		super();
	}
	
	public Long getHiscoreSnapshotId() {
		return hiscoreSnapshotId;
	}
	public void setHiscoreSnapshotId(Long hiscoreSnapshotId) {
		this.hiscoreSnapshotId = hiscoreSnapshotId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	
}
