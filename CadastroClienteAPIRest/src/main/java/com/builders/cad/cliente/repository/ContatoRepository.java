package com.builders.cad.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.builders.cad.cliente.model.ClienteContatoModel;

@Repository
public interface ContatoRepository extends JpaRepository<ClienteContatoModel, Long>{
	
}
