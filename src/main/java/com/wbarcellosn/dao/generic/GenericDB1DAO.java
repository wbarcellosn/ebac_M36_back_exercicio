package com.wbarcellosn.dao.generic;

import java.io.Serializable;

import com.wbarcellosn.dao.Persistente;

public abstract class GenericDB1DAO<T extends Persistente, E extends Serializable> extends GenericDAO<T, E> {

	public GenericDB1DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre-1");
	}

	
}
