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
import com.builders.cad.cliente.model.ClienteContatoModel;
import com.builders.cad.cliente.model.ClienteContatoDominioModel;
import com.builders.cad.cliente.repository.ClienteRepository;
import com.builders.cad.cliente.repository.ContatoDominioRepository;
import com.builders.cad.cliente.repository.ContatoRepository;
import com.builders.cad.cliente.service.handler.ContatoHandler;
import com.builders.cad.cliente.service.interfaces.IContatoService;
import com.builders.cad.cliente.vo.ContatoDominioVO;
import com.builders.cad.cliente.vo.ContatoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContatoServiceImpl implements IContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private ContatoDominioRepository contatoDominioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;  
	
	@Autowired
	private ContatoHandler contatoHandler;
	
	private ZoneId timeZone = ZoneId.of("GMT-3");
	
	public ContatoServiceImpl() {
	}
	
	public ContatoServiceImpl(ContatoHandler contatoHandler) {
		this.contatoHandler = contatoHandler;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public ContatoVO salvarContato(ContatoVO contatoVO) {
		
		log.info("> INICIO salvarContato");		
		
		LocalDateTime localDateTime = LocalDateTime.now(timeZone);

		ClienteContatoModel clienteContato = contatoHandler.convertToClienteContato(contatoVO, StatusEnum.ATIVO);
		
		ClienteModel cliente = clienteRepository.findById(contatoVO.getIdCliente())
		.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		clienteContato.setDataCadastro(localDateTime);
		clienteContato.setDataAtualizacao(localDateTime);

		cliente.getClienteContato().add(clienteContato);
		
		clienteContato.setCliente(cliente);

		clienteRepository.saveAndFlush(cliente);

		log.info("> FIM salvarContato");
		
		return contatoVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public ContatoVO atualizarContato(ContatoVO contatoVO) {
		
		log.info("> INICIO atualizarContato");

		ClienteContatoModel clienteContato = contatoRepository.findById(contatoVO.getKey())
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID to update."));
 
		contatoHandler.convertToClienteContato(clienteContato, contatoVO, contatoVO.getStatusEnum());
		
		clienteContato.setDataAtualizacao(LocalDateTime.now(timeZone));
		
		contatoRepository.save(clienteContato);
		
		log.info("> FIM atualizarContato");
		
		return contatoVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void excluirContato(Long id) {
		
		log.info("> INICIO excluirContato");
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		if(contatoRepository.existsById(id)) {
			contatoRepository.deleteById(id);
		}else {
			throw new ResourceNoContentException("No records found for this ID.");
		}

		log.info("> FIM excluirContato");	
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public ContatoVO buscarContato(Long id) {
		
		log.info("> INICIO buscarContato {}", id);
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		ContatoVO contatoVO = new ContatoVO();
		
		ClienteContatoModel clienteContato = contatoRepository.findById(id)
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		contatoHandler.convertToContatoVO(clienteContato, contatoVO);
		
		log.info("> FIM buscarContato {}", id);
		
		return contatoVO;
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public Page<ContatoVO> buscarTodosContatosPorPaginacao(Pageable pageable) {
		
		log.info("> INICIO buscarTodosContatosPorPaginacao");
		
		var page = contatoRepository.findAll(pageable);

		log.info("> FIM buscarTodosContatosPorPaginacao");
		
		return page.map(contatoHandler::convertToContatoVO);
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public ContatoDominioVO salvarContatoDominio(ContatoDominioVO contatoDominioVO) {
		
		log.info("> INICIO salvarContatoDominio");
		
		LocalDateTime localDateTime = LocalDateTime.now(timeZone);

		ClienteContatoDominioModel clienteContatoDominio = contatoHandler.convertToClienteContatoDominio(contatoDominioVO);
		clienteContatoDominio.setDataCadastro(localDateTime);
		clienteContatoDominio.setDataAtualizacao(localDateTime);
		
		contatoDominioRepository.save(clienteContatoDominio);
		
		contatoDominioVO.setIdContatoDominio(clienteContatoDominio.getIdClienteContatoDominio());
		
		log.info("> FIM salvarContatoDominio");
		
		return contatoDominioVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public ContatoDominioVO atualizarContatoDominio(ContatoDominioVO contatoDominioVO) {
		
		log.info("> INICIO atualizarContatoDominio");

		ClienteContatoDominioModel clienteContatoDominio = contatoDominioRepository.findById(contatoDominioVO.getIdContatoDominio())
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID to update."));
 
		contatoHandler.convertToContatoDominio(clienteContatoDominio, contatoDominioVO);
		
		clienteContatoDominio.setDataAtualizacao(LocalDateTime.now(timeZone));
		
		contatoDominioRepository.save(clienteContatoDominio);
		
		log.info("> FIM atualizarContatoDominio");
		
		return contatoDominioVO;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void excluirContatoDominio(Long id) {
		
		log.info("> INICIO excluirContatoDominio");
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		if(contatoDominioRepository.existsById(id)) {
			contatoDominioRepository.deleteById(id);
		}else {
			throw new ResourceNoContentException("No records found for this ID.");
		}

		log.info("> FIM excluirContatoDominio");	
		
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public ContatoDominioVO buscarContatoDominio(Long id) {
		
		log.info("> INICIO buscarContatoDominio {}", id);
		
		if(id == null || id <= 0){
			new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		ContatoDominioVO contatoDominioVO = new ContatoDominioVO();
		
		ClienteContatoDominioModel clienteContatoDominio = contatoDominioRepository.findById(id)
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		contatoHandler.convertToContatoDominioVO(clienteContatoDominio, contatoDominioVO);
		
		log.info("> FIM buscarContatoDominio {}", id);
		
		return contatoDominioVO;
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public List<ContatoDominioVO> buscarTodosContatoDominio() {

		log.info("> INICIO buscarTodosContatoDominio");
		
		List<ClienteContatoDominioModel> findAll 				= contatoDominioRepository.findAll();
		List<ContatoDominioVO> findAllContatoDominioVO 		= new LinkedList<ContatoDominioVO>();
		ContatoDominioVO contatoDominioVO 					= null;
		
		for (ClienteContatoDominioModel clienteContatoDominio : findAll) {
			
			contatoDominioVO = new ContatoDominioVO();
			contatoHandler.convertToContatoDominioVO(clienteContatoDominio, contatoDominioVO);
			findAllContatoDominioVO.add(contatoDominioVO);
			
		}
		
		log.info("> FIM buscarTodosContatoDominio");
		
		return findAllContatoDominioVO;
	}
}
