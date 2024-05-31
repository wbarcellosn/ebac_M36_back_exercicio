package com.wbarcellosn.dao;

import com.wbarcellosn.dao.generic.IGenericDAO;
import com.wbarcellosn.domain.Venda;
import com.wbarcellosn.exceptions.DAOException;
import com.wbarcellosn.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, Long> {

    void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;

    void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;

	Venda consultarComCollection(Long id);
}
