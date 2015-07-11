package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionConfiguration {
	private static SessionFactory sessionFactory;
	
	static{
		try {
			Configuration cfg = new Configuration()
					.configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
			sb.applySettings(cfg.getProperties());
			StandardServiceRegistry standardServiceRegistry = sb.build();
			sessionFactory = (SessionFactory) cfg
					.buildSessionFactory(standardServiceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}	
	}
	
	public static SessionFactory getFactory(){
		return sessionFactory;
	}
	
	
}
