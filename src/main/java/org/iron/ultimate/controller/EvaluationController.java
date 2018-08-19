package org.iron.ultimate.controller;

import org.iron.ultimate.exception.UserNotFoundException;
import org.iron.ultimate.model.UserEvaluationDTO;
import org.iron.ultimate.service.EvaluationService;
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
@RequestMapping("/user/evaluation")
@Api(value = "User Evaluation Controller")
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaluationService;
	
	@RequestMapping(value = "/process/pending", method = RequestMethod.GET)
	@ApiOperation(value = "Process a user pending a name-change check", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = String.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public String processPendingUser() {
		return evaluationService.processPendingUser();
	}

	@RequestMapping(value = "/clan/member", method = RequestMethod.GET)
	@ApiOperation(value = "Check if a clan member is eligible for rank-up", response = UserEvaluationDTO.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = UserEvaluationDTO.class),
		@ApiResponse(code = 204, message = "User Not On Hiscores"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public UserEvaluationDTO checkClanMember(@RequestParam String username) throws UserNotFoundException {
		return evaluationService.checkClanMember(username);
	}
	
}
