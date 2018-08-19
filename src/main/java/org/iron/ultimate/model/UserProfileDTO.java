package org.iron.ultimate.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private AccountTypeDTO currentAccountType;
	private Long currentTotalLevel;
	private Long currentTotalExperience;
	private List<AccountTypeDTO> pastAccountTypes;
	private List<AccountTypeDTO> relevantAccountTypes;
	private Map<String, Long> accountTypeRanks;
	private Map<String, Long> pastAccountTypeTotalLevels;
	private Map<String, Long> pastAccountTypeTotalExperience;
	private Map<String, MultiHiscoreEntry> hiscores;
	
	public UserProfileDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public AccountTypeDTO getCurrentAccountType() {
		return currentAccountType;
	}
	public void setCurrentAccountType(AccountTypeDTO currentAccountType) {
		this.currentAccountType = currentAccountType;
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
	public List<AccountTypeDTO> getRelevantAccountTypes() {
		return relevantAccountTypes;
	}
	public void setRelevantAccountTypes(List<AccountTypeDTO> relevantAccountTypes) {
		this.relevantAccountTypes = relevantAccountTypes;
	}
	public Map<String, Long> getAccountTypeRanks() {
		return accountTypeRanks;
	}
	public void setAccountTypeRanks(Map<String, Long> accountTypeRanks) {
		this.accountTypeRanks = accountTypeRanks;
	}
	public List<AccountTypeDTO> getPastAccountTypes() {
		return pastAccountTypes;
	}
	public void setPastAccountTypes(List<AccountTypeDTO> pastAccountTypes) {
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
	public Map<String, MultiHiscoreEntry> getHiscores() {
		return hiscores;
	}
	public void setHiscores(Map<String, MultiHiscoreEntry> hiscores) {
		this.hiscores = hiscores;
	}

	@Override
	public String toString() {
		return "UserProfileDTO [username=" + username + ", currentAccountType=" + currentAccountType
				+ ", currentTotalLevel=" + currentTotalLevel + ", currentTotalExperience=" + currentTotalExperience
				+ ", pastAccountTypes=" + pastAccountTypes + ", relevantAccountTypes=" + relevantAccountTypes
				+ ", accountTypeRanks=" + accountTypeRanks + ", pastAccountTypeTotalLevels="
				+ pastAccountTypeTotalLevels + ", pastAccountTypeTotalExperience=" + pastAccountTypeTotalExperience
				+ ", hiscores=" + hiscores + "]";
	}
	
}
