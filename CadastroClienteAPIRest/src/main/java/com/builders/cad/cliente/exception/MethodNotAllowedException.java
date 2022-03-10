package com.builders.cad.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6295937998824934771L;

	public MethodNotAllowedException(String exception) {
		super(exception);
	}

}
