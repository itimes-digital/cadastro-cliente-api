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

import com.builders.cad.cliente.service.interfaces.IContatoService;
import com.builders.cad.cliente.vo.ContatoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Api(value = "Contato Cliente Endpoint", tags = {"contato"})
@RestController
public class ContatoController {
	
	@Autowired
	private IContatoService contatoService;
	
	/**
	 * 
	 * @param contatoVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Salva dados de um Contato", response = ContatoVO.class)
	@PostMapping(value = "/v1/contato")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ContatoVO> salvarContato(@RequestBody ContatoVO contatoVO) throws Exception{

		log.info("> INICIO salvarContato");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.salvarContato(contatoVO));
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca Contato a partir de um ID.", response = ContatoVO.class)
	@GetMapping(value = "/v1/contato/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<ContatoVO> buscarPorId(@PathVariable(value="id") Long id) throws Exception{
		
		log.info("> INICIO buscarPorId: {}", id);
		
		ResponseEntity<ContatoVO> retorno = ResponseEntity.ok(contatoService.buscarContato(id));
		
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
	@ApiOperation(value = "Busca uma lista de Contatos existentes na base.", response = ContatoVO.class)
	@GetMapping(value = "/v1/contato/todos")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<PagedResources<ContatoVO>> buscarTodosContatosPorPaginacao(
																	@RequestParam(value = "page", defaultValue = "0") Short page,
																	@RequestParam(value = "size", defaultValue = "10") Short size,
																	 PagedResourcesAssembler assembler) throws Exception{

		log.info("> INICIO buscarTodosContatosPorPaginacao");
		
		Pageable pageable = PageRequest.of(page, size);
		Page<ContatoVO> pageContatoVO = contatoService.buscarTodosContatosPorPaginacao(pageable);
		
		//ajuste final do hateoas
		pageContatoVO.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(ContatoController.class).buscarPorId(p.getKey())).withSelfRel());
			} catch (Exception ex) {
				log.warn("> ERRO montagem dos links hateoas.");
			}
		});

		return new ResponseEntity<PagedResources<ContatoVO>>(assembler.toResource(pageContatoVO), HttpStatus.OK);
	}

	/**
	 * 
	 * @param contatoVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Atualiza os dados de Contato de um Cliente na base de dados.", response = ContatoVO.class)
	@PutMapping(value = "/v1/contato")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ContatoVO atualizarContato(@RequestBody ContatoVO contatoVO) throws Exception{

		log.info("> INICIO atualizarContato");
		
		return contatoService.atualizarContato(contatoVO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Exclui um Contato de um Cliente da base de dados.")
	@DeleteMapping(value = "/v1/contato/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 201, message = "Registro criado"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public ResponseEntity<?> excluirContatoPorId(@PathVariable(value="id") Long id) throws Exception{

		log.info("> INICIO excluirContatoPorId");
		
		contatoService.excluirContato(id);
		
		return ResponseEntity.ok().build();
	}
}
