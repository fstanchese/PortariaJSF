package br.com.portaria.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.portaria.model.Veiculo;
import br.com.portaria.util.HibernateUtil;

public class VeiculoDAO {

	public void salvar(Veiculo veiculo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(veiculo);
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
	public List<Veiculo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Veiculo> veiculos = null;
		try {
			Query consulta = sessao.getNamedQuery("Veiculo.listar");
			veiculos = consulta.list();
			for (Veiculo veiculo : veiculos) {
				Hibernate.initialize(veiculo.getCor());
				Hibernate.initialize(veiculo.getModelo().getMarca());
				Hibernate.initialize(veiculo.getMorador().getUnidade());
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return veiculos;
	}
	
	public Veiculo buscaPorId(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Veiculo veiculo = null;
		try {
			Query consulta = sessao.getNamedQuery("Veiculo.buscaPorId");
			consulta.setLong("id", id);
			veiculo = (Veiculo) consulta.uniqueResult();
			Hibernate.initialize(veiculo.getCor());
			Hibernate.initialize(veiculo.getModelo().getMarca());
			Hibernate.initialize(veiculo.getMorador().getUnidade());

		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return veiculo;
	}

	public void excluir(Veiculo veiculo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(veiculo);
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

	public void editar(Veiculo veiculo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(veiculo);
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
