package org.iron.ultimate.service;

import org.iron.ultimate.exception.MethodFailureException;
import org.iron.ultimate.exception.UserNotFoundException;
import org.iron.ultimate.exception.ValidationException;
import org.iron.ultimate.jpa.dao.model.ArUserName;
import org.iron.ultimate.jpa.dao.model.DirClanRank;
import org.iron.ultimate.jpa.dao.model.RsClanMember;
import org.iron.ultimate.jpa.dao.model.RsUserName;
import org.iron.ultimate.jpa.dao.model.UnprocessedSighting;
import org.iron.ultimate.jpa.dao.repository.ArUserNameRepository;
import org.iron.ultimate.jpa.dao.repository.DirClanRankRepository;
import org.iron.ultimate.jpa.dao.repository.RsClanMemberRepository;
import org.iron.ultimate.jpa.dao.repository.RsUserNameRepository;
import org.iron.ultimate.jpa.dao.repository.UnprocessedSightingRepository;
import org.iron.ultimate.jpa.dao.repository.UserSightingRepository;
import org.iron.ultimate.model.ClanRankDTO;
import org.iron.ultimate.model.UserEvaluationDTO;
import org.iron.ultimate.model.UserProfileDTO;
import org.iron.ultimate.model.enums.AccountType;
import org.iron.ultimate.model.enums.ClanRank;
import org.iron.ultimate.model.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

	@Autowired
	private HiscoreSnapshotService hiscoreSnapshotService;
	
	@Autowired
	private DiscordService discordService;
	
	@Autowired
	private UnprocessedSightingRepository unprocessedSightingRepository;
	
	@Autowired
	private UserSightingRepository userSightingRepository;
	
	@Autowired
	private RsUserNameRepository rsUserNameRepository;
	
	@Autowired
	private ArUserNameRepository arUserNameRepository;
	
	@Autowired
	private RsClanMemberRepository rsClanMemberRepository;
	
	@Autowired
	private DirClanRankRepository dirClanRankRepository;
	
	@Autowired
	private ResourceMapper resourceMapper;
	
	public String cleanupUnusableSighting(UnprocessedSighting unprocessedSighting) {
		if ( unprocessedSighting != null ) {
			Long userNameId = unprocessedSighting.getUserNameId();
			if ( userNameId != null ) {
				userSightingRepository.deleteByUserNameId(userNameId);
				rsUserNameRepository.delete(userNameId);
				return "Cleanup finished!";
			} else {
				throw new MethodFailureException("Cleanup method called with a null userNameId!");
			}
		} else {
			throw new MethodFailureException("Cleanup method called on a null unprocessedSighting!");
		}
	}

	public String processPendingUser() {
		UnprocessedSighting unprocessedSighting = unprocessedSightingRepository.getUnprocessedSighting();
		if ( unprocessedSighting != null ) {
			Long userNameId = unprocessedSighting.getUserNameId();
			String username = unprocessedSighting.getUserName();
			Long sightingId = unprocessedSighting.getSightingId();
			String rankName = unprocessedSighting.getRankName();
			if ( userNameId == null || username == null || username.isEmpty() || sightingId == null ) {
				return cleanupUnusableSighting(unprocessedSighting);
			}
			try {
				hiscoreSnapshotService.takeHiscoreSnapshot(userNameId, username);
				RsUserName changedRsUserName = hiscoreSnapshotService.getChangedUserName(userNameId);
				if ( changedRsUserName != null ) { // Name change detected
					Long changedUserNameId = changedRsUserName.getUserNameId();
					Long clanMemberId = changedRsUserName.getClanMemberId();
					String changedUserName = changedRsUserName.getUserName();
					
					ArUserName arUserName = new ArUserName();
					arUserName.setUserNameId(changedUserNameId);
					arUserName.setClanMemberId(clanMemberId);
					arUserName.setUserName(changedUserName);
					arUserNameRepository.save(arUserName);
					rsUserNameRepository.delete(changedUserNameId);
					
					RsUserName rsUserName = new RsUserName();
					rsUserName.setUserNameId(userNameId);
					rsUserName.setClanMemberId(clanMemberId);
					rsUserName.setUserName(username);
					rsUserNameRepository.save(rsUserName);
					
					RsClanMember rsClanMember = rsClanMemberRepository.findOne(clanMemberId);
					String oldRankName = rsClanMember.getRankName();
					if ( rankName != null && !rankName.isEmpty() && !rankName.equals("UNKNOWN") && !rankName.equals(oldRankName)) {
						rsClanMember.setRankName(rankName);
						rsClanMemberRepository.save(rsClanMember);
						String response = "Name change detected, updated clan member with username " + changedUserName + " to new name " + username
								+ " and updated rank from " + oldRankName + " to " + rankName;
						discordService.sendMessage(response);
						return response;
					} else {
						String response = "Name change detected, updated clan member with username " + changedUserName + " to new name " + username;
						discordService.sendMessage(response);
						return response;
					}
				} else { // New user detected
					RsClanMember rsClanMember = new RsClanMember();
					rsClanMember.setRankName(rankName);
					rsClanMember = rsClanMemberRepository.save(rsClanMember);
					Long clanMemberId = rsClanMember.getClanMemberId();
					
					RsUserName rsUserName = new RsUserName();
					rsUserName.setUserNameId(userNameId);
					rsUserName.setClanMemberId(clanMemberId);
					rsUserName.setUserName(username);
					rsUserNameRepository.save(rsUserName);
					String response = "New user detected, added clan member with username " + username + " to the clan member list with rank " + rankName;
					discordService.sendMessage(response);
					return response;
				}
			} catch (UserNotFoundException e) {
				return cleanupUnusableSighting(unprocessedSighting);
			}
		}
		return null;
	}
	
	public UserEvaluationDTO checkClanMember(String username) throws UserNotFoundException {
		if ( username == null || username.isEmpty() ) {
			throw new ValidationException("No user provided!");
		}
		RsUserName rsUserName = rsUserNameRepository.findByUserNameEqualsIgnoreCase(username);
		if ( rsUserName != null ) {
			Long clanMemberId = rsUserName.getClanMemberId();
			if ( clanMemberId == null ) {
				UserEvaluationDTO userEvaluationDto = new UserEvaluationDTO();
				userEvaluationDto.setUsername(username);
				userEvaluationDto.setMessage("User was recently added and is currently pending a name change check.");
				return userEvaluationDto;
			} else {
				return checkClanMember(rsUserName);
			}
		}
		UserEvaluationDTO userEvaluationDto = new UserEvaluationDTO();
		userEvaluationDto.setUsername(username);
		userEvaluationDto.setMessage("User has not been sighted yet!");
		return userEvaluationDto;
	}
	
	public UserEvaluationDTO checkClanMember(RsUserName rsUserName) throws UserNotFoundException {
		Long clanMemberId = rsUserName.getClanMemberId();
		String username = rsUserName.getUserName();
		RsClanMember rsClanMember = rsClanMemberRepository.findOne(clanMemberId);
		String currentRankName = rsClanMember.getRankName();
		ClanRank currentClanRank = ClanRank.valueOf(currentRankName);
		
		UserProfileDTO userProfile = hiscoreSnapshotService.takeHiscoreSnapshot(rsUserName.getUserNameId(), rsUserName.getUserName());
		Long totalLevel = userProfile.getCurrentTotalLevel();
		AccountType accountType = AccountType.valueOf(userProfile.getCurrentAccountType().getAccountType());
		
		UserEvaluationDTO userEvaluationDto = new UserEvaluationDTO();
		userEvaluationDto.setUsername(username);
		
		if ( accountType == AccountType.IRONMAN || accountType == AccountType.HARDCORE || accountType == AccountType.ULTIMATE ) {
			DirClanRank dirCurrentClanRank = dirClanRankRepository.findOne(currentRankName);
			ClanRankDTO clanRankDto = resourceMapper.convertToClanRankDTO(dirCurrentClanRank);
			userEvaluationDto.setCurrentRank(clanRankDto);
			boolean isStaff = dirCurrentClanRank.getIsStaff();
			if ( isStaff ) {
				userEvaluationDto.setMessage("Clan member is staff and is not eligible for auto-ranking!");
				return userEvaluationDto;
			} else {
				DirClanRank highestEligibleRank = dirClanRankRepository.findHighestEligibleRank(totalLevel, clanMemberId);
				if ( highestEligibleRank != null ) {
					int highestEligibleRankIndex = highestEligibleRank.getRankIndex();
					int currentRankIndex = dirCurrentClanRank.getRankIndex();
					if ( highestEligibleRankIndex < currentRankIndex ) {
						if ( highestEligibleRank.getRankName().equals(ClanRank.UNRANKED.name()) ) {
							rsClanMember.setRankName(ClanRank.UNRANKED.name());
							rsClanMemberRepository.save(rsClanMember);
							String message = "Clan member " + username + " was moved from unknown to unranked.";
							discordService.sendMessage(message);
							userEvaluationDto.setMessage(message);
							return userEvaluationDto;
						} else {
							String message = "Clan member " + username + " is eligible to be promoted to " + highestEligibleRank.getRankName() + "!";
							discordService.sendMessage(message);
							userEvaluationDto.setMessage(message);
							return userEvaluationDto;
						}
					} else if ( highestEligibleRankIndex > currentRankIndex ) {
						String message = "Clan member " + username + " should be demoted to " + highestEligibleRank.getRankName() + ".";
						discordService.sendMessage(message);
						userEvaluationDto.setMessage(message);
						return userEvaluationDto;
					}
				}
				userEvaluationDto.setMessage("Clan member is already the rank they should be.");
				return userEvaluationDto;
			}
		} else if ( currentClanRank == ClanRank.UNKNOWN ) { // Not iron, rank unknown, set to unranked
			rsClanMember.setRankName(ClanRank.UNRANKED.name());
			rsClanMemberRepository.save(rsClanMember);
			userEvaluationDto.setMessage("Clan member is not iron, updated their rank from unknown to unranked!");
			return userEvaluationDto;
		} else if ( currentClanRank != ClanRank.UNRANKED ) { // Not iron, not unknown or unranked, send de-rank message
			discordService.sendMessage("Clan member " + username + " has rank but is not iron, their rank should be removed!");
			userEvaluationDto.setMessage("Clan member has rank but is not iron, their rank should be removed!");
			return userEvaluationDto;
		} else {
			userEvaluationDto.setMessage("Clan member is not iron and is appropriately unranked.");
			return userEvaluationDto;
		}
	}
	
}
