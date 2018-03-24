package org.iron.ultimate.model.enums;

public enum HiscoreCategory {

	REGULAR("hiscore_oldschool", AccountType.REGULAR),
	IRONMAN("hiscore_oldschool_ironman", AccountType.IRONMAN),
	HARDCORE("hiscore_oldschool_hardcore_ironman", AccountType.HARDCORE),
	ULTIMATE("hiscore_oldschool_ultimate", AccountType.ULTIMATE);
	
	private String urlComponent;
	private AccountType accountType;
	
	private HiscoreCategory(String urlComponent, AccountType accountType) {
		this.urlComponent = urlComponent;
		this.accountType = accountType;
	}
	
	@Override
	public String toString() {
		return urlComponent;
	}

	public AccountType getAccountType() {
		return accountType;
	}
	
}
