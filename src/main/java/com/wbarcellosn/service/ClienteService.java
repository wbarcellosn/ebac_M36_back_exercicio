/**
 * @author winic
 */
package com.wbarcellosn.service;

import com.wbarcellosn.dao.IClienteDAO;
import com.wbarcellosn.domain.Cliente;
import com.wbarcellosn.exceptions.DAOException;
import com.wbarcellosn.exceptions.MaisDeUmRegistroException;
import com.wbarcellosn.exceptions.TableException;
import com.wbarcellosn.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

    public ClienteService(IClienteDAO clienteDAO) {
        super(clienteDAO);
    }

    @Override
    public Cliente buscarPorCPF(Long cpf) throws DAOException {
        try {
            return this.dao.consultar(cpf);
        } catch (MaisDeUmRegistroException | TableException e) {
            e.printStackTrace();
        }
        return null;
    }
}

