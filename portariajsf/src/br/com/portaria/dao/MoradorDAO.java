package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Morador;
import br.com.portaria.util.HibernateUtil;

public class MoradorDAO {

	public void salvar(Morador morador) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(morador);
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
	public List<Morador> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Morador> moradores = null;
		try {
			Query consulta = sessao.getNamedQuery("Morador.listar");
			moradores = consulta.list();
			for (Morador morador : moradores) {
				Hibernate.initialize(morador.getUnidade());
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return moradores;
	}

	public Morador buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Morador morador = null;
		try {
			Query consulta = sessao.getNamedQuery("Morador.buscaPorId");
			consulta.setLong("id", id);
			morador = (Morador) consulta.uniqueResult();
			Hibernate.initialize(morador.getUnidade());
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return morador;
	}

	public void excluir(Morador morador) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(morador);
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

	public void editar(Morador morador) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(morador);
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
