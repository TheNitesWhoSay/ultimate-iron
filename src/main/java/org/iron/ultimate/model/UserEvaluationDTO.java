package org.iron.ultimate.model;

import java.io.Serializable;

public class UserEvaluationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private ClanRankDTO currentRank;
	private ClanRankDTO eligibleRank;
	private String message;
	
	public UserEvaluationDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ClanRankDTO getCurrentRank() {
		return currentRank;
	}
	public void setCurrentRank(ClanRankDTO currentRank) {
		this.currentRank = currentRank;
	}
	public ClanRankDTO getEligibleRank() {
		return eligibleRank;
	}
	public void setEligibleRank(ClanRankDTO eligibleRank) {
		this.eligibleRank = eligibleRank;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
