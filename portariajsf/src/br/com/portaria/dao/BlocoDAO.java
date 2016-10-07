package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Bloco;
import br.com.portaria.util.HibernateUtil;

public class BlocoDAO {
	public void salvar(Bloco bloco) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(bloco);
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
	public List<Bloco> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Bloco> blocos = null;
		try {
			Query consulta = sessao.getNamedQuery("Bloco.listar");
			blocos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return blocos;
	}

	public Bloco buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Bloco bloco = null;
		try {
			Query consulta = sessao.getNamedQuery("Bloco.buscaPorId");
			consulta.setLong("id", id);
			bloco = (Bloco) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return bloco;
	}

	public void excluir(Bloco bloco) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(bloco);
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

	public void editar(Bloco bloco) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(bloco);
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
