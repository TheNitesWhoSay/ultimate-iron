package org.iron.ultimate.model;

import java.io.Serializable;
import java.util.Map;

public class PrettyMultiHiscoreEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long level;
	private Long experience;
	private Map<String, Long> accountTypeRanks;
	private Map<String, Long> pastAccountTypeLevels;
	private Map<String, Long> pastAccountTypeExperience;
	
	public PrettyMultiHiscoreEntry() {
		super();
	}
	
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getExperience() {
		return experience;
	}
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	public Map<String, Long> getAccountTypeRanks() {
		return accountTypeRanks;
	}
	public void setAccountTypeRanks(Map<String, Long> accountTypeRanks) {
		this.accountTypeRanks = accountTypeRanks;
	}
	public Map<String, Long> getPastAccountTypeLevels() {
		return pastAccountTypeLevels;
	}
	public void setPastAccountTypeLevels(Map<String, Long> pastAccountTypeLevels) {
		this.pastAccountTypeLevels = pastAccountTypeLevels;
	}
	public Map<String, Long> getPastAccountTypeExperience() {
		return pastAccountTypeExperience;
	}
	public void setPastAccountTypeExperience(Map<String, Long> pastAccountTypeExperience) {
		this.pastAccountTypeExperience = pastAccountTypeExperience;
	}
	
}
