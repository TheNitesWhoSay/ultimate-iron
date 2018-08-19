package org.iron.ultimate.model;

import java.io.Serializable;

public class AccountTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long accountTypeId;
	private String accountType;
	private String displayName;
	
	public AccountTypeDTO() {
		super();
	}

	public Long getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
