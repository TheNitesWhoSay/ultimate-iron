package org.iron.ultimate.jpa.dao.repository;

import java.util.List;
import java.util.Set;

import org.iron.ultimate.jpa.dao.model.RsUserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RsUserNameRepository extends JpaRepository<RsUserName, Long> {

	List<RsUserName> findByUserNameInIgnoreCase(Set<String> usernames);
	
	RsUserName findByUserNameEqualsIgnoreCase(String username);
	
	@Query(value = "SELECT run.USER_NAME_ID, run.CLAN_MEMBER_ID, run.USER_NAME FROM rs_user_name run WHERE run.CLAN_MEMBER_ID IS NOT NULL ORDER BY run.USER_NAME ASC", nativeQuery = true)
	List<RsUserName> getAllNames();
	
}
