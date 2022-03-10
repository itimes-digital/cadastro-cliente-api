package com.builders.cad.cliente.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "TAB_CLIENTE", uniqueConstraints = {@UniqueConstraint(columnNames = "CPF_CNPJ")})
public class ClienteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "ID_CLIENTE", nullable = false )
	private Long idCliente;
	
	@Column( name = "NM_PRIMEIRO", nullable = false )
	private String primeiroNome;
	
	@Column( name = "NM_SEGUNDO", nullable = false )
	private String segundoNome;
	
	@Column( name = "NICK_NAME", nullable = true, length = 50)
	private String nickname;
	
	@Column( name = "TP_PESSOA", nullable = false, length = 1)
	private char tipoPessoa;
	
	@Column( name = "ATIVO", nullable = false, length = 1 )
	private Byte ativo;

	@Column( name = "CPF_CNPJ", nullable = false, length = 14)
	private String cpfCnpj;
	
	@Column( name = "DT_NASCIMENTO", nullable = false )
	private LocalDate dataNascimento;
	
	@Column( name = "DT_ATUALIZACAO", nullable = false )
	private LocalDateTime dataAtualizacao;
	
	@Column( name = "DT_CADASTRO", nullable = false, updatable = false )
	private LocalDateTime dataCadastro;
	
	@OneToMany(
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
	        orphanRemoval = true, 
	        fetch = FetchType.EAGER
	    )
	@JoinColumn(name = "ID_CLIENTE")
	@Column( name = "ID_CLIENTE_CONTATO", nullable = true )
	private Set<ClienteContatoModel> clienteContato;
	
	@OneToMany(
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
	        orphanRemoval = true,
	        fetch = FetchType.EAGER
	    )
	@JoinColumn(name = "ID_CLIENTE")
	@Column( name = "ID_CLIENTE_ENDERECO", nullable = true )
	private Set<ClienteEnderecoModel> clienteEndereco;
	
	public Set<ClienteContatoModel> getClienteContato(){
		if(clienteContato == null) {
			clienteContato = new LinkedHashSet<ClienteContatoModel>();
		}
		return clienteContato;
	}
	
	public Set<ClienteEnderecoModel> getClienteEndereco(){
		if(clienteEndereco == null) {
			clienteEndereco = new LinkedHashSet<ClienteEnderecoModel>();
		}
		return clienteEndereco;
	}
	
	public ClienteModel() {
		
	}
	
	public ClienteModel(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public ClienteModel(String primeiroNome, 
					String segundoNome, 
					String nickname, 
					char tipoPessoa, 
					String cpfCnpj, 
					LocalDate dataNascimento) {
		
		this.primeiroNome = primeiroNome;
		this.segundoNome = segundoNome;
		this.nickname = nickname;
		this.tipoPessoa = tipoPessoa;
		this.cpfCnpj = cpfCnpj;
		this.dataNascimento = dataNascimento;
		
	}
	
}
