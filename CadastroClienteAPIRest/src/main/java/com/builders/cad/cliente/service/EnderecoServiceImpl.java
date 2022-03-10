package com.builders.cad.cliente.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.exception.ResourceNoContentException;
import com.builders.cad.cliente.exception.UnSupportedNumberException;
import com.builders.cad.cliente.model.ClienteModel;
import com.builders.cad.cliente.model.ClienteEnderecoModel;
import com.builders.cad.cliente.model.ClienteEnderecoDominioModel;
import com.builders.cad.cliente.repository.ClienteRepository;
import com.builders.cad.cliente.repository.EnderecoDominioRepository;
import com.builders.cad.cliente.repository.EnderecoRepository;
import com.builders.cad.cliente.service.handler.EnderecoHandler;
import com.builders.cad.cliente.service.interfaces.IEnderecoService;
import com.builders.cad.cliente.vo.EnderecoDominioVO;
import com.builders.cad.cliente.vo.EnderecoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnderecoServiceImpl implements IEnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EnderecoDominioRepository enderecoDominioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;  
	
	@Autowired
	private EnderecoHandler enderecoHandler;
	
	private ZoneId timeZone = ZoneId.of("GMT-3");
	
	public EnderecoServiceImpl() {
	}
	
	public EnderecoServiceImpl(EnderecoHandler enderecoHandler) {
		this.enderecoHandler = enderecoHandler;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public EnderecoVO salvarEndereco(EnderecoVO enderecoVO) {
		
		log.info("> INICIO salvarEndereco");
		
		LocalDateTime localDateTime = LocalDateTime.now(timeZone);

		ClienteEnderecoModel clienteEndereco = enderecoHandler.convertToClienteEndereco(enderecoVO, StatusEnum.ATIVO);
		
		ClienteModel cliente = clienteRepository.findById(enderecoVO.getIdCliente())
		.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		clienteEndereco.setDataCadastro(localDateTime);
		clienteEndereco.setDataAtualizacao(localDateTime);

		cliente.getClienteEndereco().add(clienteEndereco);
		
		clienteEndereco.setCliente(cliente);

		clienteRepository.saveAndFlush(cliente);
		
		log.info("> FIM salvarEndereco");
		
		return enderecoVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public EnderecoVO atualizarEndereco(EnderecoVO enderecoVO) {
		
		log.info("> INICIO atualizarEndereco");

		ClienteEnderecoModel clienteEndereco = enderecoRepository.findById(enderecoVO.getKey())
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID to update."));
 
		enderecoHandler.convertToClienteEndereco(clienteEndereco, enderecoVO, enderecoVO.getStatusEnum());

		clienteEndereco.setDataAtualizacao(LocalDateTime.now(timeZone));
		
		enderecoRepository.save(clienteEndereco);
		
		log.info("> FIM atualizarEndereco");
		
		return enderecoVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void excluirEndereco(Long id) {
		
		log.info("> INICIO excluirEndereco");
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		if(enderecoRepository.existsById(id)) {
			enderecoRepository.deleteById(id);
		}else {
			throw new ResourceNoContentException("No records found for this ID.");
		}

		log.info("> FIM excluirEndereco");	
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public EnderecoVO buscarEndereco(Long id) {
		
		log.info("> INICIO buscarEndereco {}", id);
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		EnderecoVO enderecoVO = new EnderecoVO();
		
		ClienteEnderecoModel clienteEndereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		enderecoHandler.convertToEnderecoVO(clienteEndereco, enderecoVO);
		
		log.info("> FIM buscarEndereco {}", id);
		
		return enderecoVO;
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public Page<EnderecoVO> buscarTodosEnderecosPorPaginacao(Pageable pageable) {
		
		log.info("> INICIO buscarTodosEnderecosPorPaginacao");
		
		var page = enderecoRepository.findAll(pageable);

		log.info("> FIM buscarTodosEnderecosPorPaginacao");
		
		return page.map(enderecoHandler::convertToEnderecoVO);
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public EnderecoDominioVO salvarEnderecoDominio(EnderecoDominioVO enderecoDominioVO) {
		
		log.info("> INICIO salvarEnderecoDominio");
		
		LocalDateTime localDateTime = LocalDateTime.now(timeZone);

		ClienteEnderecoDominioModel clienteEnderecoDominio = enderecoHandler.convertToClienteEnderecoDominio(enderecoDominioVO);
		
		clienteEnderecoDominio.setDataCadastro(localDateTime);
		clienteEnderecoDominio.setDataAtualizacao(localDateTime);
		
		enderecoDominioRepository.save(clienteEnderecoDominio);
		
		enderecoDominioVO.setIdEnderecoDominio(clienteEnderecoDominio.getIdClienteEnderecoDominio());
		
		log.info("> FIM salvarEnderecoDominio");
		
		return enderecoDominioVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public EnderecoDominioVO atualizarEnderecoDominio(EnderecoDominioVO enderecoDominioVO) {
		
		log.info("> INICIO atualizarEnderecoDominio");

		ClienteEnderecoDominioModel clienteEnderecoDominio = enderecoDominioRepository.findById(enderecoDominioVO.getIdEnderecoDominio())
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID to update."));
 
		enderecoHandler.convertToEnderecoDominio(clienteEnderecoDominio, enderecoDominioVO);
		
		clienteEnderecoDominio.setDataAtualizacao(LocalDateTime.now(timeZone));
		
		enderecoDominioRepository.save(clienteEnderecoDominio);
		
		log.info("> FIM atualizarEnderecoDominio");
		
		return enderecoDominioVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void excluirEnderecoDominio(Long id) {
		
		log.info("> INICIO excluirEnderecoDominio");
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		if(enderecoDominioRepository.existsById(id)) {
			enderecoDominioRepository.deleteById(id);
		}else {
			throw new ResourceNoContentException("No records found for this ID.");
		}

		log.info("> FIM excluirEnderecoDominio");	
		
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public EnderecoDominioVO buscarEnderecoDominio(Long id) {
		
		log.info("> INICIO buscarEnderecoDominio {}", id);
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		EnderecoDominioVO enderecoDominioVO = new EnderecoDominioVO();
		
		ClienteEnderecoDominioModel clienteEnderecoDominio = enderecoDominioRepository.findById(id)
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		enderecoHandler.convertToEnderecoDominioVO(clienteEnderecoDominio, enderecoDominioVO);
		
		log.info("> FIM buscarEnderecoDominio {}", id);
		
		return enderecoDominioVO;
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public List<EnderecoDominioVO> buscarTodosEnderecoDominio() {

		log.info("> INICIO buscarTodosEnderecoDominio");
		
		List<ClienteEnderecoDominioModel> findAll 				= enderecoDominioRepository.findAll();
		List<EnderecoDominioVO> findAllEnderecoDominioVO 	= new LinkedList<EnderecoDominioVO>();
		EnderecoDominioVO enderecoDominioVO 				= null;
		
		for (ClienteEnderecoDominioModel clienteEnderecoDominio : findAll) {
			
			enderecoDominioVO = new EnderecoDominioVO();
			enderecoHandler.convertToEnderecoDominioVO(clienteEnderecoDominio, enderecoDominioVO);
			findAllEnderecoDominioVO.add(enderecoDominioVO);
			
		}
		
		log.info("> FIM buscarTodosEnderecoDominio");
		
		return findAllEnderecoDominioVO;
	}
}
