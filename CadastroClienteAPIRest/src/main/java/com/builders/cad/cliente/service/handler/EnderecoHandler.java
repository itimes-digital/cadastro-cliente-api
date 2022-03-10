package com.builders.cad.cliente.service.handler;

import org.springframework.stereotype.Component;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.model.ClienteEnderecoModel;
import com.builders.cad.cliente.model.ClienteEnderecoDominioModel;
import com.builders.cad.cliente.vo.EnderecoDominioVO;
import com.builders.cad.cliente.vo.EnderecoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnderecoHandler {

	
	/**
	 * 
	 * @param enderecoDominioVO
	 * @return
	 */
	public ClienteEnderecoDominioModel convertToClienteEnderecoDominio(EnderecoDominioVO enderecoDominioVO) {
		
		log.info("> INICIO convertToClienteEnderecoDominio");
		
		ClienteEnderecoDominioModel clienteEnderecoDominio = new ClienteEnderecoDominioModel();
		clienteEnderecoDominio.setIdClienteEnderecoDominio(enderecoDominioVO.getIdEnderecoDominio());
		clienteEnderecoDominio.setNomeDominio(enderecoDominioVO.getNomeDominio());
		clienteEnderecoDominio.setDescDominio(enderecoDominioVO.getDescDominio());

		log.info("> FIM convertToClienteEnderecoDominio");
		
		return clienteEnderecoDominio;
	}
	
	/**
	 * 
	 * @param enderecoVO
	 * @param statusEnum
	 * @return
	 */
	public ClienteEnderecoModel convertToClienteEndereco(EnderecoVO enderecoVO, StatusEnum statusEnum) {
		
		log.info("> INICIO convertToClienteEndereco");
		
		ClienteEnderecoModel clienteEndereco = new ClienteEnderecoModel();
		clienteEndereco.setIdClienteEndereco(enderecoVO.getKey());
		clienteEndereco.setCaixaPostal(enderecoVO.getCaixaPostal());
		clienteEndereco.setCidade(enderecoVO.getCidade());
		clienteEndereco.setEstado(enderecoVO.getEstado());
		clienteEndereco.setLogradouro(enderecoVO.getLogradouro());
		clienteEndereco.setNumCep(enderecoVO.getNumCep());
		clienteEndereco.setNumLogradouro(enderecoVO.getNumLogradouro());
		clienteEndereco.setPais(enderecoVO.getPais());
		clienteEndereco.setEnderecoDominio(new ClienteEnderecoDominioModel(Long.valueOf(enderecoVO.getEnderecoEnum().getTipoEndereco())));
		clienteEndereco.setAtivo(statusEnum.getTipoStatusAtivo().byteValue());
		
		log.info("> FIM convertToClienteEndereco");
		
		return clienteEndereco;
	}
	
	
	/**
	 * 
	 * @param clienteEndereco
	 * @return
	 */
	public EnderecoVO convertToEnderecoVO(ClienteEnderecoModel clienteEndereco) {
		
		log.info("> INICIO convertToEnderecoVO");
		
		EnderecoVO enderecoVO = new EnderecoVO();
		enderecoVO.setKey(clienteEndereco.getIdClienteEndereco());
		enderecoVO.setCaixaPostal(clienteEndereco.getCaixaPostal());
		enderecoVO.setCidade(clienteEndereco.getCidade());
		enderecoVO.setEstado(clienteEndereco.getEstado());
		enderecoVO.setLogradouro(clienteEndereco.getLogradouro());
		enderecoVO.setNumCep(clienteEndereco.getNumCep());
		enderecoVO.setNumLogradouro(clienteEndereco.getNumLogradouro());
		enderecoVO.setPais(clienteEndereco.getPais());
		enderecoVO.setStatusEnum(StatusEnum.ATIVO.getStatus(clienteEndereco.getAtivo().intValue()));
		
		log.info("> FIM convertToEnderecoVO");
		
		return enderecoVO;
	}

	
	/**
	 * 
	 * @param clienteEnderecoDominio
	 * @param enderecoDominioVO
	 */
	public void convertToEnderecoDominioVO(ClienteEnderecoDominioModel clienteEnderecoDominio, EnderecoDominioVO enderecoDominioVO) {
		
		log.info("> INICIO convertToEnderecoDominioVO");

		enderecoDominioVO.setIdEnderecoDominio(clienteEnderecoDominio.getIdClienteEnderecoDominio());
		enderecoDominioVO.setNomeDominio(clienteEnderecoDominio.getNomeDominio());
		enderecoDominioVO.setDescDominio(clienteEnderecoDominio.getDescDominio());

		log.info("> FIM convertToEnderecoDominioVO");
	}
	
	/**
	 * 
	 * @param clienteEnderecoDominio
	 * @param enderecoDominioVO
	 */
	public void convertToEnderecoDominio(ClienteEnderecoDominioModel clienteEnderecoDominio, EnderecoDominioVO enderecoDominioVO) {
		
		log.info("> INICIO convertToEnderecoDominio");

		clienteEnderecoDominio.setIdClienteEnderecoDominio(enderecoDominioVO.getIdEnderecoDominio());
		clienteEnderecoDominio.setNomeDominio(enderecoDominioVO.getNomeDominio());
		clienteEnderecoDominio.setDescDominio(enderecoDominioVO.getDescDominio());

		log.info("> FIM convertToEnderecoDominio");
	}
	
	/**
	 * 
	 * @param clienteEndereco
	 * @param enderecoVO
	 * @param statusEnum
	 */
	public void convertToClienteEndereco(ClienteEnderecoModel clienteEndereco, EnderecoVO enderecoVO, StatusEnum statusEnum) {
		
		log.info("> INICIO convertToEndereco");

		clienteEndereco.setIdClienteEndereco(enderecoVO.getKey());
		clienteEndereco.setCaixaPostal(enderecoVO.getCaixaPostal());
		clienteEndereco.setCidade(enderecoVO.getCidade());
		clienteEndereco.setEstado(enderecoVO.getEstado());
		clienteEndereco.setLogradouro(enderecoVO.getLogradouro());
		clienteEndereco.setNumCep(enderecoVO.getNumCep());
		clienteEndereco.setNumLogradouro(enderecoVO.getNumLogradouro());
		clienteEndereco.setPais(enderecoVO.getPais());
		clienteEndereco.setAtivo(statusEnum.getTipoStatusAtivo().byteValue());
		
		log.info("> FIM convertToEndereco");

	}
	
	public void convertToEnderecoVO(ClienteEnderecoModel clienteEndereco, EnderecoVO enderecoVO) {
		
		log.info("> INICIO convertToEnderecoVO");

		enderecoVO.setKey(clienteEndereco.getIdClienteEndereco());
		enderecoVO.setCaixaPostal(clienteEndereco.getCaixaPostal());
		enderecoVO.setCidade(clienteEndereco.getCidade());
		enderecoVO.setEstado(clienteEndereco.getEstado());
		enderecoVO.setLogradouro(clienteEndereco.getLogradouro());
		enderecoVO.setNumCep(clienteEndereco.getNumCep());
		enderecoVO.setNumLogradouro(clienteEndereco.getNumLogradouro());
		enderecoVO.setPais(clienteEndereco.getPais());
		enderecoVO.setStatusEnum(StatusEnum.ATIVO.getStatus(clienteEndereco.getAtivo().intValue()));
		
		log.info("> FIM convertToEnderecoVO");

	}
}
