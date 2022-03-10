package com.builders.cad.cliente.vo;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.springframework.hateoas.ResourceSupport;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.enums.TipoContatoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContatoVO extends ResourceSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 951418213116439228L;

	@JsonProperty("id")
	private Long key;
	
	private Long idCliente;

	private String numTelefone;

	@Email(message = "E-mail inválido!")
	private String email;
	
	private TipoContatoEnum contatoEnum;
	
	private StatusEnum statusEnum;

}
