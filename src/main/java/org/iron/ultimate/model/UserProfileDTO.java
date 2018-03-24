package org.iron.ultimate.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String currentAccountType;
	private String currentAccountTypeDisplayName;
	private Long currentTotalLevel;
	private Long currentTotalExperience;
	private Map<String, Long> accountTypeRanks;
	private Map<String, String> pastAccountTypes;
	private Map<String, Long> pastAccountTypeTotalLevels;
	private Map<String, Long> pastAccountTypeTotalExperience;
	private List<MultiHiscoreEntry> hiscores;
	
	public UserProfileDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCurrentAccountType() {
		return currentAccountType;
	}
	public void setCurrentAccountType(String currentAccountType) {
		this.currentAccountType = currentAccountType;
	}
	public String getCurrentAccountTypeDisplayName() {
		return currentAccountTypeDisplayName;
	}
	public void setCurrentAccountTypeDisplayName(String currentAccountTypeDisplayName) {
		this.currentAccountTypeDisplayName = currentAccountTypeDisplayName;
	}
	public Long getCurrentTotalLevel() {
		return currentTotalLevel;
	}
	public void setCurrentTotalLevel(Long currentTotalLevel) {
		this.currentTotalLevel = currentTotalLevel;
	}
	public Long getCurrentTotalExperience() {
		return currentTotalExperience;
	}
	public void setCurrentTotalExperience(Long currentTotalExperience) {
		this.currentTotalExperience = currentTotalExperience;
	}
	public Map<String, Long> getAccountTypeRanks() {
		return accountTypeRanks;
	}
	public void setAccountTypeRanks(Map<String, Long> accountTypeRanks) {
		this.accountTypeRanks = accountTypeRanks;
	}
	public Map<String, String> getPastAccountTypes() {
		return pastAccountTypes;
	}
	public void setPastAccountTypes(Map<String, String> pastAccountTypes) {
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
	public List<MultiHiscoreEntry> getHiscores() {
		return hiscores;
	}
	public void setHiscores(List<MultiHiscoreEntry> hiscores) {
		this.hiscores = hiscores;
	}
	
}
