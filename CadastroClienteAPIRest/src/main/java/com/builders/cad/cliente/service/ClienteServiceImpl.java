package com.builders.cad.cliente.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.enums.TipoContatoEnum;
import com.builders.cad.cliente.enums.TipoEnderecoEnum;
import com.builders.cad.cliente.exception.ResourceNoContentException;
import com.builders.cad.cliente.exception.ResourceConflitException;
import com.builders.cad.cliente.exception.UnSupportedCharacterException;
import com.builders.cad.cliente.exception.UnSupportedNumberException;
import com.builders.cad.cliente.model.ClienteModel;
import com.builders.cad.cliente.model.ClienteContatoModel;
import com.builders.cad.cliente.model.ClienteEnderecoModel;
import com.builders.cad.cliente.repository.ClienteRepository;
import com.builders.cad.cliente.service.handler.ClienteHandler;
import com.builders.cad.cliente.service.handler.ContatoHandler;
import com.builders.cad.cliente.service.handler.EnderecoHandler;
import com.builders.cad.cliente.service.interfaces.IClienteService;
import com.builders.cad.cliente.vo.ClienteVO;
import com.builders.cad.cliente.vo.ContatoVO;
import com.builders.cad.cliente.vo.EnderecoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteHandler clienteHandler;
	
	@Autowired
	private ContatoHandler contatoHandler;
	
	@Autowired
	private EnderecoHandler enderecoHandler;
	
	private ZoneId timeZone = ZoneId.of("GMT-3");
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public ClienteServiceImpl() {
		
	}
	
	public ClienteServiceImpl(ClienteHandler clienteHandler, ContatoHandler contatoHandler, EnderecoHandler enderecoHandler) {
		this.clienteHandler = clienteHandler;
		this.contatoHandler = contatoHandler;
		this.enderecoHandler = enderecoHandler;
	}

	/**
	 * 
	 */
	@Transactional(value = TxType.REQUIRED)
	@Override
	public ClienteVO salvarCliente(ClienteVO clienteVO) {
		
		LocalDateTime localDateTime 	= LocalDateTime.now(timeZone);
		String existeCliente 			= null;
		
		if(clienteVO.getTipoPessoa().equals("F")) {
			existeCliente = clienteRepository.existClient(clienteVO.getCpf());
		}else {
			existeCliente = clienteRepository.existClient(clienteVO.getCnpj());
		}

		if(existeCliente == null || existeCliente.trim().isEmpty() || existeCliente.equals("0")) {
			
			ClienteModel cliente = clienteHandler.convertToCliente(clienteVO);
			
			cliente.setAtivo(StatusEnum.ATIVO.getTipoStatusAtivo().byteValue());
			
			List<ContatoVO> contatos = clienteVO.getContato();
			
			if(contatos != null && !contatos.isEmpty()) {
			
				for (ContatoVO contatoVO : contatos) {
					
					ClienteContatoModel clienteContato = contatoHandler.convertToClienteContato(contatoVO, StatusEnum.ATIVO);
					clienteContato.setDataCadastro(localDateTime);
					clienteContato.setDataAtualizacao(localDateTime);
					
					clienteContato.setCliente(cliente);
					
					cliente.getClienteContato().add(clienteContato);
				}
			}
			
			List<EnderecoVO> enderecos = clienteVO.getEndereco();
			
			if(enderecos != null && !enderecos.isEmpty()) {
				
				for (EnderecoVO enderecoVO : enderecos) {
			
					ClienteEnderecoModel clienteEndereco = enderecoHandler.convertToClienteEndereco(enderecoVO, StatusEnum.ATIVO);
					clienteEndereco.setDataCadastro(localDateTime);
					clienteEndereco.setDataAtualizacao(localDateTime);
					
					clienteEndereco.setCliente(cliente);
					
					cliente.getClienteEndereco().add(clienteEndereco);
				}
				
			}
			
			cliente.setDataCadastro(localDateTime);
			cliente.setDataAtualizacao(localDateTime);
			
			//Salva o endereço do cliente
			clienteRepository.save(cliente);
			
			clienteVO.setKey(cliente.getIdCliente());

		} else {
			throw new ResourceConflitException("Cliente já existe!");
		}

		return clienteVO;
	}

	/**
	 * 
	 */
	@Transactional(value = TxType.REQUIRED)
	@Override
	public ClienteVO atualizarCliente(ClienteVO clienteVO) {
		
		log.info("> INICIO atualizarCliente");

		ClienteModel cliente = clienteRepository.findById(clienteVO.getKey())
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID to update."));
 
		clienteHandler.convertToCliente(cliente, clienteVO);
		
		cliente.setDataAtualizacao(LocalDateTime.now(timeZone));
		
		clienteRepository.saveAndFlush(cliente);
		
		log.info("> FIM atualizarCliente");
		
		return clienteVO;

	}

	/**
	 * 
	 */
	@Transactional(value = TxType.REQUIRED)
	@Override
	public void excluirCliente(Long id) {
		
		log.info("> INICIO excluirCliente");
		
		if(id == null || id <= 0){
			throw new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		}else {
			throw new ResourceNoContentException("No records found for this ID.");
		}

		log.info("> FIM excluirCliente");
	}

	/**
	 * 
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public ClienteVO buscarCliente(Long id) {

		log.info("> INICIO buscarCliente {}", id);
		
		if(id == null || id <= 0){
			throw new UnSupportedNumberException("Valor não permitido. Favor passar um número válido.");
		}
		
		ClienteModel cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		ClienteVO clienteVO = this.convertToClienteVO(cliente);
		
		log.info("> FIM buscarCliente {}", id);
		
		return clienteVO;

	}
	
	/**
	 * 
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public ClienteVO buscarClientePorNome(String nome) {
		
		log.info("> INICIO buscarClientePorNome {}", nome);
		
		if(nome == null || nome.trim().isEmpty() || nome.trim().length() < 4){
			throw new UnSupportedCharacterException("Dados incompletos para prosseguir com a pesquisa. Espera-se no mínimo 4 caracteres.");
		}
		
		ClienteModel cliente = clienteRepository.findByName(nome);
		
		if(cliente == null) {
			throw new ResourceNoContentException("No records found for this ID.");
		}
		
		ClienteVO clienteVO = this.convertToClienteVO(cliente);
		
		log.info("> FIM buscarClientePorNome {}", nome);
		
		return clienteVO;
	}
	
	/**
	 * 
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public ClienteVO buscarClientePorCpfCnpj(String cpfCnpj) {
		
		log.info("> INICIO buscarClientePorCpfCnpj {}", cpfCnpj);
		
		if(cpfCnpj == null || cpfCnpj.trim().isEmpty()){
			throw new UnSupportedCharacterException("Dados incompletos para prosseguir com a pesquisa. Espera-se apenas o nº CPF ou CNPJ");
		}
		
		cpfCnpj = cpfCnpj.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("\\/", "");
		
		ClienteModel cliente = clienteRepository.findByDocument(cpfCnpj);
		
		if(cliente == null) {
			throw new ResourceNoContentException("No records found for this ID.");
		}
		
		ClienteVO clienteVO = this.convertToClienteVO(cliente);
		
		log.info("> FIM buscarClientePorCpfCnpj {}", cpfCnpj);
		
		return clienteVO;
	}
	
	/**
	 * 
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	@Override
	public Page<ClienteVO> buscarTodosClientesPorPaginacao(Pageable pageable) {
		
		log.info("> INICIO buscarTodosClientesPorPaginacao");
		
		var page = clienteRepository.findAll(pageable);

		log.info("> FIM buscarTodosClientesPorPaginacao");
		
		return page.map(this::convertToClienteVO);
	}
	
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
		
		if(cliente.getClienteContato() != null) {
			
			Set<ClienteContatoModel> clienteContato = cliente.getClienteContato();
			
			ContatoVO contatoVO = null;
			
			for (ClienteContatoModel clienteContatoModel : clienteContato) {
				
				contatoVO = new ContatoVO();
				contatoVO.setContatoEnum(TipoContatoEnum.CONTATO_RESIDENCIAL.getStatus(clienteContatoModel.getContatoDominio().getIdClienteContatoDominio().intValue()));
				contatoVO.setEmail(clienteContatoModel.getEmail());
				contatoVO.setKey(clienteContatoModel.getIdClienteContato());
				contatoVO.setNumTelefone(clienteContatoModel.getNumTelefone());
				contatoVO.setStatusEnum(StatusEnum.ATIVO.getStatus(clienteContatoModel.getAtivo().intValue()));
				clienteVO.getContato().add(contatoVO);
			}
		}
		
		if(cliente.getClienteEndereco() != null) {
			
			Set<ClienteEnderecoModel> clienteEndereco = cliente.getClienteEndereco();
			
			EnderecoVO enderecoVO = null;
			
			for (ClienteEnderecoModel clienteEnderecoModel : clienteEndereco) {
				
				enderecoVO = new EnderecoVO();
				enderecoVO.setEnderecoEnum(TipoEnderecoEnum.ENDERECO_RESIDENCIAL.getStatus(clienteEnderecoModel.getEnderecoDominio().getIdClienteEnderecoDominio().intValue()));
				enderecoVO.setCaixaPostal(clienteEnderecoModel.getCaixaPostal());
				enderecoVO.setKey(clienteEnderecoModel.getIdClienteEndereco());
				enderecoVO.setCidade(clienteEnderecoModel.getCidade());
				enderecoVO.setEstado(clienteEnderecoModel.getEstado());
				enderecoVO.setLogradouro(clienteEnderecoModel.getLogradouro());
				enderecoVO.setNumCep(clienteEnderecoModel.getNumCep());
				enderecoVO.setNumLogradouro(clienteEnderecoModel.getNumLogradouro());
				enderecoVO.setPais(clienteEnderecoModel.getPais());
				enderecoVO.setStatusEnum(StatusEnum.ATIVO.getStatus(clienteEnderecoModel.getAtivo().intValue()));
				clienteVO.getEndereco().add(enderecoVO);
			}
		}

		log.info("> FIM convertToClienteVO");
		
		return clienteVO;
	}

}
