package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.MoradorTipo;
import br.com.portaria.util.HibernateUtil;

public class MoradorTipoDAO {
	
	public void salvar(MoradorTipo moradortipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(moradortipo);
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
	public List<MoradorTipo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<MoradorTipo> moradortipos = null;
		try {
			Query consulta = sessao.getNamedQuery("MoradorTipo.listar");
			moradortipos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return moradortipos;
	}

	public MoradorTipo buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		MoradorTipo moradortipo = null;
		try {
			Query consulta = sessao.getNamedQuery("MoradorTipo.buscaPorId");
			consulta.setLong("id", id);
			moradortipo = (MoradorTipo) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return moradortipo;
	}

	public void excluir(MoradorTipo moradortipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(moradortipo);
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

	public void editar(MoradorTipo moradortipo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(moradortipo);
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
