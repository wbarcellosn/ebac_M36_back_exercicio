/**
 * @author winic
 */
package com.wbarcellosn.dao.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.wbarcellosn.dao.Persistente;
import com.wbarcellosn.exceptions.DAOException;
import com.wbarcellosn.exceptions.MaisDeUmRegistroException;
import com.wbarcellosn.exceptions.TableException;
import com.wbarcellosn.exceptions.TipoChaveNaoEncontradaException;


public class GenericDAO <T extends Persistente, E extends Serializable> implements IGenericDAO <T,E> {

	private static final String PERSISTENCE_UNIT_NAME = "Postgre1";
	
	private String persistenceUnitName;
	
	protected EntityManagerFactory emf;
	
	protected EntityManager em;
	
	private Class<T> persistenteClass;
	
	public GenericDAO(Class<T> persistenteClass, String persistenceUnitName) {
		this.persistenteClass = persistenteClass;
		this.persistenceUnitName = persistenceUnitName;
	}
	
	@Override
	public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		em.persist(entity);
		em.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public void excluir(T entity) throws DAOException {
		openConnection();
		entity = em.merge(entity);
		em.remove(entity);
		em.getTransaction().commit();
		closeConnection();
	}

	@Override
	public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		entity = em.merge(entity);
		em.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
		openConnection();
		T entity = em.find(this.persistenteClass, valor);
		em.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public Collection<T> buscarTodos() throws DAOException {
		openConnection();
		List<T> list = em.createQuery(getSelectSql(), this.persistenteClass).getResultList();
		closeConnection();
		return list;
	}
	
	protected void openConnection() {
		emf = Persistence.createEntityManagerFactory(getPersistenceUnitName());
		em = emf.createEntityManager();
		em.getTransaction().begin(); 
	}
	
	protected void closeConnection() {
		em.close();
		emf.close();
	}
	
	private String getSelectSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT obj FROM ");
		sb.append(this.persistenteClass.getSimpleName());
		sb.append(" obj");
		return sb.toString();
	}
	
	private String getPersistenceUnitName() {
		if (persistenceUnitName != null 
				&& !"".equals(persistenceUnitName)) {
			return persistenceUnitName;
		} else {
			return PERSISTENCE_UNIT_NAME;
		}
	}


}
