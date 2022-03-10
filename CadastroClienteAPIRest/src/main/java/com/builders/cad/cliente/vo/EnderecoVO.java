package com.builders.cad.cliente.vo;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.enums.TipoEnderecoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoVO extends ResourceSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 951418213116439228L;

	@JsonProperty("id")
	private Long key;
	
	private Long idCliente;

	private String logradouro;

	private Integer numLogradouro;

	private String numCep;

	private String caixaPostal;

	private String cidade;

	private String estado;

	private String pais;
	
	private TipoEnderecoEnum enderecoEnum;
	
	private StatusEnum statusEnum;

}
