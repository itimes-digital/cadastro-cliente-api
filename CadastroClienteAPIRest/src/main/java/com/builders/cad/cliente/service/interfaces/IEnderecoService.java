package com.builders.cad.cliente.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.builders.cad.cliente.vo.EnderecoDominioVO;
import com.builders.cad.cliente.vo.EnderecoVO;

public interface IEnderecoService {
	
	public EnderecoVO salvarEndereco(EnderecoVO enderecoVO);
	
	public EnderecoVO atualizarEndereco(EnderecoVO enderecoVO);
	
	public void excluirEndereco(Long id);
	
	public EnderecoVO buscarEndereco(Long id);
	
	public Page<EnderecoVO> buscarTodosEnderecosPorPaginacao(Pageable pageable);
	
	public EnderecoDominioVO salvarEnderecoDominio(EnderecoDominioVO enderecoDominioVO);
	
	public EnderecoDominioVO atualizarEnderecoDominio(EnderecoDominioVO enderecoDominioVO);
	
	public void excluirEnderecoDominio(Long id);
	
	public EnderecoDominioVO buscarEnderecoDominio(Long id);
	
	public List<EnderecoDominioVO> buscarTodosEnderecoDominio();

}
