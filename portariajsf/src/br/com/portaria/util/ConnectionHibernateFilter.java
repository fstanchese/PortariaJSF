package br.com.portaria.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

public class ConnectionHibernateFilter implements Filter {

	private SessionFactory sessionFactory;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		try {
			this.sessionFactory.getCurrentSession().beginTransaction();
			System.out.println("Abri a Sessão");
			chain.doFilter(servletRequest, servletResponse);
			this.sessionFactory.getCurrentSession().getTransaction().commit();
			this.sessionFactory.getCurrentSession().close();
			System.out.println("Fechei a Sessão");
		} catch (Throwable e) {
			try {
				if (this.sessionFactory.getCurrentSession().getTransaction().isActive()) {
					this.sessionFactory.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {
	}
}
