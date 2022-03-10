package com.builders.cad.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.builders.cad.cliente.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{

	@Query(value = "SELECT EXISTS(SELECT DISTINCT cpf_cnpj FROM tab_cliente WHERE cpf_cnpj = ?) AS exist", nativeQuery = true)
	String existClient(String cpfCnpj);
	
	@Query(value = "SELECT id_cliente, cpf_cnpj, dt_atualizacao, ativo, dt_cadastro, dt_nascimento, nick_name, nm_primeiro, nm_segundo, tp_pessoa " + 
					"FROM tab_cliente WHERE " +
					"(nm_primeiro LIKE :nome%) OR " +
					"(nm_segundo LIKE :nome%) OR " + 
					"(nick_name LIKE :nome%)", 
					nativeQuery = true)
	ClienteModel findByName(@Param("nome") String nome);
	
	@Query(value = "SELECT id_cliente, cpf_cnpj, dt_atualizacao, ativo, dt_cadastro, dt_nascimento, nick_name, nm_primeiro, nm_segundo, tp_pessoa " + 
			"FROM tab_cliente WHERE cpf_cnpj = ?", 
			nativeQuery = true)
	ClienteModel findByDocument(String cpfCnpj);

}
