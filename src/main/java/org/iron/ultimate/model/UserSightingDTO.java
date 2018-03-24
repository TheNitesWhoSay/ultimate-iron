package org.iron.ultimate.model;

import java.io.Serializable;

public class UserSightingDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String rankName;
	
	public UserSightingDTO() {
		super();
	}
	public UserSightingDTO(String username) {
		super();
		this.username = username;
	}
	public UserSightingDTO(String username, String rankName) {
		super();
		this.username = username;
		this.rankName = rankName;
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
	
}
