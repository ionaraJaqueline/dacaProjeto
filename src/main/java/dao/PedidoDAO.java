package dao;

import java.util.List;

import entities.Pedido;
import filters.PedidoFilter;


public interface PedidoDAO {
	void save(Pedido obj) throws PersistenciaDacException;

	Pedido update(Pedido obj) throws PersistenciaDacException;

	void delete(Pedido obj) throws PersistenciaDacException;

	Pedido getByID(Integer objId) throws PersistenciaDacException;

	List<Pedido> getAll() throws PersistenciaDacException;

	List<Pedido> findBy(PedidoFilter filter) throws PersistenciaDacException;
}
