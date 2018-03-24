package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIR_ACCOUNT_TYPE")
public class DirAccountType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "DISPLAY_NAME")
	private String displayName;
	
	public DirAccountType() {
		super();
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
