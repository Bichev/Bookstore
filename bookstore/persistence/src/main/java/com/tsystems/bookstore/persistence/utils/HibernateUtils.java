package com.tsystems.bookstore.persistence.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {

	private static final SessionFactory sessionFactory;
	//Hibernate 4
	
	private static ServiceRegistry serviceRegistry;
	
	static {
		try {
		    Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (HibernateException ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

/*	
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new AnnotationConfiguration().configure().buildSessionFactory();
 
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
*/    
	 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static Session beginTransaction() {
		Session hibernateSession = HibernateUtils.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	 
	public static void commitTransaction() {
		HibernateUtils.getSession().getTransaction().commit();
	}
	 
	public static void rollbackTransaction() {
		HibernateUtils.getSession().getTransaction().rollback();
	}
	 
	public static void closeSession() {
		HibernateUtils.getSession().close();
	}
	 
	public static Session getSession() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}
	
	public static Session openSession() {
		Session hibernateSession = sessionFactory.openSession();
		return hibernateSession;
	}
}
