/**
 * @author winic
 */
package com.wbarcellosn.service;

import com.wbarcellosn.domain.Cliente;
import com.wbarcellosn.exceptions.DAOException;
import com.wbarcellosn.service.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {

    Cliente buscarPorCPF(Long cpf) throws DAOException;
}
