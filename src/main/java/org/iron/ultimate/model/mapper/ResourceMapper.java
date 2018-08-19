package org.iron.ultimate.model.mapper;

import java.util.List;

import org.iron.ultimate.jpa.dao.model.DirAccountType;
import org.iron.ultimate.jpa.dao.model.DirClanRank;
import org.iron.ultimate.jpa.dao.model.DirSkill;
import org.iron.ultimate.model.AccountTypeDTO;
import org.iron.ultimate.model.ClanRankDTO;
import org.iron.ultimate.model.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;

import ma.glasnost.orika.MapperFacade;

public class ResourceMapper {

	@Autowired
	private MapperFacade mapperFacade;
	
	public SkillDTO convertToSkillDTO(DirSkill dirSkill) {
		return mapperFacade.map(dirSkill, SkillDTO.class);
	}
	
	public List<AccountTypeDTO> convertToAccountTypeDTO(List<DirAccountType> dirAccountTypes) {
		return mapperFacade.mapAsList(dirAccountTypes, AccountTypeDTO.class);
	}

	public ClanRankDTO convertToClanRankDTO(DirClanRank dirClanRanks) {
		return mapperFacade.map(dirClanRanks, ClanRankDTO.class);
	}
	public List<ClanRankDTO> convertToClanRankDTO(List<DirClanRank> dirClanRanks) {
		return mapperFacade.mapAsList(dirClanRanks, ClanRankDTO.class);
	}
	
}
