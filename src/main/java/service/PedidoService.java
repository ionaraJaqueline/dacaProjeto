package service;

import java.util.List;

import entities.Pedido;
import filters.PedidoFilter;



public interface PedidoService {
	void save(Pedido pedido) throws ServiceDacException;

	void  update(Pedido pedido) throws ServiceDacException;

	void delete(Pedido pedido) throws ServiceDacException;

	Pedido getByID(int pedidoId) throws ServiceDacException;

	List<Pedido> getAll() throws ServiceDacException;

	List<Pedido> findBy(PedidoFilter filter) throws ServiceDacException;
}
