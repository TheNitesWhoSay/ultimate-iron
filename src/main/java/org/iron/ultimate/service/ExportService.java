package org.iron.ultimate.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.iron.ultimate.jpa.dao.model.UserSummary;
import org.iron.ultimate.jpa.dao.repository.UserSummaryRepository;
import org.iron.ultimate.model.UserSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportService {

	@Autowired
	private UserSummaryRepository userSummaryRepository;

	public String dataExport() {
		List<UserSummary> userSummaries = userSummaryRepository.getUserSummary();
		Map<Long, UserSummaryDTO> clanMemberIdToUserSummary = new LinkedHashMap<Long, UserSummaryDTO>();
		for ( UserSummary userSummary : userSummaries ) {
			Long clanMemberId = userSummary.getPk().getClanMemberId();
			UserSummaryDTO userSummaryDto = clanMemberIdToUserSummary.get(clanMemberId);
			String pastUsername = userSummary.getPastUsername();
			if ( userSummaryDto == null ) {
				userSummaryDto = new UserSummaryDTO(userSummary.getUsername(), userSummary.getJoinDate());
				clanMemberIdToUserSummary.put(clanMemberId, userSummaryDto);
			}
			if ( pastUsername != null ) {
				userSummaryDto.getPastUsernames().add(pastUsername);
			}
		}
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Set<Long> clanMemberIds = clanMemberIdToUserSummary.keySet();
		String exportData = "";
		for ( Long clanMemberId : clanMemberIds ) {
			UserSummaryDTO userSummaryDto = clanMemberIdToUserSummary.get(clanMemberId);
			List<String> pastUsernames = userSummaryDto.getPastUsernames();
			exportData += userSummaryDto.getUsername() + "," + dateFormatter.format(userSummaryDto.getJoinDate());
			for ( String pastUsername : pastUsernames ) {
				exportData += "," + pastUsername;
			}
			exportData += "\n";
		}
		return exportData;
	}
	
}
