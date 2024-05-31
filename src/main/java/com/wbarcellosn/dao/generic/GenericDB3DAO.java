package com.wbarcellosn.dao.generic;

import java.io.Serializable;

import com.wbarcellosn.dao.Persistente;

public class GenericDB3DAO<T extends Persistente, E extends Serializable> extends GenericDAO<T, E> {

	public GenericDB3DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Mysql1");
	}	
}