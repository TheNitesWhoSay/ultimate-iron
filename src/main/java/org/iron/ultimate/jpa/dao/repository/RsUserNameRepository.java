package org.iron.ultimate.jpa.dao.repository;

import java.util.List;
import java.util.Set;

import org.iron.ultimate.jpa.dao.model.RsUserName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RsUserNameRepository extends JpaRepository<RsUserName, Long> {

	List<RsUserName> findByUserNameInIgnoreCase(Set<String> usernames);
	
}
