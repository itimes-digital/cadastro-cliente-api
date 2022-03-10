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
import com.builders.cad.cliente.enums.TipoContatoEnum;
import com.builders.cad.cliente.enums.TipoPessoaEnum;
import com.builders.cad.cliente.model.ClienteContatoDominioModel;
import com.builders.cad.cliente.model.ClienteContatoModel;
import com.builders.cad.cliente.model.ClienteModel;
import com.builders.cad.cliente.repository.ClienteRepository;
import com.builders.cad.cliente.repository.ContatoDominioRepository;
import com.builders.cad.cliente.repository.ContatoRepository;
import com.builders.cad.cliente.service.handler.ContatoHandler;
import com.builders.cad.cliente.service.interfaces.IContatoService;
import com.builders.cad.cliente.vo.ContatoVO;

@SpringBootTest
public class ContatoServiceTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	private ContatoRepository contatoRepository;
	
	@Mock
	private ContatoDominioRepository contatoDominioRepository;
	
	@InjectMocks
	private IContatoService contatoService = new ContatoServiceImpl(new ContatoHandler());
	
	@BeforeEach
	public void setup() {
		
		ClienteContatoModel contato = new ClienteContatoModel();
		contato.setIdClienteContato(1L);
		contato.setNumTelefone("11969236896");
		contato.setEmail("elvis@gmail.com");
		contato.setAtivo(StatusEnum.ATIVO.getTipoStatusAtivo().byteValue());
		contato.setContatoDominio(new ClienteContatoDominioModel(1L));
		
		when(contatoRepository.save(contato)).thenReturn(contato);
		
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
		
		when(contatoRepository.findById(1l)).thenReturn(Optional.of(contato));

	}
	
	@Test
	void testCriarContatoComSucesso() {
		
		ContatoVO contato = new ContatoVO();
		contato.setIdCliente(1L);
		contato.setNumTelefone("11969236896");
		contato.setEmail("elvis@gmail.com");
		contato.setContatoEnum(TipoContatoEnum.CONTATO_RESIDENCIAL);

		assertEquals(contato, contatoService.salvarContato(contato));
	}
	
	@Test
	void testbuscarContatoResidencialComSucesso() {

		ContatoVO buscarContato = contatoService.buscarContato(1l);
		
		assertEquals(StatusEnum.ATIVO, buscarContato.getStatusEnum());
	}
	
	@Test
	void testbuscarContatoResidencialQueNaoExiste() {
		
		try {
			ContatoVO buscarContato = contatoService.buscarContato(10l);
			
			assertNotEquals(StatusEnum.ATIVO, buscarContato.getStatusEnum());
		}catch(Exception e) {
			assertEquals("No records found for this ID.", e.getMessage());	
		}
		
	}
}
