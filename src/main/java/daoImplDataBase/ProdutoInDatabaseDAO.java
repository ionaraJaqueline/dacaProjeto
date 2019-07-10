package daoImplDataBase;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import dao.PersistenciaDacException;
import dao.ProdutoDAO;
import entities.Produto;

import filters.ProdutoFilter;

public class ProdutoInDatabaseDAO extends InDatabaseDAO implements ProdutoDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Produto obj) throws PersistenciaDacException {
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
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o produto.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Produto update(Produto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Produto resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o produto.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	@Override
	public void delete(Produto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(Produto.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o produto.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Produto getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Produto resultado = null;
		try {
			resultado = em.find(Produto.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o usuário com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	@Override
	public List<Produto> getAll() throws PersistenciaDacException {
		return findBy(new ProdutoFilter());
	}

	@Override
	public List<Produto> findBy(ProdutoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Produto> resultado = new ArrayList<>();
		try {

			String jpql = "SELECT p FROM Produto p WHERE 1 = 1 ";

			// Nome
			if (notEmpty(filter.getNome())) {
				jpql += "AND p.nome LIKE :nome ";
			}

			// Descricao
			if (notEmpty(filter.getDescricao())) {
				jpql += "AND p.descricao LIKE :descricao ";
			}

			// Início da Data de Validade
			if (notEmpty(filter.getInicioDataDeValidade())) {
				jpql += "AND p.inicioDataDeValidade >= :inicioDataDeValidade ";
			}
			// Fim da Data de Validade
			if (notEmpty(filter.getFimDataDeValidade())) {
				jpql += "AND p.fimDataDeValidade >= :fimDataDeValidade ";
			}
			// Início da Data de Entrada
			if (notEmpty(filter.getInicioDataEntrada())) {
				jpql += "AND p.inicioDataEntrada >= :inicioDataEntrada ";
			}
			// Fim da Data de Entrada
			if (notEmpty(filter.getFimDataEntrada())) {
				jpql += "AND p.fimDataEntrada >= :fimDataEntrada ";
			}
			// Início da Data de Saída
			if (notEmpty(filter.getInicioDataSaida())) {
				jpql += "AND p.inicioDataSaida >= :inicioDataSaida ";
			}
			// Fim da Data de Saída
			if (notEmpty(filter.getFimDataSaida())) {
				jpql += "AND p.fimDataSaida >= :fimDataSaida ";
			}

			// Tipo De Produto
			if (notEmpty(filter.getTipoDeProduto())) {
				jpql += "AND p.tipoDeProduto = :tipoDeProduto ";
			}
			TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

			// Nome
			if (notEmpty(filter.getNome())) {
				query.setParameter("nome", "%" + filter.getNome() + "%");
			}

			// Descricao
			if (notEmpty(filter.getDescricao())) {
				query.setParameter("descricao", "%" + filter.getDescricao() + "%");
			}

			// Inicio Data de Validade
			if (notEmpty(filter.getInicioDataDeValidade())) {
				query.setParameter("inicioDataDeValidade", filter.getInicioDataDeValidade());
			}

			// Fim Data de Validade
			if (notEmpty(filter.getFimDataDeValidade())) {
				query.setParameter("fimDataDeValidade", filter.getFimDataDeValidade());
			}
			// Inicio Data de Entrada
			if (notEmpty(filter.getInicioDataEntrada())) {
				query.setParameter("inicioDataEntrada", filter.getInicioDataEntrada());
			}

			// Fim Data de Entrada
			if (notEmpty(filter.getFimDataEntrada())) {
				query.setParameter("fimDataEntrada", filter.getFimDataEntrada());
			}
			// Inicio Data de Saída
			if (notEmpty(filter.getInicioDataSaida())) {
				query.setParameter("inicioDataSaida", filter.getInicioDataSaida());
			}

			// Fim Data de Saída
			if (notEmpty(filter.getFimDataSaida())) {
				query.setParameter("fimDataSaida", filter.getFimDataSaida());
			}

			// Tipo de Produto
			if (notEmpty(filter.getTipoDeProduto())) {
				query.setParameter("group", filter.getTipoDeProduto());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os produtos.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}