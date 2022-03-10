package com.builders.cad.cliente.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.builders.cad.cliente.service.interfaces.IContatoService;
import com.builders.cad.cliente.service.interfaces.IEnderecoService;
import com.builders.cad.cliente.vo.ContatoDominioVO;
import com.builders.cad.cliente.vo.EnderecoDominioVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Api(value = "Domínios Cliente Endpoint", tags = {"contato, endereco, dominio"})
@RestController
public class DominiosController {
	
	@Autowired
	private IContatoService contatoService;
	
	@Autowired
	private IEnderecoService enderecoService;
	
	/**
	 * 
	 * @param contatoDominioVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Salva dados de Domínio Contato", response = ContatoDominioVO.class)
	@PostMapping(value = "/v1/contato/dominio")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ContatoDominioVO> salvarContatoDominio(@RequestBody ContatoDominioVO contatoDominioVO) throws Exception{

		log.info("> INICIO salvarContatoDominio");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.salvarContatoDominio(contatoDominioVO));
	}
	
	/**
	 * 
	 * @param contatoDominioVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Atualiza dados de Domínio Contato.", response = ContatoDominioVO.class)
	@PutMapping(value = "/v1/contato/dominio")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ContatoDominioVO atualizarContatoDominio(@RequestBody ContatoDominioVO contatoDominioVO) throws Exception{

		log.info("> INICIO atualizarContatoDominio");
		
		return contatoService.atualizarContatoDominio(contatoDominioVO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Exclui um Contato Domínio da base de dados.")
	@DeleteMapping(value = "/v1/contato/dominio/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public ResponseEntity<?> excluirContatoDominioPorId(@PathVariable(value="id") Long id) throws Exception{

		log.info("> INICIO excluirContatoDominioPorId");
		
		contatoService.excluirContatoDominio(id);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca Contato Domínio a partir de um ID.", response = ContatoDominioVO.class)
	@GetMapping(value = "/v1/contato/dominio/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ContatoDominioVO> buscarContatoDominioPorId(@PathVariable(value="id") Long id) throws Exception{
		
		log.info("> INICIO buscarContatoDominioPorId: {}", id);
		
		ResponseEntity<ContatoDominioVO> retorno = ResponseEntity.ok(contatoService.buscarContatoDominio(id));
		
		return retorno;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca todos os domínios de contato", response = ContatoDominioVO.class)
	@GetMapping(value = "/v1/contato/dominio")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<List<ContatoDominioVO>> buscarTodosContatoDominio() throws Exception{
		
		log.info("> INICIO buscarTodosContatoDominio");
		
		ResponseEntity<List<ContatoDominioVO>> retorno = ResponseEntity.ok(contatoService.buscarTodosContatoDominio());
		
		return retorno;
	}
	
	/**
	 * 
	 * @param enderecoDominioVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Salva dados de Domínio Endereço", response = EnderecoDominioVO.class)
	@PostMapping(value = "/v1/endereco/dominio")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<EnderecoDominioVO> salvarEnderecoDominio(@RequestBody EnderecoDominioVO enderecoDominioVO) throws Exception{

		log.info("> INICIO salvarEnderecoDominio");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvarEnderecoDominio(enderecoDominioVO));
	}
	
	/**
	 * 
	 * @param enderecoDominioVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Atualiza dados de Domínio Endereço.", response = EnderecoDominioVO.class)
	@PutMapping(value = "/v1/endereco/dominio")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody EnderecoDominioVO atualizarEnderecoDominio(@RequestBody EnderecoDominioVO enderecoDominioVO) throws Exception{

		log.info("> INICIO atualizarEnderecoDominio");
		
		return enderecoService.atualizarEnderecoDominio(enderecoDominioVO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Exclui um Endereço Domínio da base de dados.")
	@DeleteMapping(value = "/v1/endereco/dominio/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public ResponseEntity<?> excluirEnderecoDominioPorId(@PathVariable(value="id") Long id) throws Exception{

		log.info("> INICIO excluirEnderecoDominioPorId");
		
		enderecoService.excluirEnderecoDominio(id);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca Endereço Domínio a partir de um ID.", response = EnderecoDominioVO.class)
	@GetMapping(value = "/v1/endereco/dominio/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<EnderecoDominioVO> buscarEnderecoDominioPorId(@PathVariable(value="id") Long id) throws Exception{
		
		log.info("> INICIO buscarEnderecoDominioPorId: {}", id);
		
		ResponseEntity<EnderecoDominioVO> retorno = ResponseEntity.ok(enderecoService.buscarEnderecoDominio(id));
		
		return retorno;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca todos os domínios de endereço", response = EnderecoDominioVO.class)
	@GetMapping(value = "/v1/endereco/dominio")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<List<EnderecoDominioVO>> buscarTodosEnderecoDominio() throws Exception{
		
		log.info("> INICIO buscarTodosEnderecoDominio");
		
		ResponseEntity<List<EnderecoDominioVO>> retorno = ResponseEntity.ok(enderecoService.buscarTodosEnderecoDominio());
		
		return retorno;
	}
	
}
