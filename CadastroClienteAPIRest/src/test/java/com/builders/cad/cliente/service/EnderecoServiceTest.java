package com.builders.cad.cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.builders.cad.cliente.enums.StatusEnum;
import com.builders.cad.cliente.enums.TipoEnderecoEnum;
import com.builders.cad.cliente.enums.TipoPessoaEnum;
import com.builders.cad.cliente.model.ClienteEnderecoDominioModel;
import com.builders.cad.cliente.model.ClienteEnderecoModel;
import com.builders.cad.cliente.model.ClienteModel;
import com.builders.cad.cliente.repository.ClienteRepository;
import com.builders.cad.cliente.repository.EnderecoDominioRepository;
import com.builders.cad.cliente.repository.EnderecoRepository;
import com.builders.cad.cliente.service.handler.EnderecoHandler;
import com.builders.cad.cliente.service.interfaces.IEnderecoService;
import com.builders.cad.cliente.vo.EnderecoVO;

@SpringBootTest
public class EnderecoServiceTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	private EnderecoRepository enderecoRepository;
	
	@Mock
	private EnderecoDominioRepository enderecoDominioRepository;
	
	@InjectMocks
	private IEnderecoService enderecoService = new EnderecoServiceImpl(new EnderecoHandler());
	
	@BeforeEach
	public void setup() {
		
		ClienteEnderecoModel clienteEndereco = new ClienteEnderecoModel();
		clienteEndereco.setCaixaPostal("12583 - SP");
		clienteEndereco.setCidade("São Paulo");
		clienteEndereco.setEstado("SP");
		clienteEndereco.setLogradouro("Av Wolfgang Amadeu Mozart");
		clienteEndereco.setNumLogradouro(1756);
		clienteEndereco.setNumCep("02101000");
		clienteEndereco.setPais("BR");
		clienteEndereco.setEnderecoDominio(new ClienteEnderecoDominioModel(1L));
		clienteEndereco.setAtivo(StatusEnum.ATIVO.getTipoStatusAtivo().byteValue());
		
		when(enderecoRepository.save(clienteEndereco)).thenReturn(clienteEndereco);
		
		ClienteModel cliente = new ClienteModel();
		cliente.setIdCliente(1L);
		cliente.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getTipoPessoa().charAt(0));
		cliente.setCpfCnpj("274.839.880-70");
		cliente.setDataNascimento(LocalDate.parse("1979-10-05"));
		cliente.setPrimeiroNome("Elvis");
		cliente.setSegundoNome("Aaron Presley");
		cliente.setNickname("elvis");
		cliente.setAtivo(StatusEnum.ATIVO.getTipoStatusAtivo().byteValue());
		
		when(clienteRepository.findById(cliente.getIdCliente())).thenReturn(Optional.of(cliente));
		
		when(enderecoRepository.findById(1l)).thenReturn(Optional.of(clienteEndereco));

	}
	
	@Test
	void testCriarEnderecoComSucesso() {
		
		EnderecoVO clienteEndereco = new EnderecoVO();
		clienteEndereco.setIdCliente(1L);
		clienteEndereco.setCaixaPostal("12583 - SP");
		clienteEndereco.setCidade("São Paulo");
		clienteEndereco.setEstado("SP");
		clienteEndereco.setLogradouro("Av Wolfgang Amadeu Mozart");
		clienteEndereco.setNumLogradouro(1756);
		clienteEndereco.setNumCep("02101000");
		clienteEndereco.setPais("BR");
		clienteEndereco.setEnderecoEnum(TipoEnderecoEnum.ENDERECO_RESIDENCIAL);

		assertEquals(clienteEndereco, enderecoService.salvarEndereco(clienteEndereco));
	}
	
	@Test
	void testbuscarEnderecoResidencialComSucesso() {

		EnderecoVO buscarEndereco = enderecoService.buscarEndereco(1l);
		
		assertEquals(StatusEnum.ATIVO, buscarEndereco.getStatusEnum());
	}
	
	@Test
	void testbuscarEnderecoResidencialQueNaoExiste() {
		
		try {
			EnderecoVO buscarEndereco = enderecoService.buscarEndereco(10l);
			
			assertNotEquals(StatusEnum.ATIVO, buscarEndereco.getStatusEnum());
		}catch(Exception e) {
			assertEquals("No records found for this ID.", e.getMessage());	
		}
		
	}
}
