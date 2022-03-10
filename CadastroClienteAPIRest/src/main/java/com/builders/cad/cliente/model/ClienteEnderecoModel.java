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
@Table(name = "TAB_CLIENTE_ENDERECO")
public class ClienteEnderecoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "ID_CLIENTE_ENDERECO", nullable = false )
	private Long idClienteEndereco;
	
	@Column( name = "LOGRADOURO", nullable = false, length = 100 )
	private String logradouro;
	
	@Column( name = "NUM_LOGRADOURO", nullable = false )
	private Integer numLogradouro;
	
	@Column( name = "NUM_CEP", nullable = false, length = 9 )
	private String numCep;
	
	@Column( name = "CAIXA_POSTAL", nullable = true, length = 50 )
	private String caixaPostal;
	
	@Column( name = "CIDADE", nullable = false, length = 100 )
	private String cidade;
	
	@Column( name = "ESTADO", nullable = false, length = 2 )
	private String estado;
	
	@Column( name = "PAIS", nullable = false, length = 2 )
	private String pais;
	
	@Column( name = "ATIVO", nullable = false, length = 1 )
	private Byte ativo;

	@Column( name = "DT_ATUALIZACAO", nullable = false )
	private LocalDateTime dataAtualizacao;
	
	@Column( name = "DT_CADASTRO", nullable = false, updatable = false )
	private LocalDateTime dataCadastro;	
	
    @OneToOne
    @JoinColumn(name = "ID_CLIENTE_ENDERECO_DOMINIO", nullable = false)
	private ClienteEnderecoDominioModel enderecoDominio;
    
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private ClienteModel cliente;
    
    public ClienteEnderecoModel() {
    	
    }
    
    public ClienteEnderecoModel(String logradouro,
				    		Integer numLogradouro,
				    		String numCep,
				    		String caixaPostal,
				    		String cidade,
				    		String estado,
				    		String pais,
				    		ClienteEnderecoDominioModel enderecoDominio) {
    	
    	this.logradouro = logradouro;
    	this.numLogradouro = numLogradouro;
    	this.numCep = numCep;
    	this.caixaPostal = caixaPostal;
    	this.cidade = cidade;
    	this.estado = estado;
    	this.pais = pais;
    	this.enderecoDominio = enderecoDominio;
    }

}
