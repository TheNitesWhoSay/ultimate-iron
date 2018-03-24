package org.iron.ultimate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database hiscore lite indexes were not contiguous")
public class NonContiguousLiteIndexException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NonContiguousLiteIndexException() {
		super("Database hiscore lite indexes were not contiguous");
	}
	
}
