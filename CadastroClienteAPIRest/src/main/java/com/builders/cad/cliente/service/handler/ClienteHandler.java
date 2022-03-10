package com.builders.cad.cliente.service.handler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.builders.cad.cliente.model.ClienteModel;
import com.builders.cad.cliente.vo.ClienteVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClienteHandler {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	/**
	 * 
	 * @param cliente
	 * @return
	 */
	public ClienteVO convertToClienteVO(ClienteModel cliente) {
		
		log.info("> INICIO convertToClienteVO");
		
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setKey(cliente.getIdCliente());
		
		if(String.valueOf(cliente.getTipoPessoa()).equals("F")) {
			clienteVO.setCpf(cliente.getCpfCnpj());
		}else {
			clienteVO.setCnpj(cliente.getCpfCnpj());
		}

		clienteVO.setDataNascimento(cliente.getDataNascimento().format(FORMATTER));
		clienteVO.setNickname(cliente.getNickname());
		clienteVO.setPrimeiroNome(cliente.getPrimeiroNome());
		clienteVO.setSegundoNome(cliente.getSegundoNome());
		clienteVO.setTipoPessoa(String.valueOf(cliente.getTipoPessoa()));

		log.info("> FIM convertToClienteVO");
		
		return clienteVO;
	}
	
	/**
	 * 
	 * @param clienteVO
	 * @return
	 */
	public ClienteModel convertToCliente(ClienteVO clienteVO) {
		
		log.info("> INICIO convertToCliente");
		
		ClienteModel cliente = new ClienteModel();
		cliente.setIdCliente(clienteVO.getKey());
		
		if(clienteVO.getTipoPessoa().equals("F")) {
			cliente.setCpfCnpj(clienteVO.getCpf());	
		}else {
			cliente.setCpfCnpj(clienteVO.getCnpj());
		}

		cliente.setDataNascimento(LocalDate.parse(clienteVO.getDataNascimento()));
		cliente.setNickname(clienteVO.getNickname());
		cliente.setPrimeiroNome(clienteVO.getPrimeiroNome());
		cliente.setSegundoNome(clienteVO.getSegundoNome());
		cliente.setTipoPessoa(clienteVO.getTipoPessoa().charAt(0));

		log.info("> FIM convertToCliente");
		
		return cliente;
	}
	
	/**
	 * 
	 * @param cliente
	 * @param clienteVO
	 */
	public void convertToClienteVO(ClienteModel cliente, ClienteVO clienteVO) {
		
		log.info("> INICIO convertToClienteVO");

		clienteVO.setKey(cliente.getIdCliente());
		
		if(String.valueOf(cliente.getTipoPessoa()).equals("F")) {
			clienteVO.setCpf(cliente.getCpfCnpj());
		}else {
			clienteVO.setCnpj(cliente.getCpfCnpj());
		}

		clienteVO.setDataNascimento(cliente.getDataNascimento().format(FORMATTER));
		clienteVO.setNickname(cliente.getNickname());
		clienteVO.setPrimeiroNome(cliente.getPrimeiroNome());
		clienteVO.setSegundoNome(cliente.getSegundoNome());
		clienteVO.setTipoPessoa(String.valueOf(cliente.getTipoPessoa()));

		log.info("> FIM convertToClienteVO");
	}
	
	/**
	 * 
	 * @param cliente
	 * @param clienteVO
	 */
	public void convertToCliente(ClienteModel cliente, ClienteVO clienteVO) {
		
		log.info("> INICIO convertToCliente");

		cliente.setIdCliente(clienteVO.getKey());
		
		if(clienteVO.getTipoPessoa().equals("F")) {
			cliente.setCpfCnpj(clienteVO.getCpf());	
		}else {
			cliente.setCpfCnpj(clienteVO.getCnpj());
		}
		
		cliente.setDataNascimento(LocalDate.parse(clienteVO.getDataNascimento()));
		cliente.setNickname(clienteVO.getNickname());
		cliente.setPrimeiroNome(clienteVO.getPrimeiroNome());
		cliente.setSegundoNome(clienteVO.getSegundoNome());
		cliente.setTipoPessoa(clienteVO.getTipoPessoa().charAt(0));

		log.info("> FIM convertToCliente");

	}
}
