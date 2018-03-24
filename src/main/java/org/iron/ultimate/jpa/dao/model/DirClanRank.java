package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIR_CLAN_RANK")
public class DirClanRank implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RANK_NAME")
	private String rankName;

	@Column(name = "DISPLAY_NAME")
	private String displayName;
	
	public DirClanRank() {
		super();
	}

	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
