package daoImplDataBase;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import dao.PedidoDAO;
import dao.PersistenciaDacException;
import entities.Pedido;
import filters.PedidoFilter;

public class PedidoInDatabaseDAO extends InDatabaseDAO implements PedidoDAO {

	@Override
	public void save(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o pedido.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Pedido update(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Pedido resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o pedido.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	@Override
	public void delete(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(Pedido.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o pedido.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Pedido getByID(Integer objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pedido resultado = null;
		try {
			resultado = em.find(Pedido.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o pedido com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	@Override
	public List<Pedido> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

	@Override
	public List<Pedido> findBy(PedidoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pedido> resultado = new ArrayList<>();
		try {

			String jpql = "SELECT ped FROM Pedido ped WHERE 1 = 1 ";

			

			
			// inicioDataDoPedido
			if (notEmpty(filter.getInicioDataDoPedido())) {
				jpql += "AND ped.inicioDataDoPedido >= :inicioDataDoPedido ";
			}
			// fimDataEntrada
			if (notEmpty(filter.getFimDataDoPedido())) {
				jpql += "AND ped.fimDataEntrada >= :fimDataEntrada ";
			}
			// valorUnitario
			if (notEmpty(filter.getValorUnitario())) {
				jpql += "AND ped.valorUnitario >= :valorUnitario ";
			}
			// valorTotal
			if (notEmpty(filter.getValorTotal())) {
				jpql += "AND ped.valorTotal >= :valorTotal ";
			}
			// Fim da Data de Saída
			if (notEmpty(filter.getTipoDePagamento())) {
				jpql += "AND ped.tipoDePagamento >= :tipoDePagamento ";
			}

			TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);

			

			

			// Inicio Data do Pedido
			if (notEmpty(filter.getInicioDataDoPedido())) {
				query.setParameter("inicioDataDoPedido", filter.getInicioDataDoPedido());
			}
			// Fim Data do Pedido
			if (notEmpty(filter.getFimDataDoPedido())) {
				query.setParameter("fimDataDoPedido", filter.getFimDataDoPedido());
			}

			

			// tipoDePagamento
			if (notEmpty(filter.getTipoDePagamento())) {
				query.setParameter("tipoDePagamento", filter.getTipoDePagamento());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os pedidos.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
