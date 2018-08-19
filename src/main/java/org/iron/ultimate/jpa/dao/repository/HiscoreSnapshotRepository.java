package org.iron.ultimate.jpa.dao.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import org.iron.ultimate.jpa.dao.model.HiscoreSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HiscoreSnapshotRepository extends JpaRepository<HiscoreSnapshot, Long> {

	@Query(value = "SELECT CURRENT_TIMESTAMP FROM DUAL", nativeQuery = true)
	Date getCurrentTimestamp();
	
	@Query(value =
			" SELECT other.USER_NAME_ID AS MATCHED_USER_NAME_ID FROM hiscore_snapshot hs" + 
			" INNER JOIN (" + 
			" 	SELECT hs.HISCORE_SNAPSHOT_ID, hs.USER_NAME_ID FROM hiscore_snapshot hs" + 
			" 	INNER JOIN hiscore_snapshot_skill hss ON hs.HISCORE_SNAPSHOT_ID = hss.HISCORE_SNAPSHOT_ID" + 
			" 	WHERE hs.USER_NAME_ID = :userNameId ORDER BY hs.SNAPSHOT_TS DESC LIMIT 1" + 
			" ) hs_latest ON (hs.HISCORE_SNAPSHOT_ID = hs_latest.HISCORE_SNAPSHOT_ID AND hs.USER_NAME_ID = hs_latest.USER_NAME_ID)" + 
			" INNER JOIN hiscore_snapshot_skill hss ON (hs.HISCORE_SNAPSHOT_ID = hss.HISCORE_SNAPSHOT_ID)" + 
			" INNER JOIN (" + 
			" 	SELECT hs.USER_NAME_ID, hss.SKILL_ID, hss.SKILL_EXPERIENCE FROM hiscore_snapshot hs" + 
			" 	INNER JOIN (" + 
			" 		SELECT DISTINCT hs.HISCORE_SNAPSHOT_ID AS HISCORE_SNAPSHOT_ID, hs.USER_NAME_ID FROM hiscore_snapshot hs" + 
			" 		INNER JOIN (" + 
			" 			SELECT hs.USER_NAME_ID, MAX(hs.SNAPSHOT_TS) AS SNAPSHOT_TS FROM hiscore_snapshot hs" + 
			" 			INNER JOIN hiscore_snapshot_skill hss ON hs.HISCORE_SNAPSHOT_ID = hss.HISCORE_SNAPSHOT_ID" + 
			" 			INNER JOIN rs_user_name run ON hs.USER_NAME_ID = run.USER_NAME_ID" +
			"			WHERE run.CLAN_MEMBER_ID IS NOT NULL" +
			" 			GROUP BY hs.USER_NAME_ID" + 
			" 		) hs_max ON (hs.USER_NAME_ID = hs_max.USER_NAME_ID AND hs.SNAPSHOT_TS = hs_max.SNAPSHOT_TS) GROUP BY (hs.USER_NAME_ID)" + 
			" 	) hs_latest ON (hs.HISCORE_SNAPSHOT_ID = hs_latest.HISCORE_SNAPSHOT_ID AND hs.USER_NAME_ID = hs_latest.USER_NAME_ID)" + 
			" 	INNER JOIN hiscore_snapshot_skill hss ON (hs.HISCORE_SNAPSHOT_ID = hss.HISCORE_SNAPSHOT_ID)" + 
			" ) other ON (hss.SKILL_ID = other.SKILL_ID AND hs.USER_NAME_ID <> other.USER_NAME_ID AND hss.SKILL_EXPERIENCE = other.SKILL_EXPERIENCE)" + 
			" WHERE hss.SKILL_EXPERIENCE > 0 GROUP BY hs.USER_NAME_ID, other.USER_NAME_ID HAVING COUNT(*) >= :minMatchingSkills ",
			nativeQuery = true)
	Set<BigInteger> getMatchingUserNameIds(@Param("userNameId") Long userNameId, @Param("minMatchingSkills") Integer minMatchingSkills);
	
}
