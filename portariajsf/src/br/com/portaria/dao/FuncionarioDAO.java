package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Funcionario;
import br.com.portaria.util.HibernateUtil;

public class FuncionarioDAO {
	public void salvar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
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
	public List<Funcionario> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> funcionarios = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();
			for (Funcionario funcionario : funcionarios) {
				Hibernate.initialize(funcionario.getFuncao());
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return funcionarios;
	}

	public Funcionario buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.buscaPorId");
			consulta.setLong("id", id);
			funcionario = (Funcionario) consulta.uniqueResult();
			Hibernate.initialize(funcionario.getFuncao());
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return funcionario;
	}

	public void excluir(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
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

	public void editar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
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

	public Funcionario autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		try {
			Query consulta = sessao.getNamedQuery("Funcionario.login");
			consulta.setString("login", login);
			consulta.setString("senha", senha);
			funcionario = (Funcionario) consulta.uniqueResult();
			Hibernate.initialize(funcionario.getFuncao());
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return funcionario;
	}
}
