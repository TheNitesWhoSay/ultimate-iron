package org.iron.ultimate.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.iron.ultimate.exception.UserNotFoundException;
import org.iron.ultimate.jpa.dao.model.HiscoreSnapshot;
import org.iron.ultimate.jpa.dao.model.HiscoreSnapshotSkill;
import org.iron.ultimate.jpa.dao.model.HiscoreSnapshotSkillRank;
import org.iron.ultimate.jpa.dao.model.RsUserName;
import org.iron.ultimate.jpa.dao.repository.HiscoreSnapshotRepository;
import org.iron.ultimate.jpa.dao.repository.HiscoreSnapshotSkillRankRepository;
import org.iron.ultimate.jpa.dao.repository.HiscoreSnapshotSkillRepository;
import org.iron.ultimate.jpa.dao.repository.RsUserNameRepository;
import org.iron.ultimate.jpa.dao.repository.UserSightingRepository;
import org.iron.ultimate.model.AccountTypeDTO;
import org.iron.ultimate.model.MultiHiscoreEntry;
import org.iron.ultimate.model.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HiscoreSnapshotService {

	@Autowired
	private HiscoreService hiscoreService;
	
	@Autowired
	private HiscoreSnapshotRepository hiscoreSnapshotRepository;

	@Autowired
	private HiscoreSnapshotSkillRepository hiscoreSnapshotSkillRepository;

	@Autowired
	private HiscoreSnapshotSkillRankRepository hiscoreSnapshotSkillRankRepository;

	@Autowired
	private RsUserNameRepository rsUserNameRepository;
	
	@Autowired
	private UserSightingRepository userSightingRepository;
	
	public UserProfileDTO takeHiscoreSnapshot(Long userNameId, String username) throws UserNotFoundException {
		
		Date currentTimestamp = hiscoreSnapshotRepository.getCurrentTimestamp();
		UserProfileDTO userProfile = hiscoreService.getUserProfile(username);
		
		List<AccountTypeDTO> relevantAccountTypeDtos = userProfile.getRelevantAccountTypes();
		Map<String, Long> relevantAccountTypesToIds = new HashMap<String, Long>();
		for ( AccountTypeDTO relevantAccountTypeDto : relevantAccountTypeDtos ) {
			relevantAccountTypesToIds.put(relevantAccountTypeDto.getAccountType(), relevantAccountTypeDto.getAccountTypeId());
		}
		
		HiscoreSnapshot hiscoreSnapshot = new HiscoreSnapshot();
		hiscoreSnapshot.setSnapshotTs(currentTimestamp);
		hiscoreSnapshot.setUserNameId(userNameId);
		hiscoreSnapshot = hiscoreSnapshotRepository.save(hiscoreSnapshot);
		Long hiscoreSnapshotId = hiscoreSnapshot.getHiscoreSnapshotId();
		
		List<HiscoreSnapshotSkill> hiscoreSnapshotSkills = new ArrayList<HiscoreSnapshotSkill>();
		List<HiscoreSnapshotSkillRank> hiscoreSnapshotSkillRanks = new ArrayList<HiscoreSnapshotSkillRank>();
		
		Map<String, MultiHiscoreEntry> hiscores = userProfile.getHiscores();
		Set<String> skillNames = hiscores.keySet();
		for ( String skillName : skillNames ) {
			MultiHiscoreEntry multiHiscoreEntry = hiscores.get(skillName);
			Long skillId = multiHiscoreEntry.getSkillId();
			Long level = multiHiscoreEntry.getLevel();
			Long experience = multiHiscoreEntry.getExperience();
			
			HiscoreSnapshotSkill.Pk hiscoreSnapshotSkillPk = new HiscoreSnapshotSkill.Pk();
			hiscoreSnapshotSkillPk.setHiscoreSnapshotId(hiscoreSnapshotId);
			hiscoreSnapshotSkillPk.setSkillId(skillId);
			
			HiscoreSnapshotSkill hiscoreSnapshotSkill = new HiscoreSnapshotSkill();
			hiscoreSnapshotSkill.setPk(hiscoreSnapshotSkillPk);
			hiscoreSnapshotSkill.setSkillLevel(level);
			hiscoreSnapshotSkill.setSkillExperience(experience);
			hiscoreSnapshotSkills.add(hiscoreSnapshotSkill);
			
			Map<String, Long> accountTypeRanks = multiHiscoreEntry.getAccountTypeRanks();
			Set<String> rankedAccountTypes = accountTypeRanks.keySet();
			for ( String accountType : rankedAccountTypes ) {
				Long rank = accountTypeRanks.get(accountType);
				Long accountTypeId = relevantAccountTypesToIds.get(accountType);
				
				HiscoreSnapshotSkillRank.Pk hiscoreSnapshotSkillRankPk = new HiscoreSnapshotSkillRank.Pk();
				hiscoreSnapshotSkillRankPk.setHiscoreSnapshotId(hiscoreSnapshotId);
				hiscoreSnapshotSkillRankPk.setAccountTypeId(accountTypeId);
				hiscoreSnapshotSkillRankPk.setSkillId(skillId);
				
				HiscoreSnapshotSkillRank hiscoreSnapshotSkillRank = new HiscoreSnapshotSkillRank();
				hiscoreSnapshotSkillRank.setPk(hiscoreSnapshotSkillRankPk);
				hiscoreSnapshotSkillRank.setSkillRank(rank);
				
				hiscoreSnapshotSkillRanks.add(hiscoreSnapshotSkillRank);
			}
		}

		hiscoreSnapshotSkills = hiscoreSnapshotSkillRepository.save(hiscoreSnapshotSkills);
		hiscoreSnapshotSkillRanks = hiscoreSnapshotSkillRankRepository.save(hiscoreSnapshotSkillRanks);
		
		return userProfile;
	}

	public RsUserName getChangedUserName(Long userNameId) {
		Set<BigInteger> matchingUserNameIds = hiscoreSnapshotRepository.getMatchingUserNameIds(userNameId, 10);
		if ( matchingUserNameIds != null && !matchingUserNameIds.isEmpty() ) {
			if ( matchingUserNameIds.size() > 1 ) {
				BigInteger mostRecentlySeen = userSightingRepository.getMostRecentlySeenUserIdAmong(matchingUserNameIds);
				if ( mostRecentlySeen != null ) {
					return rsUserNameRepository.getOne(mostRecentlySeen.longValue());
				}
			} else if ( matchingUserNameIds.size() == 1 ) {
				return rsUserNameRepository.getOne(matchingUserNameIds.iterator().next().longValue());
			}
		}
		return null;
	}
	
}
