package com.wbarcellosn.dao;

import com.wbarcellosn.dao.generic.GenericDB1DAO;
import com.wbarcellosn.domain.Venda;
import com.wbarcellosn.exceptions.DAOException;
import com.wbarcellosn.exceptions.TipoChaveNaoEncontradaException;

public class VendaExclusaoDAO extends GenericDB1DAO<Venda, Long> implements IVendaDAO {

	public VendaExclusaoDAO() {
		super(Venda.class);
	}

	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	public Venda consultarComCollection(Long id) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

}
