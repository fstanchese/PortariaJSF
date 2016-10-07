package br.com.portaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Fechei a SessionFactory");
		HibernateUtil.getSessionFactory().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Abri a SessionFactory");
		HibernateUtil.getSessionFactory().openSession();
	}

}
