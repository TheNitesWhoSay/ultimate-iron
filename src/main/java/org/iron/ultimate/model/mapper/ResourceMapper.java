package org.iron.ultimate.model.mapper;

import org.iron.ultimate.jpa.dao.model.DirSkill;
import org.iron.ultimate.model.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;

import ma.glasnost.orika.MapperFacade;

public class ResourceMapper {

	@Autowired
	private MapperFacade mapperFacade;
	
	public SkillDTO convertToSkillDTO(DirSkill dirSkill) {
		return mapperFacade.map(dirSkill, SkillDTO.class);
	}
	
}
