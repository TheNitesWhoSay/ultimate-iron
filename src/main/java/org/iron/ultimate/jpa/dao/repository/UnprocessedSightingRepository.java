package org.iron.ultimate.jpa.dao.repository;

import org.iron.ultimate.jpa.dao.model.UnprocessedSighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UnprocessedSightingRepository extends JpaRepository<UnprocessedSighting, Long> {

	@Query(value = "SELECT run.USER_NAME_ID, run.USER_NAME, us.SIGHTING_ID, us.SIGHTING_TS, us.RANK_NAME"
			+ " FROM rs_user_name run LEFT JOIN user_sighting us ON run.USER_NAME_ID = us.USER_NAME_ID"
			+ " WHERE run.CLAN_MEMBER_ID IS NULL ORDER BY us.SIGHTING_TS ASC LIMIT 1", nativeQuery = true)
	UnprocessedSighting getUnprocessedSighting();
	
}
