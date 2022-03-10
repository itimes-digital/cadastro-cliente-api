package com.builders.cad.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6640433071922259671L;
	
	public ResourceConflitException(String exception) {
		super(exception);
	}

}
