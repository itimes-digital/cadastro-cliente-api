package com.builders.cad.cliente.service.handler;

import org.springframework.stereotype.Component;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.model.ClienteContatoModel;
import com.builders.cad.cliente.model.ClienteContatoDominioModel;
import com.builders.cad.cliente.vo.ContatoDominioVO;
import com.builders.cad.cliente.vo.ContatoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContatoHandler {

	/**
	 * 
	 * @param contatoDominioVO
	 * @return
	 */
	public ClienteContatoDominioModel convertToClienteContatoDominio(ContatoDominioVO contatoDominioVO) {
		
		log.info("> INICIO convertToClienteContatoDominio");
		
		ClienteContatoDominioModel clienteContatoDominio = new ClienteContatoDominioModel();
		clienteContatoDominio.setIdClienteContatoDominio(contatoDominioVO.getIdContatoDominio());
		clienteContatoDominio.setNomeDominio(contatoDominioVO.getNomeDominio());
		clienteContatoDominio.setDescDominio(contatoDominioVO.getDescDominio());

		log.info("> FIM convertToClienteContatoDominio");
		
		return clienteContatoDominio;
	}
	
	/**
	 * 
	 * @param contatoVO
	 * @return
	 */
	public ClienteContatoModel convertToClienteContato(ContatoVO contatoVO, StatusEnum statusEnum) {
		
		log.info("> INICIO convertToContato");
		
		ClienteContatoModel clienteContato = new ClienteContatoModel();
		clienteContato.setIdClienteContato(contatoVO.getKey());
		clienteContato.setEmail(contatoVO.getEmail());
		clienteContato.setNumTelefone(contatoVO.getNumTelefone());
		clienteContato.setContatoDominio(new ClienteContatoDominioModel(Long.valueOf(contatoVO.getContatoEnum().getTipoContato())));
		clienteContato.setAtivo(statusEnum.getTipoStatusAtivo().byteValue());
		
		log.info("> FIM convertToContato");
		
		return clienteContato;
	}
	
	/**
	 * 
	 * @param contatoVO
	 * @return
	 */
	public ClienteContatoModel convertToClienteContato(ClienteContatoModel clienteContato, ContatoVO contatoVO, StatusEnum statusEnum) {
		
		log.info("> INICIO convertToContato");

		clienteContato.setIdClienteContato(contatoVO.getKey());
		clienteContato.setEmail(contatoVO.getEmail());
		clienteContato.setNumTelefone(contatoVO.getNumTelefone());
		clienteContato.setContatoDominio(new ClienteContatoDominioModel(Long.valueOf(contatoVO.getContatoEnum().getTipoContato())));
		clienteContato.setAtivo(statusEnum.getTipoStatusAtivo().byteValue());
		
		log.info("> FIM convertToContato");
		
		return clienteContato;
	}
	
	
	/**
	 * 
	 * @param clienteContato
	 * @return
	 */
	public ContatoVO convertToContatoVO(ClienteContatoModel clienteContato) {
		
		log.info("> INICIO convertToContatoVO");
		
		ContatoVO contatoVO = new ContatoVO();
		contatoVO.setKey(clienteContato.getIdClienteContato());
		contatoVO.setEmail(clienteContato.getEmail());
		contatoVO.setNumTelefone(clienteContato.getNumTelefone());
		contatoVO.setStatusEnum(StatusEnum.ATIVO.getStatus(clienteContato.getAtivo().intValue()));
		
		log.info("> FIM convertToContatoVO");
		
		return contatoVO;
	}

	public void convertToContatoDominio(ClienteContatoDominioModel clienteContatoDominio, ContatoDominioVO contatoDominioVO) {
		
		log.info("> INICIO convertToContatoDominioVO");

		clienteContatoDominio.setIdClienteContatoDominio(contatoDominioVO.getIdContatoDominio());
		clienteContatoDominio.setNomeDominio(contatoDominioVO.getNomeDominio());
		clienteContatoDominio.setDescDominio(contatoDominioVO.getDescDominio());

		log.info("> FIM convertToContatoDominioVO");
	}
	
	/**
	 * 
	 * @param clienteContatoDominio
	 * @param contatoDominioVO
	 */
	public void convertToContatoDominioVO(ClienteContatoDominioModel clienteContatoDominio, ContatoDominioVO contatoDominioVO) {
		
		log.info("> INICIO convertToContatoDominioVO");

		contatoDominioVO.setIdContatoDominio(clienteContatoDominio.getIdClienteContatoDominio());
		contatoDominioVO.setNomeDominio(clienteContatoDominio.getNomeDominio());
		contatoDominioVO.setDescDominio(clienteContatoDominio.getDescDominio());

		log.info("> FIM convertToContatoDominioVO");
	}
	
	/**
	 * 
	 * @param clienteContato
	 * @param contatoVO
	 */
	public void convertToContatoVO(ClienteContatoModel clienteContato, ContatoVO contatoVO) {
		
		log.info("> INICIO convertToContato");

		contatoVO.setKey(clienteContato.getIdClienteContato());
		contatoVO.setEmail(clienteContato.getEmail());
		contatoVO.setNumTelefone(clienteContato.getNumTelefone());
		contatoVO.setStatusEnum(StatusEnum.ATIVO.getStatus(clienteContato.getAtivo().intValue()));
		
		log.info("> FIM convertToContato");

	}
}
