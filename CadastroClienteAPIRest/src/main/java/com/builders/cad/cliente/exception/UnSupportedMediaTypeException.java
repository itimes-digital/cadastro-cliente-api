package com.builders.cad.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnSupportedMediaTypeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1081844982358739040L;

	public UnSupportedMediaTypeException(String exception) {
		super(exception);
	}

}
