package com.builders.cad.cliente.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import com.builders.cad.cliente.service.interfaces.IEnderecoService;
import com.builders.cad.cliente.vo.EnderecoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Api(value = "Endereco Cliente Endpoint", tags = {"endereco"})
@RestController
public class EnderecoController {
	
	@Autowired
	private IEnderecoService enderecoService;

	/**
	 * 
	 * @param enderecoVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Salva dados de um Endereço", response = EnderecoVO.class)
	@PostMapping(value = "/v1/endereco")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<EnderecoVO> salvarEndereco(@RequestBody EnderecoVO enderecoVO) throws Exception{

		log.info("> INICIO salvarEndereco");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvarEndereco(enderecoVO));
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca Endereco a partir de um ID.", response = EnderecoVO.class)
	@GetMapping(value = "/v1/endereco/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<EnderecoVO> buscarPorId(@PathVariable(value="id") Long id) throws Exception{
		
		log.info("> INICIO buscarPorId: {}", id);
		
		ResponseEntity<EnderecoVO> retorno = ResponseEntity.ok(enderecoService.buscarEndereco(id));
		
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
	@ApiOperation(value = "Busca uma lista de Enderecos existentes na base.", response = EnderecoVO.class)
	@GetMapping(value = "/v1/endereco/todos")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<PagedResources<EnderecoVO>> buscarTodosEnderecosPorPaginacao(
																	@RequestParam(value = "page", defaultValue = "0") Short page,
																	@RequestParam(value = "size", defaultValue = "10") Short size,
																	 PagedResourcesAssembler assembler) throws Exception{

		log.info("> INICIO buscarTodosEnderecosPorPaginacao");
		
		Pageable pageable = PageRequest.of(page, size);
		Page<EnderecoVO> pageEnderecoVO = enderecoService.buscarTodosEnderecosPorPaginacao(pageable);
		
		//ajuste final do hateoas
		pageEnderecoVO.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(EnderecoController.class).buscarPorId(p.getKey())).withSelfRel());
			} catch (Exception ex) {
				log.warn("> ERRO montagem dos links hateoas.");
			}
		});

		return new ResponseEntity<PagedResources<EnderecoVO>>(assembler.toResource(pageEnderecoVO), HttpStatus.OK);
	}

	/**
	 * 
	 * @param enderecoVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Atualiza os dados de Endereço de um Cliente na base de dados.", response = EnderecoVO.class)
	@PutMapping(value = "/v1/endereco")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody EnderecoVO atualizarEndereco(@RequestBody EnderecoVO enderecoVO) throws Exception{

		log.info("> INICIO atualizarEndereco");
		
		return enderecoService.atualizarEndereco(enderecoVO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Exclui um Endereço de um Cliente da base de dados.")
	@DeleteMapping(value = "/v1/endereco/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public ResponseEntity<?> excluirEnderecoPorId(@PathVariable(value="id") Long id) throws Exception{

		log.info("> INICIO excluirEnderecoPorId");
		
		enderecoService.excluirEndereco(id);
		
		return ResponseEntity.ok().build();
	}
	
}
