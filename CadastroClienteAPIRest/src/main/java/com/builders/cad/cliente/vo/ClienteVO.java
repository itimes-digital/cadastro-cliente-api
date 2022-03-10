package com.builders.cad.cliente.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.ResourceSupport;

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
public class ClienteVO extends ResourceSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 951418213116439228L;

	@JsonProperty("id")
	private Long key;

	@NotBlank
	private String primeiroNome;

	@NotBlank
	private String segundoNome;

	private String nickname;

	@NotBlank
	private String tipoPessoa;

	@CPF(message = "CPF inválido!")
	private String cpf;
	
	@CNPJ(message = "CNPJ inválido!")
	private String cnpj;

	@NotBlank
	private String dataNascimento;
	
	public Short getIdade() {
		
		LocalDate dateTimeAtual = LocalDate.now();
		
		LocalDate dateTimeDtNascimento = LocalDate.now();
		
		if(dataNascimento != null) {
			dateTimeDtNascimento = LocalDate.parse(dataNascimento);
		}
		
		return Integer.valueOf(dateTimeAtual.getYear() - dateTimeDtNascimento.getYear()).shortValue();
	}
	
	private ContatoVO contato;
	
	private EnderecoVO endereco;
	
	public String getCpf() {
		
		if(cpf != null) {
			cpf = cpf.replaceAll("\\.", "").replaceAll("\\-", "");
		}
		return cpf;
	}
	
	public String getCnpj() {
		
		if(cnpj != null) {
			cnpj = cnpj.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("\\/", "");
		}
		return cnpj;
	}
	
	public ClienteVO() {
		
	}
	
	public ClienteVO(Long key, String primeiroNome, String segundoNome,
					String nickname, String tipoPessoa, String cpf, 
					String cnpj, String dataNascimento) {
		
		this.key = key;
		this.primeiroNome = primeiroNome;
		this.segundoNome = segundoNome;
		this.nickname = nickname;
		this.tipoPessoa = tipoPessoa;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataNascimento = dataNascimento;
		
	}

}
