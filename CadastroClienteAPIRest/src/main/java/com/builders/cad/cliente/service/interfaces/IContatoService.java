package com.builders.cad.cliente.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.builders.cad.cliente.vo.ContatoDominioVO;
import com.builders.cad.cliente.vo.ContatoVO;

public interface IContatoService {
	
	public ContatoVO salvarContato(ContatoVO contatoVO);
	
	public ContatoVO atualizarContato(ContatoVO contatoVO);
	
	public void excluirContato(Long id);
	
	public ContatoVO buscarContato(Long id);
	
	public Page<ContatoVO> buscarTodosContatosPorPaginacao(Pageable pageable);
	
	public ContatoDominioVO salvarContatoDominio(ContatoDominioVO contatoDominioVO);
	
	public ContatoDominioVO atualizarContatoDominio(ContatoDominioVO contatoDominioVO);
	
	public void excluirContatoDominio(Long id);
	
	public ContatoDominioVO buscarContatoDominio(Long id);
	
	public List<ContatoDominioVO> buscarTodosContatoDominio();
}
