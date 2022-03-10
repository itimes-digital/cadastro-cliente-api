package com.builders.cad.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.builders.cad.cliente.model.ClienteEnderecoDominioModel;

@Repository
public interface EnderecoDominioRepository extends JpaRepository<ClienteEnderecoDominioModel, Long>{

}
