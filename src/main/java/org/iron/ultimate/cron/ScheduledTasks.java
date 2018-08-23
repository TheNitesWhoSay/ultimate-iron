package org.iron.ultimate.cron;

import java.util.List;

import org.iron.ultimate.exception.UserNotFoundException;
import org.iron.ultimate.jpa.dao.model.RsUserName;
import org.iron.ultimate.jpa.dao.repository.RsUserNameRepository;
import org.iron.ultimate.model.UserEvaluationDTO;
import org.iron.ultimate.service.DiscordService;
import org.iron.ultimate.service.EvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private EvaluationService evaluationService;
	
	@Autowired
	private RsUserNameRepository rsUserNameRepository;
	
	@Autowired
	private DiscordService discordService;
	
	//@Scheduled(fixedRate = 60000) // Every minute
	public void handlePendingUser() {
		String result = evaluationService.processPendingUser();
		if ( result != null ) {
			log.info(result);
		}
	}
	
	@Scheduled(cron = "0 57 10 * * *") // Every day at 10:57AM
	public void recheckAllUsers() {
		log.info("Starting recheckAllUsers()");
		List<RsUserName> allNames = rsUserNameRepository.getAllNames();
		int totalNames = allNames.size();
		for ( int i=0; i<totalNames; i++ ) {
			RsUserName rsUserName = allNames.get(i);
			try {
				UserEvaluationDTO userEval = evaluationService.checkClanMember(rsUserName);
				String message = "[" + rsUserName.getUserName() + "]: " + ((userEval != null && userEval.getMessage() != null) ? userEval.getMessage() : "null");
				log.info(message);
			} catch (UserNotFoundException e1) {
				String message = "Clan member " + rsUserName.getUserName() + " could not be found on hiscores!";
				log.info(message);
				discordService.sendMessage(message);
			}
		}
	}
	
}
