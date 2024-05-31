/**
 * @author winic
 */
package com.wbarcellosn.dao;

import com.wbarcellosn.dao.generic.GenericDB1DAO;
import com.wbarcellosn.domain.Produto;

public class ProdutoDAO extends GenericDB1DAO<Produto, Long> implements IProdutoDAO {

	public ProdutoDAO() {
		super(Produto.class);
	}

}