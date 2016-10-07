package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Andar;
import br.com.portaria.util.HibernateUtil;

public class AndarDAO {
	
	public void salvar(Andar andar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(andar);
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
	public List<Andar> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Andar> andares = null;
		try {
			Query consulta = sessao.getNamedQuery("Andar.listar");
			andares = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return andares;
	}

	public Andar buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Andar andar = null;
		try {
			Query consulta = sessao.getNamedQuery("Andar.buscaPorId");
			consulta.setLong("id", id);
			andar = (Andar) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return andar;
	}

	public void excluir(Andar andar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(andar);
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

	public void editar(Andar andar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(andar);
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
