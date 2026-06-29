package com.example.ems.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//exception handling without writing try-catch blocks
@ControllerAdvice
public class GlobalExceptionHandler {

	// This method handles only EmployeeNotFoundException
	
	/*
	 * @ExceptionHandler(EmployeeNotFoundException.class) public
	 * ResponseEntity<String> exceptionHandler(EmployeeNotFoundException ex) {
	 * return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); }
	 */
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(EmployeeNotFoundException ex) {
		
		//AllArgconstructor
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage());
		
		/*
		 * //noArgConstrutor ErrorResponse errorResponse = new ErrorResponse();
		 * errorResponse.setHttpStatus();
		 */

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
}

/*
 * without controladvise Api will return { "timestamp":
 * "2026-06-29T11:49:27.933Z", "status": 500, "error": "Internal Server Error",
 * "path": "/api/5" }
 */

/*
 * with contoller Advice Api returns Employee not found with id : 5
 * with error response 
 * {
  "timestamp": "2026-06-29T15:40:10",
  "status": 404,
  "error": "Not Found",
  "message": "Employee not found with id: 10"
}
 */