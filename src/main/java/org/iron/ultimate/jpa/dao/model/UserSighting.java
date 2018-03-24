package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_SIGHTING")
public class UserSighting implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SIGHTING_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sightingId;

	@Column(name = "SIGHTING_TS")
	private String sightingTs;
	
	@Column(name = "RANK_NAME")
	private String rankName;

	@Column(name = "USER_NAME_ID")
	private Long userNameId;
	
	public UserSighting() {
		super();
	}
	public UserSighting(String rankName, Long userNameId) {
		super();
		this.rankName = rankName;
		this.userNameId = userNameId;
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
	public Long getUserNameId() {
		return userNameId;
	}
	public void setUserNameId(Long userNameId) {
		this.userNameId = userNameId;
	}
	
}
