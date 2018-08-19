package org.iron.ultimate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private Date joinDate;
	private List<String> pastUsernames;
	
	public UserSummaryDTO() {
		super();
	}
	public UserSummaryDTO(String username, Date joinDate) {
		super();
		this.username = username;
		this.joinDate = joinDate;
		this.pastUsernames = new ArrayList<String>();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public List<String> getPastUsernames() {
		return pastUsernames;
	}
	public void setPastUsernames(List<String> pastUsernames) {
		this.pastUsernames = pastUsernames;
	}
	
}
