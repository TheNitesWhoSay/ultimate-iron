package org.iron.ultimate.jpa.dao.repository;

import org.iron.ultimate.jpa.dao.model.DirClanRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DirClanRankRepository extends JpaRepository<DirClanRank, String> {

	@Query(value =
			" SELECT dcr.RANK_NAME, dcr.RANK_INDEX, dcr.DISPLAY_NAME, dcr.IS_STAFF, dcr.MINIMUM_TOTAL, dcr.MINIMUM_CLAN_DAYS" +
			" FROM dir_clan_rank dcr" + 
			" WHERE dcr.MINIMUM_TOTAL <= :totalLevel AND DATEDIFF(CURRENT_TIMESTAMP, (" + 
			" 	SELECT MIN(us.SIGHTING_TS) FROM user_sighting us" + 
			" 	INNER JOIN (" + 
			" 		SELECT run.USER_NAME_ID FROM rs_user_name run WHERE run.CLAN_MEMBER_ID = :clanMemberId " + 
			" 		UNION ALL" + 
			" 		SELECT aun.USER_NAME_ID FROM ar_user_name aun WHERE aun.CLAN_MEMBER_ID = :clanMemberId " + 
			" 	) un ON (us.USER_NAME_ID = un.USER_NAME_ID)" + 
			" )) >= dcr.MINIMUM_CLAN_DAYS AND dcr.IS_STAFF = 0" + 
			" ORDER BY dcr.RANK_INDEX ASC LIMIT 1", nativeQuery = true)
	DirClanRank findHighestEligibleRank(@Param("totalLevel") Long totalLevel, @Param("clanMemberId") Long clanMemberId);
	
}
