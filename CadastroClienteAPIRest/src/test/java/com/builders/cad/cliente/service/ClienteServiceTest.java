package com.builders.cad.cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.enums.TipoContatoEnum;
import com.builders.cad.cliente.enums.TipoEnderecoEnum;
import com.builders.cad.cliente.enums.TipoPessoaEnum;
import com.builders.cad.cliente.model.ClienteModel;
import com.builders.cad.cliente.repository.ClienteRepository;
import com.builders.cad.cliente.service.handler.ClienteHandler;
import com.builders.cad.cliente.service.handler.ContatoHandler;
import com.builders.cad.cliente.service.handler.EnderecoHandler;
import com.builders.cad.cliente.service.interfaces.IClienteService;
import com.builders.cad.cliente.vo.ClienteVO;
import com.builders.cad.cliente.vo.ContatoVO;
import com.builders.cad.cliente.vo.EnderecoVO;

@SpringBootTest
public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
	private IClienteService clienteService = new ClienteServiceImpl(new ClienteHandler(), 
																	new ContatoHandler(), 
																	new EnderecoHandler());
	
	@BeforeEach
	public void setup() {
		
		ClienteModel cliente = new ClienteModel();
		cliente.setIdCliente(1L);
		cliente.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getTipoPessoa().charAt(0));
		cliente.setCpfCnpj("274.839.880-70");
		cliente.setDataNascimento(LocalDate.parse("1979-10-05"));
		cliente.setPrimeiroNome("Elvis");
		cliente.setSegundoNome("Aaron Presley");
		cliente.setNickname("elvis");
		cliente.setAtivo(StatusEnum.ATIVO.getTipoStatusAtivo().byteValue());
		
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		
		when(clienteRepository.findByDocument("27483988070")).thenReturn(cliente);
		
		when(clienteRepository.findByName("elvi")).thenReturn(cliente);

	}
	
	@Test
	void testCriarClienteComSucesso() {
		
		ClienteVO cliente = new ClienteVO();

		cliente.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getTipoPessoa());
		cliente.setCpf("274.839.880-70");
		cliente.setDataNascimento("1979-10-05");
		cliente.setPrimeiroNome("Elvis");
		cliente.setSegundoNome("Aaron Presley");
		cliente.setNickname("elvis");
		
		assertEquals(cliente, clienteService.salvarCliente(cliente));
	}
	
	@Test
	void testCriarClienteContatoComSucesso() {
		
		ClienteVO cliente = new ClienteVO();
		cliente.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getTipoPessoa());
		cliente.setCpf("274.839.880-70");
		cliente.setDataNascimento("1979-10-05");
		cliente.setPrimeiroNome("Elvis");
		cliente.setSegundoNome("Aaron Presley");
		cliente.setNickname("elvis");
		
		ContatoVO clienteContato = new ContatoVO();
		clienteContato.setNumTelefone("11969236896");
		clienteContato.setEmail("elvis@gmail.com");
		clienteContato.setContatoEnum(TipoContatoEnum.CONTATO_RESIDENCIAL);
		
		cliente.setContato(clienteContato);
		
		assertEquals(cliente, clienteService.salvarCliente(cliente));
	}
	
	@Test
	void testCriarClienteEnderecoComSucesso() {
		
		ClienteVO cliente = new ClienteVO();
		cliente.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getTipoPessoa());
		cliente.setCpf("274.839.880-70");
		cliente.setDataNascimento("1979-10-05");
		cliente.setPrimeiroNome("Elvis");
		cliente.setSegundoNome("Aaron Presley");
		cliente.setNickname("elvis");
		cliente.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getTipoPessoa());
		
		EnderecoVO clienteEndereco = new EnderecoVO();
		clienteEndereco.setCaixaPostal("12583 - SP");
		clienteEndereco.setCidade("São Paulo");
		clienteEndereco.setEstado("SP");
		clienteEndereco.setLogradouro("Av Wolfgang Amadeu Mozart");
		clienteEndereco.setNumLogradouro(1756);
		clienteEndereco.setNumCep("02101000");
		clienteEndereco.setPais("BR");
		clienteEndereco.setEnderecoEnum(TipoEnderecoEnum.ENDERECO_RESIDENCIAL);
		
		cliente.setEndereco(clienteEndereco);

		assertEquals(cliente, clienteService.salvarCliente(cliente));
	}
	
	@Test
	void testBuscarClientePorCpfComSucesso() {

		ClienteVO buscarClientePorCpfCnpj = clienteService.buscarClientePorCpfCnpj("27483988070");
		assertEquals("27483988070", buscarClientePorCpfCnpj.getCpf());	
		
	}
	
	@Test
	void testBuscarClientePorCpfQueNaoExiste() {

		try {
			ClienteVO buscarClientePorCpfCnpj = clienteService.buscarClientePorCpfCnpj("1234");
			assertEquals("124", buscarClientePorCpfCnpj.getCpf());	
		}catch(Exception e) {
			assertEquals("No records found for this ID.", e.getMessage());	
		}
		
	}
	
	@Test
	void testBuscarClientePorNomeComSucesso() {

		ClienteVO clienteNome = clienteService.buscarClientePorNome("elvi");
		assertEquals("Aaron Presley", clienteNome.getSegundoNome());	
		
	}
	
	@Test
	void testBuscarClientePorNomeQueNaoExiste() {

		try {
			ClienteVO clienteNome = clienteService.buscarClientePorNome("James");
			assertEquals("James", clienteNome.getPrimeiroNome());	
		}catch(Exception e) {
			assertEquals("No records found for this ID.", e.getMessage());	
		}
		
	}
}
