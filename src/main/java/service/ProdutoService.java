package service;

import java.util.List;

import entities.Produto;
import filters.ProdutoFilter;



public interface ProdutoService {

	void save(Produto produto) throws ServiceDacException;

	void update(Produto produto) throws ServiceDacException;

	void delete(Produto produto) throws ServiceDacException;

	Produto getByID(int produtoId) throws ServiceDacException;

	List<Produto> getAll() throws ServiceDacException;

	List<Produto> findBy(ProdutoFilter filter) throws ServiceDacException;
}
