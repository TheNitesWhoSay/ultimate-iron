package org.iron.ultimate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iron.ultimate.exception.NonContiguousLiteIndexException;
import org.iron.ultimate.exception.ValidationException;
import org.iron.ultimate.jpa.dao.model.DirAccountType;
import org.iron.ultimate.jpa.dao.model.DirClanRank;
import org.iron.ultimate.jpa.dao.model.DirSkill;
import org.iron.ultimate.jpa.dao.repository.DirAccountTypeRepository;
import org.iron.ultimate.jpa.dao.repository.DirClanRankRepository;
import org.iron.ultimate.jpa.dao.repository.DirSkillRepository;
import org.iron.ultimate.model.AccountTypeDTO;
import org.iron.ultimate.model.ClanRankDTO;
import org.iron.ultimate.model.SkillDTO;
import org.iron.ultimate.model.enums.HiscoreCategory;
import org.iron.ultimate.model.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDataService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private DirSkillRepository dirSkillRepository;
	
	@Autowired
	private DirAccountTypeRepository dirAccountTypeRepository;
	
	@Autowired
	private DirClanRankRepository dirClanRankRepository;
	
	public Map<String, AccountTypeDTO> getAccountTypeMap() {
		Map<String, AccountTypeDTO> getAccountTypeMap = new HashMap<String, AccountTypeDTO>();
		List<AccountTypeDTO> accountTypes = getAccountTypes();
		for ( AccountTypeDTO accountType : accountTypes ) {
			getAccountTypeMap.put(accountType.getAccountType(), accountType);
		}
		return getAccountTypeMap;
	}
	
	public List<AccountTypeDTO> getAccountTypes() {
		List<DirAccountType> dirAccountTypes = dirAccountTypeRepository.findAll();
		if ( dirAccountTypes != null ) {
			return resourceMapper.convertToAccountTypeDTO(dirAccountTypes);
		} else {
			return new ArrayList<AccountTypeDTO>();
		}
	}
	
	public List<ClanRankDTO> getClanRanks() {
		List<DirClanRank> dirClanRanks = dirClanRankRepository.findAll();
		if ( dirClanRanks != null ) {
			return resourceMapper.convertToClanRankDTO(dirClanRanks);
		} else {
			return new ArrayList<ClanRankDTO>();
		}
	}
	
	public List<SkillDTO> getListOfSkills() {
		List<SkillDTO> skillDtos = new ArrayList<SkillDTO>();
		List<DirSkill> dirSkills = dirSkillRepository.findAll();
		if ( dirSkills != null && !dirSkills.isEmpty() ) {
			int expectedLiteIndex = 0;
			for ( DirSkill dirSkill : dirSkills ) {
				Integer liteIndex = dirSkill.getLiteIndex();
				if ( liteIndex == null || expectedLiteIndex != liteIndex.intValue() ) {
					throw new NonContiguousLiteIndexException();
				}
				skillDtos.add(resourceMapper.convertToSkillDTO(dirSkill));
				expectedLiteIndex ++;
			}
		}
		return skillDtos;
	}
	
	public List<String> getHiscoreCategories() {
		List<String> hiscoreCategories = new ArrayList<String>();
		HiscoreCategory[] hiscoreCategoryEnums = HiscoreCategory.values();
		if ( hiscoreCategoryEnums != null ) {
			for ( HiscoreCategory hiscoreCategoryEnum : hiscoreCategoryEnums ) {
				hiscoreCategories.add(hiscoreCategoryEnum.name());
			}
		}
		return hiscoreCategories;
	}
	
	public HiscoreCategory getHiscoreCategory(String hiscoreCategory) {
		if ( hiscoreCategory != null && !hiscoreCategory.isEmpty() ) {
			try {
				HiscoreCategory resolvedHiscoreCategory = HiscoreCategory.valueOf(hiscoreCategory.toUpperCase());
				if ( hiscoreCategory != null ) {
					return resolvedHiscoreCategory;
				}
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		throw new ValidationException("Invalid hiscore category!");
	}
	
}
