/**
 * @author winic
 */
package com.wbarcellosn.dao;

import com.wbarcellosn.dao.generic.GenericDB1DAO;
import com.wbarcellosn.domain.Cliente;

public class ClienteDAODB1 extends GenericDB1DAO<Cliente, Long> implements IClienteDAO<Cliente> {

	public ClienteDAODB1() {
		super(Cliente.class);
	}

}
