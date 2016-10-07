package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Unidade;
import br.com.portaria.util.HibernateUtil;


public class UnidadeDAO {

	public void salvar(Unidade unidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(unidade);
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
	public List<Unidade> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Unidade> unidades = null;
		try {
			Query consulta = sessao.getNamedQuery("Unidade.listar");
			unidades = consulta.list();
			for (Unidade unidade : unidades) {
				Hibernate.initialize(unidade.getAndar());
				Hibernate.initialize(unidade.getBloco());
				Hibernate.initialize(unidade.getMoradorTipo());
				Hibernate.initialize(unidade.getUnidadeTipo());
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return unidades;
	}

	public Unidade buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Unidade unidade = null;
		try {
			Query consulta = sessao.getNamedQuery("Unidade.buscaPorId");
			consulta.setLong("id", id);
			unidade = (Unidade) consulta.uniqueResult();
			Hibernate.initialize(unidade.getAndar());
			Hibernate.initialize(unidade.getBloco());
			Hibernate.initialize(unidade.getMoradorTipo());
			Hibernate.initialize(unidade.getUnidadeTipo());			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return unidade;
	}

	public void excluir(Unidade unidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(unidade);
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

	public void editar(Unidade unidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(unidade);
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
