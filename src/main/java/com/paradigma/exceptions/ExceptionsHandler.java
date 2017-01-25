package com.paradigma.exceptions;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;


/**
 * This class handles all the exceptions occurred in the requests
 * @author jmedinilla
 */
@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

	
	
	
	/**
	 * Handle DataAccessResourceFailureException exception.
	 *
	 * @param e - the exception
	 * @return ResponseEntity with INTERNAL_SERVER_ERROR code
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error("DataAccessResourceFailureException hander: " + e);
		String jsonError = "{\"error\":\"Could not process the operation due an unreadable body.\"}";
		
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonError);
    }
	
	/**
	 * Handle DataAccessResourceFailureException exception.
	 *
	 * @param e - the exception
	 * @return ResponseEntity with INTERNAL_SERVER_ERROR code
	 */
	@ExceptionHandler(DataAccessResourceFailureException.class)
	@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
    public ResponseEntity<String> handleMongoTimeoutException(DataAccessResourceFailureException e) {
		log.error("DataAccessResourceFailureException hander: " + e);
		String jsonError = "{\"error\":\"Could not process the operation due to a data base timeout exception.\"}";
		
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(jsonError);
    }
	
	/**
	 * Handle general exception.
	 *
	 * @param e - the exception
	 * @return ResponseEntity with INTERNAL_SERVER_ERROR code
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(RuntimeException e) {
		log.error("Runtime exception hander: " + e);
		String jsonError = "{\"error\":\"Could not process the operation due to an internal error.\"}";
		
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonError);
    }
	
}
