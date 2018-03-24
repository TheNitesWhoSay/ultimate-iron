package org.iron.ultimate.controller;

import java.util.List;

import org.iron.ultimate.model.SightingResponseDTO;
import org.iron.ultimate.model.UserSightingDTO;
import org.iron.ultimate.service.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sighting")
@Api(value = "Users Sighted Controller")
public class SightingController {

	@Autowired
	private SightingService sightingService;
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Submit a list of sighted users", responseContainer = "List", response = SightingResponseDTO.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", responseContainer = "List", response = SightingResponseDTO.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public List<SightingResponseDTO> recordSightedUsers(@RequestBody List<UserSightingDTO> sightedUserNames) {
		return sightingService.sightUsers(sightedUserNames);
	}
}
