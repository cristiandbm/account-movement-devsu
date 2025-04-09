package com.devsu.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handlerNotFound(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(BalanceException.class)
	public ResponseEntity<String> handlerException(BalanceException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ex.getMessage());
	}
}
