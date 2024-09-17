package br.com.erudio.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.exceptions.BadRequestException;
import br.com.erudio.exceptions.ExceptionResponse;
import br.com.erudio.exceptions.UnsupportedMathOperationException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(
			Exception ex, WebRequest request) {
		return new ResponseEntity<>(createExceptionResponse(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({BadRequestException.class, UnsupportedMathOperationException.class})
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(
			Exception ex, WebRequest request) {
		return new ResponseEntity<>(createExceptionResponse(ex, request), HttpStatus.BAD_REQUEST);
	}

	private ExceptionResponse createExceptionResponse(
			Exception ex, WebRequest request) {
		return new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false)
		);
	}
	
}
