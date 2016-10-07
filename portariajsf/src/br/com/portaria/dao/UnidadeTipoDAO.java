package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.UnidadeTipo;
import br.com.portaria.util.HibernateUtil;

public class UnidadeTipoDAO {
	
	public void salvar(UnidadeTipo unidadetipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(unidadetipo);
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
	public List<UnidadeTipo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<UnidadeTipo> unidadetipos = null;
		try {
			Query consulta = sessao.getNamedQuery("UnidadeTipo.listar");
			unidadetipos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return unidadetipos;
	}

	public UnidadeTipo buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		UnidadeTipo unidadetipo = null;
		try {
			Query consulta = sessao.getNamedQuery("UnidadeTipo.buscaPorId");
			consulta.setLong("id", id);
			unidadetipo = (UnidadeTipo) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return unidadetipo;
	}

	public void excluir(UnidadeTipo unidadetipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(unidadetipo);
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

	public void editar(UnidadeTipo unidadetipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(unidadetipo);
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
