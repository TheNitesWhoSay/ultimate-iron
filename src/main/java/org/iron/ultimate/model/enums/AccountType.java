package org.iron.ultimate.model.enums;

public enum AccountType {

	REGULAR(new AccountType[] {}),
	IRONMAN(new AccountType[] { REGULAR }),
	HARDCORE(new AccountType[] { IRONMAN, REGULAR }),
	ULTIMATE(new AccountType[] { IRONMAN, REGULAR });

	private AccountType[] inheritedAccountTypes;
	
	private AccountType(AccountType[] inheritedAccountTypes) {
		this.inheritedAccountTypes = inheritedAccountTypes;
	}
	
	public AccountType[] getInheritedAccountTypes() {
		return inheritedAccountTypes;
	}

}
