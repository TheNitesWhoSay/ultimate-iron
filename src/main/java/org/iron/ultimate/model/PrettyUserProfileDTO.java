package org.iron.ultimate.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PrettyUserProfileDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String accountType;
	private Long totalLevel;
	private Long totalExperience;
	private Map<String, Long> accountTypeRanks;
	private List<String> pastAccountTypes;
	private Map<String, Long> pastAccountTypeTotalLevels;
	private Map<String, Long> pastAccountTypeTotalExperience;
	private Map<String, PrettyMultiHiscoreEntry> hiscores;
	
	public PrettyUserProfileDTO() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Long getTotalLevel() {
		return totalLevel;
	}
	public void setTotalLevel(Long totalLevel) {
		this.totalLevel = totalLevel;
	}
	public Long getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(Long totalExperience) {
		this.totalExperience = totalExperience;
	}
	public Map<String, Long> getAccountTypeRanks() {
		return accountTypeRanks;
	}
	public void setAccountTypeRanks(Map<String, Long> accountTypeRanks) {
		this.accountTypeRanks = accountTypeRanks;
	}
	public List<String> getPastAccountTypes() {
		return pastAccountTypes;
	}
	public void setPastAccountTypes(List<String> pastAccountTypes) {
		this.pastAccountTypes = pastAccountTypes;
	}
	public Map<String, Long> getPastAccountTypeTotalLevels() {
		return pastAccountTypeTotalLevels;
	}
	public void setPastAccountTypeTotalLevels(Map<String, Long> pastAccountTypeTotalLevels) {
		this.pastAccountTypeTotalLevels = pastAccountTypeTotalLevels;
	}
	public Map<String, Long> getPastAccountTypeTotalExperience() {
		return pastAccountTypeTotalExperience;
	}
	public void setPastAccountTypeTotalExperience(Map<String, Long> pastAccountTypeTotalExperience) {
		this.pastAccountTypeTotalExperience = pastAccountTypeTotalExperience;
	}
	public Map<String, PrettyMultiHiscoreEntry> getHiscores() {
		return hiscores;
	}
	public void setHiscores(Map<String, PrettyMultiHiscoreEntry> hiscores) {
		this.hiscores = hiscores;
	}
	
}
