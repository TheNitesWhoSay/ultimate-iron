package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HISCORE_SNAPSHOT_SKILL_RANK")
public class HiscoreSnapshotSkillRank implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embeddable
	public static class Pk implements Serializable {
		
		private static final long serialVersionUID = 1L;

		@Column(name = "HISCORE_SNAPSHOT_ID")
		private Long hiscoreSnapshotId;

		@Column(name = "ACCOUNT_TYPE_ID")
		private Long accountTypeId;

		@Column(name = "SKILL_ID")
		private Long skillId;
		
		public Pk() {
			super();
		}

		public Long getHiscoreSnapshotId() {
			return hiscoreSnapshotId;
		}
		public void setHiscoreSnapshotId(Long hiscoreSnapshotId) {
			this.hiscoreSnapshotId = hiscoreSnapshotId;
		}
		public Long getAccountTypeId() {
			return accountTypeId;
		}
		public void setAccountTypeId(Long accountTypeId) {
			this.accountTypeId = accountTypeId;
		}
		public Long getSkillId() {
			return skillId;
		}
		public void setSkillId(Long skillId) {
			this.skillId = skillId;
		}
		
	}
	
	@EmbeddedId
	private Pk pk;

	@Column(name = "SKILL_RANK")
	private Long skillRank;
	
	public HiscoreSnapshotSkillRank() {
		super();
	}

	public Pk getPk() {
		return pk;
	}
	public void setPk(Pk pk) {
		this.pk = pk;
	}
	public Long getSkillRank() {
		return skillRank;
	}
	public void setSkillRank(Long skillRank) {
		this.skillRank = skillRank;
	}
	
}
