package com.wbarcellosn.dao;

import com.wbarcellosn.dao.generic.IGenericDAO;
import com.wbarcellosn.domain.Cliente;

public interface IClienteDAO<T extends Persistente> extends IGenericDAO<Cliente, Long> {


}
