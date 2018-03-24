package org.iron.ultimate.jpa.dao.repository;

import java.util.List;

import org.iron.ultimate.jpa.dao.model.DirSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DirSkillRepository extends JpaRepository<DirSkill, Long> {

	List<DirSkill> findAllByOrderByLiteIndexAsc();
	
}
