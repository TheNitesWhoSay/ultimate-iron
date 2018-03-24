package org.iron.ultimate.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
	
	public Map<String, String> getAccountTypes() {
		List<DirAccountType> dirAccountTypes = dirAccountTypeRepository.findAll();
		Map<String, String> accountTypes = new LinkedHashMap<String, String>();
		if ( dirAccountTypes != null ) {
			for ( DirAccountType dirAccountType : dirAccountTypes ) {
				accountTypes.put(dirAccountType.getAccountType(), dirAccountType.getDisplayName());
			}
		}
		return accountTypes;
	}
	
	public Map<String, String> getClanRanks() {
		List<DirClanRank> dirClanRanks = dirClanRankRepository.findAll();
		Map<String, String> clanRanks = new LinkedHashMap<String, String>();
		if ( dirClanRanks != null ) {
			for ( DirClanRank dirClanRank : dirClanRanks ) {
				clanRanks.put(dirClanRank.getRankName(), dirClanRank.getDisplayName());
			}
		}
		return clanRanks;
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
