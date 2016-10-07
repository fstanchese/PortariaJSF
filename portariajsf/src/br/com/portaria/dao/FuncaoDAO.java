package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Funcao;
import br.com.portaria.util.HibernateUtil;

public class FuncaoDAO {

	public void salvar(Funcao funcao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcao);
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
	public List<Funcao> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Funcao> funcoes = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcao.listar");
			funcoes = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return funcoes;
	}

	public Funcao buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcao funcao = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcao.buscaPorId");
			consulta.setLong("id", id);
			funcao = (Funcao) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return funcao;
	}

	public void excluir(Funcao funcao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcao);
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

	public void editar(Funcao funcao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcao);
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
