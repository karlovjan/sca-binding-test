package cz.baros.wildfly.sy.sca.sca_binding_test.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAORepository<T extends Serializable> {

	public T find(Object id);
	
	public void persist(final T entity);
	
	public List<T> findAll();
	
	public void deleteAll();
	
	public void delete(Object id);
	
	public void update(final T entity);
	
}