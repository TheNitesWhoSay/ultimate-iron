package org.iron.ultimate.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {

	REGULAR,
	IRONMAN,
	HARDCORE,
	ULTIMATE;

	private static Map<AccountType, AccountType[]> inheritedAccountTypes;

	static {
		inheritedAccountTypes = new HashMap<AccountType, AccountType[]>();
		inheritedAccountTypes.put(REGULAR, new AccountType[] {});
		inheritedAccountTypes.put(IRONMAN, new AccountType[] { REGULAR });
		inheritedAccountTypes.put(HARDCORE, new AccountType[] { IRONMAN, REGULAR });
		inheritedAccountTypes.put(ULTIMATE, new AccountType[] { IRONMAN, REGULAR });
	}
	
	public AccountType[] getInheritedAccountTypes() {
		return inheritedAccountTypes.get(this);
	}

}
