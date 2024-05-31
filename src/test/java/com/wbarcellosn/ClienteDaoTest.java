package com.wbarcellosn;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Test;

import com.wbarcellosn.dao.ClienteDAODB1;
import com.wbarcellosn.dao.IClienteDAO;
import com.wbarcellosn.domain.Cliente;
import com.wbarcellosn.exceptions.DAOException;
import com.wbarcellosn.exceptions.MaisDeUmRegistroException;
import com.wbarcellosn.exceptions.TableException;
import com.wbarcellosn.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDaoTest {

	private IClienteDAO<Cliente> clienteDao;

	private Random ramdom;

	public ClienteDaoTest() {
		this.clienteDao = new ClienteDAODB1();
		ramdom = new Random();
	}

	@After
	public void end() throws DAOException {
		Collection<Cliente> list = clienteDao.buscarTodos();
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void pesquisarCliente()
			throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		Cliente cliente = criarCliente();
		clienteDao.cadastrar(cliente);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);

	}

	@Test
	public void salvarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);

		Cliente clienteConsultado = clienteDao.consultar(retorno.getId());
		assertNotNull(clienteConsultado);

		clienteDao.excluir(cliente);

		Cliente clienteConsultado1 = clienteDao.consultar(retorno.getId());
		assertNull(clienteConsultado1);
	}

	@Test
	public void excluirCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);

		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNull(clienteConsultado);
	}

	@Test
	public void alterarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);

		clienteConsultado.setSobrenome("Barcellos Nunes");
		clienteDao.alterar(clienteConsultado);

		Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
		assertNotNull(clienteAlterado);
		assertEquals("Barcellos Nunes", clienteAlterado.getSobrenome());

		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(clienteAlterado.getId());
		assertNull(clienteConsultado);
	}

	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);

		Cliente cliente1 = criarCliente();
		Cliente retorno1 = clienteDao.cadastrar(cliente1);
		assertNotNull(retorno1);

		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);

		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		});

		Collection<Cliente> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
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
