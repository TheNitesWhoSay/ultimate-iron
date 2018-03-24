package org.iron.ultimate.model;

import java.io.Serializable;

public class SightingResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String rankName;
	private String actionTaken;
	
	public SightingResponseDTO() {
		super();
	}
	public SightingResponseDTO(String username, String rankName, String actionTaken) {
		super();
		this.username = username;
		this.rankName = rankName;
		this.actionTaken = actionTaken;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getActionTaken() {
		return actionTaken;
	}
	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}
	
}
