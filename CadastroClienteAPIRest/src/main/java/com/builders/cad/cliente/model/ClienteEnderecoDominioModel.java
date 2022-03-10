package com.builders.cad.cliente.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "TAB_CLIENTE_ENDERECO_DOMINIO")
public class ClienteEnderecoDominioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "ID_CLIENTE_ENDERECO_DOMINIO", nullable = false )
	private Long idClienteEnderecoDominio;
	
	@Column( name = "NM_DOMINIO", nullable = false, length = 50 )
	private String nomeDominio;
	
	@Column( name = "DESC_DOMINIO", nullable = false, length = 100 )
	private String descDominio;
	
	@Column( name = "DT_ATUALIZACAO", nullable = false )
	private LocalDateTime dataAtualizacao;
	
	@Column( name = "DT_CADASTRO", nullable = false, updatable = false )
	private LocalDateTime dataCadastro;
	
	public ClienteEnderecoDominioModel() {
		
	}
	
	public ClienteEnderecoDominioModel(Long idClienteEnderecoDominio) {
		this.idClienteEnderecoDominio = idClienteEnderecoDominio;
	}
	
	public ClienteEnderecoDominioModel(String nomeDominio, String descDominio) {
		this.nomeDominio = nomeDominio;
		this.descDominio = descDominio;
	}
}
