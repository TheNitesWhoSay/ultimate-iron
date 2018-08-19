package org.iron.ultimate.jpa.dao.repository;

import java.util.List;

import org.iron.ultimate.jpa.dao.model.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserSummaryRepository extends JpaRepository<UserSummary, Long> {

	@Query(value =
			" SELECT rcm.CLAN_MEMBER_ID, IFNULL(aun.USER_NAME_ID, run.USER_NAME_ID) AS USER_NAME_ID," +
			" 	join_date.JOIN_DATE, run.USER_NAME, aun.USER_NAME AS PAST_USER_NAME" +
			" FROM rs_clan_member rcm" + 
			" INNER JOIN (" + 
			" 	SELECT first_seen.CLAN_MEMBER_ID, MIN(first_seen.SIGHTING_TS) AS JOIN_DATE FROM (" + 
			" 		SELECT run.CLAN_MEMBER_ID, MIN(us.SIGHTING_TS) AS SIGHTING_TS FROM rs_user_name run" + 
			" 		INNER JOIN user_sighting us ON run.USER_NAME_ID = us.USER_NAME_ID" + 
			" 		WHERE run.CLAN_MEMBER_ID IS NOT NULL" + 
			" 		GROUP BY run.CLAN_MEMBER_ID" + 
			" 	UNION" + 
			" 		SELECT aun.CLAN_MEMBER_ID, MIN(us.SIGHTING_TS) AS SIGHTING_TS FROM ar_user_name aun" + 
			" 		INNER JOIN user_sighting us ON aun.USER_NAME_ID = us.USER_NAME_ID" + 
			" 		WHERE aun.CLAN_MEMBER_ID IS NOT NULL" + 
			" 		GROUP BY aun.CLAN_MEMBER_ID" + 
			" 	) first_seen GROUP BY first_seen.CLAN_MEMBER_ID" + 
			" ) join_date ON (rcm.CLAN_MEMBER_ID = join_date.CLAN_MEMBER_ID)" + 
			" INNER JOIN rs_user_name run ON (rcm.CLAN_MEMBER_ID = run.CLAN_MEMBER_ID)" + 
			" LEFT JOIN ar_user_name aun ON (rcm.CLAN_MEMBER_ID = aun.CLAN_MEMBER_ID)" + 
			" ORDER BY join_date.JOIN_DATE, rcm.CLAN_MEMBER_ID ASC", nativeQuery = true)
	List<UserSummary> getUserSummary();
	
}
