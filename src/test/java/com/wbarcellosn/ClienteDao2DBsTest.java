package com.wbarcellosn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wbarcellosn.dao.ClienteDAODB1;
import com.wbarcellosn.dao.ClienteDAODB2;
import com.wbarcellosn.dao.IClienteDAO;
import com.wbarcellosn.domain.Cliente;
import com.wbarcellosn.exceptions.DAOException;
import com.wbarcellosn.exceptions.MaisDeUmRegistroException;
import com.wbarcellosn.exceptions.TableException;
import com.wbarcellosn.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDao2DBsTest {

	private IClienteDAO<Cliente> clienteDao;

	private IClienteDAO<Cliente> clienteDao2;
	
	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;
	private Cliente cliente4;


	private Random ramdom;

	public ClienteDao2DBsTest() {
		this.clienteDao = new ClienteDAODB1();
		this.clienteDao2 = new ClienteDAODB2();

		ramdom = new Random();
	}
	
	@Before
	public void init() {
		cliente1 = criarCliente();
		cliente2 = criarCliente();
		cliente3 = criarCliente();
		cliente4 = criarCliente();
	}

	@After
	public void end() throws DAOException {
		Collection<Cliente> list1 = clienteDao.buscarTodos();
		excluir1(list1);

		Collection<Cliente> list2 = clienteDao2.buscarTodos();
		excluir2(list2);
		
	}

	private void excluir1(Collection<Cliente> list) {
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		});
	}

	private void excluir2(Collection<Cliente> list) {
		list.forEach(cli -> {
			try {
				clienteDao2.excluir(cli);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void pesquisarClientes() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		
		clienteDao.cadastrar(cliente1);

		Cliente clienteConsultado = clienteDao.consultar(cliente1.getId());
		assertNotNull(clienteConsultado);

		clienteDao2.cadastrar(cliente2);

		Cliente clienteConsultado2 = clienteDao2.consultar(cliente2.getId());
		assertNotNull(clienteConsultado2);

	}
	
	@Test
	public void salvarClientes() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		
		clienteDao.cadastrar(cliente1);

		Cliente clienteConsultado = clienteDao.consultar(cliente1.getId());
		assertNotNull(clienteConsultado);
	
		clienteDao2.cadastrar(cliente2);
	
		Cliente clienteConsultado2 = clienteDao2.consultar(cliente2.getId());
		assertNotNull(clienteConsultado2);

	}
	
	@Test
	public void excluirClientes() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		
		//Cadastro no banco
		Cliente retorno = clienteDao.cadastrar(cliente1);
		assertNotNull(retorno);

		Cliente clienteConsultado = clienteDao.consultar(retorno.getId());
		assertNotNull(clienteConsultado);
		
		Cliente retorno2 = clienteDao2.cadastrar(cliente2);
		assertNotNull(retorno2);
		
		Cliente clienteConsultado2 = clienteDao2.consultar(retorno2.getId());
		assertNotNull(clienteConsultado2);

		//Exclusão
		clienteDao.excluir(cliente1);
		clienteConsultado = clienteDao.consultar(cliente1.getId());
		assertNull(clienteConsultado);
		
		clienteDao2.excluir(cliente2);
		clienteConsultado2 = clienteDao2.consultar(cliente2.getId());
		assertNull(clienteConsultado2);
		
	}

	@Test
	public void alterarClientes() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		
		//Cliente 1
		//Cadastrando no banco
		Cliente retorno = clienteDao.cadastrar(cliente1);
		assertNotNull(retorno);

		//Alterando o sobrenome
		cliente1.setSobrenome("Barcellos Nunes");
		clienteDao.alterar(cliente1);

		//Testando a alteração
		Cliente cliente1Alterado = clienteDao.consultar(cliente1.getId());
		assertNotNull(cliente1Alterado);
		assertEquals("Barcellos Nunes", cliente1Alterado.getSobrenome());

		//Cliente 2
		//Cadastrando no banco
		Cliente retorno2 = clienteDao2.cadastrar(cliente2);
		assertNotNull(retorno2);
		
		//Alterando o sobrenome
		cliente2.setSobrenome("Barcellos Nunes");
		clienteDao2.alterar(cliente2);
		
		//Testando a alteração
		Cliente cliente2Alterado = clienteDao2.consultar(cliente2.getId());
		assertNotNull(cliente2Alterado);
		assertEquals("Barcellos Nunes", cliente2Alterado.getSobrenome());	

	}

	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {

		//Banco de dados 1
		Cliente retorno1 = clienteDao.cadastrar(cliente1);
		assertNotNull(retorno1);

		Cliente retorno2 = clienteDao.cadastrar(cliente2);
		assertNotNull(retorno2);

		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		//Banco de dados 2
		Cliente retorno3 = clienteDao2.cadastrar(cliente3);
		assertNotNull(retorno3);
		
		Cliente retorno4 = clienteDao2.cadastrar(cliente4);
		assertNotNull(retorno4);
		
		Collection<Cliente> list2 = clienteDao2.buscarTodos();
		assertTrue(list2 != null);
		assertTrue(list2.size() == 2);

	}
	
	private Cliente criarCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf(ramdom.nextLong());
		cliente.setNome("Winicius");
		cliente.setSobrenome("Barcellos");
		cliente.setCidade("Vitória");
		cliente.setEndereco("Rua dos perdidos");
		cliente.setEstado("ES");
		cliente.setNumero(10);
		cliente.setTelefone(27987654321L);
		return cliente;
	}


}
