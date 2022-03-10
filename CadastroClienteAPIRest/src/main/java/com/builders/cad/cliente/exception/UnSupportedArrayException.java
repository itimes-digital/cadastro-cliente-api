package com.builders.cad.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnSupportedArrayException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6640433071922259671L;
	
	public UnSupportedArrayException(String exception) {
		super(exception);
	}

}
