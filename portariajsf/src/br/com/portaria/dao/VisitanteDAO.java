package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Visitante;
import br.com.portaria.util.HibernateUtil;

public class VisitanteDAO {

	public void salvar(Visitante visitante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(visitante);
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
	public List<Visitante> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Visitante> visitantes = null;
		try {
			Query consulta = sessao.getNamedQuery("Visitante.listar");
			visitantes = consulta.list();
			for (Visitante visitante : visitantes) {
				Hibernate.initialize(visitante.getCor());
				Hibernate.initialize(visitante.getModelo().getMarca());
				Hibernate.initialize(visitante.getUnidade());
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return visitantes;
	}
	
	public Visitante buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Visitante visitante = null;
		try {
			Query consulta = sessao.getNamedQuery("Visitante.buscaPorId");
			consulta.setLong("id", id);
			visitante = (Visitante) consulta.uniqueResult();
			Hibernate.initialize(visitante.getCor());
			Hibernate.initialize(visitante.getModelo().getMarca());
			Hibernate.initialize(visitante.getUnidade());

		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return visitante;
	}

	public void excluir(Visitante visitante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(visitante);
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

	public void editar(Visitante visitante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(visitante);
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
