package com.supplychain.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice    // to handle exceptions globally
public class GlobalExceptionHandler 
{
	//ResourceNotFoundException (custom exception)
	
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request)
	{
		ErrorDetails errorDetails=
				new ErrorDetails(new Date(), "data not found",ex.getMessage());
		return new ResponseEntity<>(errorDetails,new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	//Global Exception
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<?> handleGlobalException(ResourceNotFoundException ex,WebRequest request)
	{
		ErrorDetails errorDetails=
				new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//InputException (Validation)
	
	@ExceptionHandler(value = {InputException.class})
	public ResponseEntity<ErrorDetails> handleAnyRequest(InputException ex,WebRequest request)
	{
		ErrorDetails errorDetails=
				new ErrorDetails(new Date(), "wrong input",ex.getMessage());
		return new ResponseEntity<>(errorDetails,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}

	//MethodArgumentNotValidException (Validation)
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationExceptionHandling(MethodArgumentNotValidException exception)
	{
		ErrorDetails errorDetails=
				new ErrorDetails(new Date(), "Validation Error",exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
