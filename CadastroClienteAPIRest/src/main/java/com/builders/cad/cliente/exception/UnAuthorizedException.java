package com.builders.cad.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6295937998824934771L;

	public UnAuthorizedException(String exception) {
		super(exception);
	}

}
