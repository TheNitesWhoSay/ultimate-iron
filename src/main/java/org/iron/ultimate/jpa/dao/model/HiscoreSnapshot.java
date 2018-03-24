package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HISCORE_SNAPSHOT")
public class HiscoreSnapshot implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "HISCORE_SNAPSHOT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long hiscoreSnapshotId;

	@Column(name = "SNAPSHOT_TS")
	private Date snapshotTs;
	
	@Column(name = "SIGHTING_TS")
	private Long sightingTs;
	
	public HiscoreSnapshot() {
		super();
	}

	public Long getHiscoreSnapshotId() {
		return hiscoreSnapshotId;
	}
	public void setHiscoreSnapshotId(Long hiscoreSnapshotId) {
		this.hiscoreSnapshotId = hiscoreSnapshotId;
	}
	public Date getSnapshotTs() {
		return snapshotTs;
	}
	public void setSnapshotTs(Date snapshotTs) {
		this.snapshotTs = snapshotTs;
	}
	public Long getSightingTs() {
		return sightingTs;
	}
	public void setSightingTs(Long sightingTs) {
		this.sightingTs = sightingTs;
	}
	
}
