package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UNPROCESSED_SIGHTING")
public class UnprocessedSighting implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_NAME_ID")
	private Long userNameId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "SIGHTING_ID")
	private Long sightingId;

	@Column(name = "SIGHTING_TS")
	private String sightingTs;
	
	@Column(name = "RANK_NAME")
	private String rankName;
	
	public UnprocessedSighting() {
		super();
	}

	public Long getUserNameId() {
		return userNameId;
	}
	public void setUserNameId(Long userNameId) {
		this.userNameId = userNameId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getSightingId() {
		return sightingId;
	}
	public void setSightingId(Long sightingId) {
		this.sightingId = sightingId;
	}
	public String getSightingTs() {
		return sightingTs;
	}
	public void setSightingTs(String sightingTs) {
		this.sightingTs = sightingTs;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	
}
