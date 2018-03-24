package org.iron.ultimate.jpa.dao.repository;

import java.util.List;
import java.util.Set;

import org.iron.ultimate.jpa.dao.model.RsClanMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RsClanMemberRepository extends JpaRepository<RsClanMember, Long> {

	List<RsClanMember> findByClanMemberIdIn(Set<Long> clanMemberId);
	
}
