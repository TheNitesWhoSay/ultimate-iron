package org.iron.ultimate.model.enums;

import java.util.HashSet;
import java.util.Set;

public enum ClanRank {

	LEADER,
	GENERAL,
	CAPTAIN,
	LIEUTENANT,
	SERGEANT,
	CORPORAL,
	RECRUIT,
	FRIEND,
	UNRANKED,
	UNKNOWN;

	private static Set<String> names;

	static {
		names = new HashSet<String>();
		ClanRank[] clanRanks = ClanRank.values();
		for ( ClanRank clanRank : clanRanks ) {
			names.add(clanRank.name());
		}
	}
	
	public static boolean contains(String name) {
		return names.contains(name);
	}

}
