package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Marca;
import br.com.portaria.model.Modelo;
import br.com.portaria.util.HibernateUtil;

public class MarcaDAO {

	public void salvar(Marca marca) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(marca);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Marca> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Marca> marcas = null;
		try {
			Query consulta = sessao.getNamedQuery("Marca.listar");
			marcas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return marcas;
	}

	@SuppressWarnings("unchecked")
	public List<Modelo> listarModelos(Long id) {
		List<Modelo> modelos = null;
		if (id != null) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			try {
				Query consulta = sessao.getNamedQuery("Marca.listarModelos");
				consulta.setLong("id", id);
				modelos = consulta.list();
			} catch (RuntimeException e) {
				throw e;
			} finally {
				sessao.close();
			}			
		}
		return modelos;
	}
	
	public Marca buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Marca marca = null;
		try {
			Query consulta = sessao.getNamedQuery("Marca.buscaPorId");
			consulta.setLong("id", id);
			marca = (Marca) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return marca;
	}

	public void excluir(Marca marca) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(marca);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

	public void editar(Marca marca) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(marca);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
}
