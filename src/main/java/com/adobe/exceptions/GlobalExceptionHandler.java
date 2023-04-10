package com.adobe.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> taskException(UserException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorDetails> personException(PostException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorDetails> loginExceptionhandler(LoginException ee, WebRequest req){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), ee.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	} 
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> NoHandlerFoundException(NoHandlerFoundException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		ErrorDetails err = new ErrorDetails(LocalDateTime.now(), "Validation Error",
				me.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> ExceptionHandler(Exception e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
}
