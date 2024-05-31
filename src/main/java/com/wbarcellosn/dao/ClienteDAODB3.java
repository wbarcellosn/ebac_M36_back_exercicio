/**
 * @author winic
 */
package com.wbarcellosn.dao;

import com.wbarcellosn.dao.generic.GenericDB3DAO;
import com.wbarcellosn.domain.Cliente;

public class ClienteDAODB3 extends GenericDB3DAO<Cliente, Long> implements IClienteDAO<Cliente> {

	public ClienteDAODB3() {
		super(Cliente.class);
	}

}
