package dao;

import java.util.List;


import entities.Produto;
import filters.ProdutoFilter;


public interface ProdutoDAO {
	void save(Produto obj) throws PersistenciaDacException;

	Produto update(Produto obj) throws PersistenciaDacException;

	void delete(Produto obj) throws PersistenciaDacException;

	Produto getByID(int objId) throws PersistenciaDacException;

	List<Produto> getAll() throws PersistenciaDacException;

	List<Produto> findBy(ProdutoFilter filter) throws PersistenciaDacException;
	
	
	

}
