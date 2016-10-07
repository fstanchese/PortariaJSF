package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.VisitanteTipo;
import br.com.portaria.util.HibernateUtil;

public class VisitanteTipoDAO {
	
	public void salvar(VisitanteTipo visitantetipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(visitantetipo);
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
	public List<VisitanteTipo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<VisitanteTipo> visitantetipos = null;
		try {
			Query consulta = sessao.getNamedQuery("VisitanteTipo.listar");
			visitantetipos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return visitantetipos;
	}

	public VisitanteTipo buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		VisitanteTipo visitantetipo = null;
		try {
			Query consulta = sessao.getNamedQuery("VisitanteTipo.buscaPorId");
			consulta.setLong("id", id);
			visitantetipo = (VisitanteTipo) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return visitantetipo;
	}

	public void excluir(VisitanteTipo visitantetipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(visitantetipo);
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

	public void editar(VisitanteTipo visitantetipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(visitantetipo);
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
