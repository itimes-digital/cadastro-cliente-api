package com.builders.cad.cliente.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.builders.cad.cliente.service.interfaces.IClienteService;
import com.builders.cad.cliente.vo.ClienteVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Api(value = "Cadastro Cliente Endpoint", tags = {"cliente"})
@RestController
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	/**
	 * 
	 * @param clienteVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Salva dados de um Cliente completo, junto com Contato e Endereço.", response = ClienteVO.class)
	@PostMapping(value = "/v1/cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ClienteVO> salvarCliente(@Valid @RequestBody ClienteVO clienteVO) throws Exception{

		log.info("> INICIO salvarCliente");
		
		ClienteVO salvarCliente 				= null;
		ResponseEntity<ClienteVO> bodyRetorno 	= null;

		salvarCliente = clienteService.salvarCliente(clienteVO);
		bodyRetorno = ResponseEntity.status(HttpStatus.CREATED).body(salvarCliente);
		
		return bodyRetorno;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca Cliente a partir de um ID.", response = ClienteVO.class)
	@GetMapping(value = "/v1/cliente/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ClienteVO> buscarPorId(@PathVariable(value="id") Long id) throws Exception{
		
		log.info("> INICIO buscarPorId: {}", id);
		
		ResponseEntity<ClienteVO> retorno = ResponseEntity.ok(clienteService.buscarCliente(id));
		
		return retorno;
	}
	
	/**
	 * 
	 * @param nome
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca Cliente a partir de um nome. O parâmetro deve conter no mínimo 4 caracteres", response = ClienteVO.class)
	@GetMapping(value = "/v1/cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ClienteVO> buscarPorNome(@RequestParam(name = "nome") String nome) throws Exception{
		
		log.info("> INICIO buscarPorNome: {}", nome);
		
		ResponseEntity<ClienteVO> retorno = ResponseEntity.ok(clienteService.buscarClientePorNome(nome));
		
		return retorno;
	}
	
	/**
	 * 
	 * @param cpfCnpj
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca Cliente a partir de um CPF ou CNPJ. O parâmetro deve conter apenas números", response = ClienteVO.class)
	@GetMapping(value = "/v1/cliente/documento/{cpfCnpj}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ClienteVO> buscarPorDocumento(@RequestParam(name = "cpfCnpj") String cpfCnpj) throws Exception{
		
		log.info("> INICIO buscarPorDocumento: {}", cpfCnpj);
		
		ResponseEntity<ClienteVO> retorno = ResponseEntity.ok(clienteService.buscarClientePorCpfCnpj(cpfCnpj));
		
		return retorno;
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param assembler
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca uma lista de Clientes por paginação.", response = ClienteVO.class)
	@GetMapping(value = "/v1/cliente/todos")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<PagedResources<ClienteVO>> buscarTodosClientesPorPaginacao(
									@RequestParam(value = "page", defaultValue = "0") Short page,
									@RequestParam(value = "size", defaultValue = "10") Short size,
									 PagedResourcesAssembler assembler) throws Exception{

		log.info("> INICIO buscarTodosClientesPorPaginacao");
		
		Pageable pageable = PageRequest.of(page, size);
		Page<ClienteVO> pageClienteVO = clienteService.buscarTodosClientesPorPaginacao(pageable);
		
		//ajuste final do hateoas
		pageClienteVO.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(ClienteController.class).buscarPorId(p.getKey())).withSelfRel());
			} catch (Exception ex) {
				log.warn("> ERRO montagem dos links hateoas.");
			}
		});

		return new ResponseEntity<PagedResources<ClienteVO>>(assembler.toResource(pageClienteVO), HttpStatus.OK);
	}

	/**
	 * 
	 * @param clienteVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Atualiza os dados do Cliente. Não atualiza dados de Contato e Endereço", response = ClienteVO.class)
	@PutMapping(value = "/v1/cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ClienteVO atualizarCliente(@RequestBody ClienteVO clienteVO) throws Exception{

		log.info("> INICIO atualizarCliente");
		
		return clienteService.atualizarCliente(clienteVO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Exclui um Cliente da base de dados, junto com o Contato e Endereço.")
	@DeleteMapping(value = "/v1/cliente/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public ResponseEntity<?> excluirClientePorId(@PathVariable(value="id") Long id) throws Exception{

		log.info("> INICIO excluirClientePorId");
		
		clienteService.excluirCliente(id);
		
		return ResponseEntity.ok().build();
	}
	
}
