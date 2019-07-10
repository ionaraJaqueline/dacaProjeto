package impl;

import java.io.Serializable;
import java.util.List;

import dao.PersistenciaDacException;
import dao.ProdutoDAO;
import daoImplDataBase.ProdutoInDatabaseDAO;
import entities.Produto;
import filters.ProdutoFilter;
import service.ProdutoService;
import service.ServiceDacException;

public class ProdutoServiceImpl implements Serializable, ProdutoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;

	private ProdutoDAO produtoDAO = new ProdutoInDatabaseDAO();

	@Override
	public void save(Produto produto) throws ServiceDacException {
		try {
			produtoDAO.save(produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	@Override
	public  void update(Produto produto) throws ServiceDacException {
		try {

			produtoDAO.update(produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(Produto produto) throws ServiceDacException {
		try {
			produtoDAO.delete(produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Produto getByID(int produtoId) throws ServiceDacException {
		try {
			return produtoDAO.getByID(produtoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Produto> getAll() throws ServiceDacException {
		try {
			return produtoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Produto> findBy(ProdutoFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return produtoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
