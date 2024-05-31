/**
 * @author winic
 */
package com.wbarcellosn.dao;

import com.wbarcellosn.dao.generic.GenericDB2DAO;
import com.wbarcellosn.domain.Cliente;

public class ClienteDAODB2 extends GenericDB2DAO<Cliente, Long> implements IClienteDAO<Cliente> {

	public ClienteDAODB2() {
		super(Cliente.class);
	}

}
