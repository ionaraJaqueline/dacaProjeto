package impl;

import java.io.Serializable;
import java.util.List;

import dao.PedidoDAO;
import dao.PersistenciaDacException;
import daoImplDataBase.PedidoInDatabaseDAO;


import entities.Pedido;
import filters.PedidoFilter;
import service.PedidoService;
import service.ServiceDacException;

public class PedidoServiceImpl implements Serializable, PedidoService {
	private static final long serialVersionUID = -7803325791425670859L;
	private PedidoDAO pedidoDAO = new PedidoInDatabaseDAO();

	@Override
	public void save(Pedido pedido) throws ServiceDacException {
		try {
			pedidoDAO.save(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Pedido pedido) throws ServiceDacException {
		try {

			pedidoDAO.update(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}


	@Override
	public void delete(Pedido pedido) throws ServiceDacException {
		try {
			pedidoDAO.delete(pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	@Override
	public Pedido getByID(int pedidoId) throws ServiceDacException {
		try {
			return pedidoDAO.getByID(pedidoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Pedido> getAll() throws ServiceDacException {
		try {
			return pedidoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Pedido> findBy(PedidoFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return pedidoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
