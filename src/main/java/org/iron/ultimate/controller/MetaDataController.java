package org.iron.ultimate.controller;

import java.util.List;
import java.util.Map;

import org.iron.ultimate.model.SkillDTO;
import org.iron.ultimate.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/meta")
@Api(value = "Meta Data Controller")
public class MetaDataController {

	@Autowired
	private MetaDataService metaDataService;
	
	@RequestMapping(value = "/account/types", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get a list of account types and their descriptions", response = Map.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", responseContainer = "Map", response = Map.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Map<String, String> getAccountTypes() {
		return metaDataService.getAccountTypes();
	}
	
	@RequestMapping(value = "/ranks", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get a list of clan ranks and their descriptions", response = Map.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = Map.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Map<String, String> getClanRanks() {
		return metaDataService.getClanRanks();
	}
	
	@RequestMapping(value = "/skills", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get a list of skills", responseContainer = "List", response = SkillDTO.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", responseContainer = "List", response = SkillDTO.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public List<SkillDTO> getListOfSkills() {
		return metaDataService.getListOfSkills();
	}
	
	@RequestMapping(value = "/hiscore/categories", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get a list of hiscore categories", response = List.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = List.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public List<String> getHiscoreCategories() {
		return metaDataService.getHiscoreCategories();
	}
	
}
