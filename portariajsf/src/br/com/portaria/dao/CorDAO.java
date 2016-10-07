package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Cor;
import br.com.portaria.util.HibernateUtil;

public class CorDAO {

	public void salvar(Cor cor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(cor);
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
	public List<Cor> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Cor> cores = null;
		try {
			Query consulta = sessao.getNamedQuery("Cor.listar");
			cores = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return cores;
	}

	public Cor buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Cor cor = null;
		try {
			Query consulta = sessao.getNamedQuery("Cor.buscaPorId");
			consulta.setLong("id", id);
			cor = (Cor) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return cor;
	}

	public void excluir(Cor cor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(cor);
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

	public void editar(Cor cor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(cor);
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
