package org.iron.ultimate.controller;

import org.iron.ultimate.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/data/export")
@Api(value = "Data Export Controller")
public class ExportController {
	
	@Autowired
	private ExportService exportService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/csv")
	@ApiOperation(value = "Export summary data", response = String.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = String.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public String dataExport() {
		return exportService.dataExport();
	}
	
}
