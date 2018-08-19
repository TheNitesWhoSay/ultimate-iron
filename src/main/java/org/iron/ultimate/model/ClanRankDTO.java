package org.iron.ultimate.model;

import java.io.Serializable;

public class ClanRankDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String rankName;
	private Integer rankIndex;
	private String displayName;
	private Boolean isStaff;
	private Integer minimumTotal;
	private Integer minimumClanDays;
	
	public ClanRankDTO() {
		super();
	}

	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public Integer getRankIndex() {
		return rankIndex;
	}
	public void setRankIndex(Integer rankIndex) {
		this.rankIndex = rankIndex;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Boolean getIsStaff() {
		return isStaff;
	}
	public void setIsStaff(Boolean isStaff) {
		this.isStaff = isStaff;
	}
	public Integer getMinimumTotal() {
		return minimumTotal;
	}
	public void setMinimumTotal(Integer minimumTotal) {
		this.minimumTotal = minimumTotal;
	}
	public Integer getMinimumClanDays() {
		return minimumClanDays;
	}
	public void setMinimumClanDays(Integer minimumClanDays) {
		this.minimumClanDays = minimumClanDays;
	}

}
