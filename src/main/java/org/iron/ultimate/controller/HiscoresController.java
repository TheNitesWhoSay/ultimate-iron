package org.iron.ultimate.controller;

import java.util.List;
import java.util.Map;

import org.iron.ultimate.exception.UserNotFoundException;
import org.iron.ultimate.exception.ValidationException;
import org.iron.ultimate.model.HiscoreEntry;
import org.iron.ultimate.model.PrettyUserProfileDTO;
import org.iron.ultimate.model.UserProfileDTO;
import org.iron.ultimate.model.enums.HiscoreCategory;
import org.iron.ultimate.service.HiscoreService;
import org.iron.ultimate.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/hiscores")
@Api(value = "Hiscores Controller")
public class HiscoresController {

	@Autowired
	private MetaDataService metaDataService;
	
	@Autowired
	private HiscoreService hiscoreService;

	@RequestMapping(value = "/raw/", method = RequestMethod.GET)
	@ApiOperation(value = "Get raw user hiscores", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = String.class),
		@ApiResponse(code = 204, message = "User Not On Hiscores"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public String getRawHiscores(@RequestParam String category, @RequestParam String user) throws UserNotFoundException {
		HiscoreCategory hiscoreCategory = metaDataService.getHiscoreCategory(category);
		if ( user == null || user.isEmpty() ) {
			throw new ValidationException("No user provided!");
		}
		return hiscoreService.getRawHiscores(hiscoreCategory, user);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get user hiscores", responseContainer = "List", response = HiscoreEntry.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", responseContainer = "List", response = HiscoreEntry.class),
		@ApiResponse(code = 204, message = "User Not On Hiscores"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public List<HiscoreEntry> getHiscores(@RequestParam String category, @RequestParam String user) throws UserNotFoundException {
		HiscoreCategory hiscoreCategory = metaDataService.getHiscoreCategory(category);
		if ( user == null || user.isEmpty() ) {
			throw new ValidationException("No user provided!");
		}
		return hiscoreService.getHiscores(hiscoreCategory, user);
	}

	@RequestMapping(value = "/pretty", method = RequestMethod.GET)
	@ApiOperation(value = "Get pretty user hiscores", response = Map.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = Map.class),
		@ApiResponse(code = 204, message = "User Not On Hiscores"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Map<String, Map<String, Long>> getPrettyHiscores(@RequestParam String category, @RequestParam String user) throws UserNotFoundException {
		HiscoreCategory hiscoreCategory = metaDataService.getHiscoreCategory(category);
		if ( user == null || user.isEmpty() ) {
			throw new ValidationException("No user provided!");
		}
		return hiscoreService.getPrettyHiscores(hiscoreCategory, user);
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@ApiOperation(value = "Get user profile", response = UserProfileDTO.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = UserProfileDTO.class),
		@ApiResponse(code = 204, message = "User Not On Hiscores"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public UserProfileDTO getUserProfile(@RequestParam String user) throws UserNotFoundException {
		if ( user == null || user.isEmpty() ) {
			throw new ValidationException("No user provided!");
		}
		return hiscoreService.getUserProfile(user);
	}

	@RequestMapping(value = "/profile/pretty", method = RequestMethod.GET)
	@ApiOperation(value = "Get pretty user profile", response = PrettyUserProfileDTO.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = PrettyUserProfileDTO.class),
		@ApiResponse(code = 204, message = "User Not On Hiscores"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public PrettyUserProfileDTO getPrettyUserProfile(@RequestParam String user) throws UserNotFoundException {
		if ( user == null || user.isEmpty() ) {
			throw new ValidationException("No user provided!");
		}
		return hiscoreService.getPrettyUserProfile(user);
	}

	
}
