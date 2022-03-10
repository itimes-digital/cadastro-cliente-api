package com.builders.cad.cliente.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.builders.cad.cliente.vo.ClienteVO;

public interface IClienteService {
	
	public ClienteVO salvarCliente(ClienteVO clienteVO);
	
	public ClienteVO atualizarCliente(ClienteVO clienteVO);
	
	public void excluirCliente(Long id);
	
	public ClienteVO buscarCliente(Long id);
	
	public ClienteVO buscarClientePorNome(String nome);
	
	public ClienteVO buscarClientePorCpfCnpj(String cpfCnpj);
	
	public Page<ClienteVO> buscarTodosClientesPorPaginacao(Pageable pageable);

}
