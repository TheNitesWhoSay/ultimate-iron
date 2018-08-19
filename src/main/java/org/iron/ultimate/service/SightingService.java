package org.iron.ultimate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.iron.ultimate.jpa.dao.model.DirClanRank;
import org.iron.ultimate.jpa.dao.model.RsClanMember;
import org.iron.ultimate.jpa.dao.model.RsUserName;
import org.iron.ultimate.jpa.dao.model.UserSighting;
import org.iron.ultimate.jpa.dao.repository.DirClanRankRepository;
import org.iron.ultimate.jpa.dao.repository.RsClanMemberRepository;
import org.iron.ultimate.jpa.dao.repository.RsUserNameRepository;
import org.iron.ultimate.jpa.dao.repository.UserSightingRepository;
import org.iron.ultimate.model.SightingResponseDTO;
import org.iron.ultimate.model.UserSightingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SightingService {

	@Autowired
	private UserSightingRepository userSightingRepository;
	
	@Autowired
	private RsUserNameRepository rsUserNameRepository;
	
	@Autowired
	private RsClanMemberRepository rsClanMemberRespository;
	
	@Autowired
	private DirClanRankRepository dirClanRankRepository;
	
	public List<SightingResponseDTO> sightUsers(List<UserSightingDTO> usersSighted, boolean createSightingAndUser) {
		
		for ( UserSightingDTO userSighted : usersSighted ) {
			if ( userSighted != null ) {
				String rankName = userSighted.getRankName();
				if ( rankName != null && !rankName.isEmpty() ) {
					userSighted.setRankName(rankName.toUpperCase().replaceAll(" ", "_"));
				}
			}
		}
		
		Set<String> usernamesSighted = new HashSet<String>();
		Map<String, RsUserName> namesToRsUserNames = new HashMap<String, RsUserName>();
		Map<String, RsClanMember> namesToClanMembers = new HashMap<String, RsClanMember>();
		
		if ( usersSighted != null && !usersSighted.isEmpty() ) {
			
			for ( UserSightingDTO userSighted : usersSighted ) {
				if ( userSighted != null ) {
					String username = userSighted.getUsername();
					if ( username != null && !username.isEmpty() ) {
						usernamesSighted.add(username.toUpperCase());
					}
				}
			}
			
			Set<Long> rsClanMemberId = new HashSet<Long>();
			Map<Long, RsUserName> clanMemberIdsToRsUserNames = new HashMap<Long, RsUserName>();
			if ( usernamesSighted != null && !usernamesSighted.isEmpty() ) {
				List<RsUserName> rsUserNames = rsUserNameRepository.findByUserNameInIgnoreCase(usernamesSighted);
				if ( rsUserNames != null && !rsUserNames.isEmpty() ) {
					for ( RsUserName rsUserName : rsUserNames ) {
						String username = rsUserName.getUserName();
						if ( username != null && !username.isEmpty() ) {
							namesToRsUserNames.put(username.toUpperCase(), rsUserName);
							Long clanMemberId = rsUserName.getClanMemberId();
							if ( clanMemberId != null ) {
								rsClanMemberId.add(clanMemberId);
								clanMemberIdsToRsUserNames.put(clanMemberId, rsUserName);
							}
						}
					}
					
					if ( rsClanMemberId != null && !rsClanMemberId.isEmpty() ) {
						List<RsClanMember> matchingClanMembers = rsClanMemberRespository.findByClanMemberIdIn(rsClanMemberId);
						if ( matchingClanMembers != null && !matchingClanMembers.isEmpty() ) {
							for ( RsClanMember matchingClanMember : matchingClanMembers ) {
								Long clanMemberId = matchingClanMember.getClanMemberId();
								if ( clanMemberId != null ) {
									RsUserName rsUserName = clanMemberIdsToRsUserNames.get(clanMemberId);
									if ( rsUserName != null ) {
										String username = rsUserName.getUserName();
										if ( username != null && !username.isEmpty() ) {
											namesToClanMembers.put(rsUserName.getUserName().toUpperCase(), matchingClanMember);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		List<SightingResponseDTO> sightingResponseDtos = new ArrayList<SightingResponseDTO>();
		if ( usernamesSighted != null && !usernamesSighted.isEmpty() ) {
			
			List<DirClanRank> clanRanks = dirClanRankRepository.findAll();
			Set<String> validClanRankNames = new HashSet<String>();
			if ( clanRanks != null && !clanRanks.isEmpty() ) {
				for ( DirClanRank clanRank : clanRanks ) {
					String rankName = clanRank.getRankName();
					if ( rankName != null && !rankName.isEmpty() ) {
						validClanRankNames.add(rankName);
					}
				}
			}

			List<RsClanMember> clanMembersToUpdate = new ArrayList<RsClanMember>();
			List<RsUserName> rsUserNamesToAdd = new ArrayList<RsUserName>();
			Map<String, String> toAddUserNameRanks = new HashMap<String, String>();
			List<UserSighting> userSightingsToAdd = new ArrayList<UserSighting>();
			for ( UserSightingDTO userSighted : usersSighted ) {
				if ( userSighted != null ) {
					String username = userSighted.getUsername();
					String clanRank = userSighted.getRankName();
					if ( clanRank != null && !clanRank.isEmpty() && !validClanRankNames.contains(clanRank) ) {
						sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "Error, provided clanRank was invalid!"));
					} else if ( username == null || username.isEmpty() ) {
						sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "Error, provided username was invalid!"));
					} else {
						RsUserName rsUserName = namesToRsUserNames.get(username.toUpperCase());
						RsClanMember rsClanMember = namesToClanMembers.get(username.toUpperCase());
						if ( rsUserName != null && rsClanMember != null ) { // User already exists, just update
							boolean updatedRank = false;
							String existingClanRank = rsClanMember.getRankName();
							if ( clanRank != null && !clanRank.isEmpty() && !existingClanRank.equals(clanRank) ) {
								rsClanMember.setRankName(clanRank);
								clanMembersToUpdate.add(rsClanMember);
								updatedRank = true;
							}
							Long userNameId = rsUserName.getUserNameId();
							userSightingsToAdd.add(new UserSighting(clanRank, userNameId));
							if ( updatedRank ) {
								if ( createSightingAndUser ) {
									sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "User already existed, updated rank and activity."));
								} else {
									sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "User already existed, updated rank."));
								}
							} else {
								if ( createSightingAndUser ) {
									sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "User already existed, updated activity."));
								} else {
									sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "No change to user."));
								}
							}
						} else if ( rsUserName != null ) {
							Long userNameId = rsUserName.getUserNameId();
							userSightingsToAdd.add(new UserSighting(clanRank, userNameId));
							sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "User was recently added and is currently pending a name change check."));
						} else {
							rsUserNamesToAdd.add(new RsUserName(username));
							toAddUserNameRanks.put(username, clanRank);
							if ( createSightingAndUser ) {
								sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "User not found, added as new user, queued to check for name changes."));
							} else {
								sightingResponseDtos.add(new SightingResponseDTO(username, clanRank, "User not found, please add a sighting before changing their rank!"));
							}
						}
					}
				}
			}
			
			if ( clanMembersToUpdate != null && !clanMembersToUpdate.isEmpty() ) {
				rsClanMemberRespository.save(clanMembersToUpdate);
			}
			
			if ( createSightingAndUser && rsUserNamesToAdd != null && !rsUserNamesToAdd.isEmpty() ) {
				rsUserNamesToAdd = rsUserNameRepository.save(rsUserNamesToAdd);
				if ( rsUserNamesToAdd != null && !rsUserNamesToAdd.isEmpty() ) {
					for ( RsUserName rsUserName : rsUserNamesToAdd ) {
						Long userNameId = rsUserName.getUserNameId();
						String username = rsUserName.getUserName();
						String rankName = toAddUserNameRanks.get(username);
						userSightingsToAdd.add(new UserSighting(rankName, userNameId));
					}
				}
			}
			
			if ( createSightingAndUser && userSightingsToAdd != null && !userSightingsToAdd.isEmpty() ) {
				userSightingsToAdd = userSightingRepository.save(userSightingsToAdd);
			}
		}
		
		return sightingResponseDtos;
	}
	
}
