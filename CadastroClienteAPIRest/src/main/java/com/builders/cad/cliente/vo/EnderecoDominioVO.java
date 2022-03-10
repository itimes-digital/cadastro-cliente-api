package com.builders.cad.cliente.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDominioVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 951418213116439228L;

	private Long idEnderecoDominio;

	private String nomeDominio;

	private String descDominio;

}
