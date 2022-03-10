package com.builders.cad.cliente.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "TAB_CLIENTE_CONTATO")
public class ClienteContatoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "ID_CLIENTE_CONTATO", nullable = false )
	private Long idClienteContato;
	
	@Column( name = "NUM_TELEFONE", nullable = false, length = 14 )
	private String numTelefone;

	@Column( name = "EMAIL", nullable = false, length = 50 )
	private String email;
	
	@Column( name = "ATIVO", nullable = false, length = 1 )
	private Byte ativo;

	@Column( name = "DT_ATUALIZACAO", nullable = false )
	private LocalDateTime dataAtualizacao;
	
	@Column( name = "DT_CADASTRO", nullable = false, updatable = false )
	private LocalDateTime dataCadastro;
	
    @OneToOne()
    @JoinColumn(name="ID_CLIENTE_CONTATO_DOMINIO", nullable = false)
	private ClienteContatoDominioModel contatoDominio;
    
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private ClienteModel cliente;
    
    public ClienteContatoModel() {
    	
    }
    
    public ClienteContatoModel(String numTelefone, String email, ClienteContatoDominioModel contatoDominio) {
    	this.numTelefone = numTelefone;
    	this.email = email;
    	this.contatoDominio = contatoDominio;
    }
	
}
