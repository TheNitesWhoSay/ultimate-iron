package org.iron.ultimate.jpa.dao.repository;

import java.math.BigInteger;
import java.util.Set;

import org.iron.ultimate.jpa.dao.model.UserSighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserSightingRepository extends JpaRepository<UserSighting, Long> {

	@Modifying
	@Query(value = "DELETE FROM user_sighting WHERE USER_NAME_ID = :userNameId", nativeQuery = true)
	void deleteByUserNameId(@Param("userNameId") Long userNameId);
	
	@Query(value = "SELECT us.USER_NAME_ID FROM user_sighting us WHERE us.USER_NAME_ID IN ( :userNameIds ) ORDER BY us.SIGHTING_TS DESC LIMIT 1", nativeQuery = true)
	BigInteger getMostRecentlySeenUserIdAmong(@Param("userNameIds") Set<BigInteger> userNameIds);
	
}
