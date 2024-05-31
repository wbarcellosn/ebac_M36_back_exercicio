package com.wbarcellosn.dao.generic;

import java.io.Serializable;

import com.wbarcellosn.dao.Persistente;

public class GenericDB2DAO <T extends Persistente, E extends Serializable> extends GenericDAO<T, E> {

	public GenericDB2DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre-2");
	}	
}