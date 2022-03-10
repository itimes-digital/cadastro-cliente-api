package com.builders.cad.cliente.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.builders.cad.cliente.exception.ExceptionResponse;
import com.builders.cad.cliente.exception.MethodNotAllowedException;
import com.builders.cad.cliente.exception.ResourceNoContentException;
import com.builders.cad.cliente.exception.ResourceConflitException;
import com.builders.cad.cliente.exception.UnAuthorizedException;
import com.builders.cad.cliente.exception.UnSupportedArrayException;
import com.builders.cad.cliente.exception.UnSupportedCharacterException;
import com.builders.cad.cliente.exception.UnSupportedMediaTypeException;
import com.builders.cad.cliente.exception.UnSupportedNumberException;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({UnSupportedNumberException.class, 
						UnSupportedArrayException.class,
						UnSupportedCharacterException.class})
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ResourceConflitException.class})
	public final ResponseEntity<ExceptionResponse> handleResourceConflitException(Exception ex, WebRequest request){
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({ResourceNoContentException.class})
	public final ResponseEntity<ExceptionResponse> handleResourceNoContentException(Exception ex, WebRequest request){
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NO_CONTENT);
	}

	
	@ExceptionHandler(UnSupportedMediaTypeException.class)
	public final ResponseEntity<ExceptionResponse> handleUnsupportedMediaTypeException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler(MethodNotAllowedException.class)
	public final ResponseEntity<ExceptionResponse> handleMethodNotAllowedException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@ExceptionHandler(UnAuthorizedException.class)
	public final ResponseEntity<ExceptionResponse> handleUnauthorizedException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
}
