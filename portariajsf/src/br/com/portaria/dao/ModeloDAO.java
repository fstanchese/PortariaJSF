package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Modelo;
import br.com.portaria.util.HibernateUtil;

public class ModeloDAO {
	public void salvar(Modelo modelo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(modelo);
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
	public List<Modelo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Modelo> modelos = null;

		try {
			Query consulta = sessao.getNamedQuery("Modelo.listar");
			modelos = consulta.list();
			for (Modelo modelo : modelos) {
				Hibernate.initialize(modelo.getMarca());
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return modelos;
	}
	
	public Modelo buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Modelo modelo = null;
		try {
			Query consulta = sessao.getNamedQuery("Modelo.buscaPorId");
			consulta.setLong("id", id);
			modelo = (Modelo) consulta.uniqueResult();
			
			Hibernate.initialize(modelo.getMarca());
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return modelo;
	}

	public void excluir(Modelo modelo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(modelo);
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

	public void editar(Modelo modelo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(modelo);
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
