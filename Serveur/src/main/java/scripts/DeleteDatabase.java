package scripts;

import java.util.ArrayList;

import modele.Notification;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.SessionConfiguration;

/**
 * Used to deleted the database completely
 * @author Xavier
 *
 */
public class DeleteDatabase {
	
	public static void main(String[] args){
		deleteAll();
		return ;
	}
	
	
	private static SessionFactory sessionFactory = SessionConfiguration.getFactory();
	
	public static void deleteAll(){
		deleteNotifications();
		deletePositions();
		deleteGroups();
		deleteUsers();
	}
	

	public static void deleteNotifications(){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		@SuppressWarnings("unused")
		ArrayList<Notification> notifications = null;
		try {
			tx = session.beginTransaction();
			String query = "delete Notification n ";			
			session.createQuery(query).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static void deletePositions(){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		@SuppressWarnings("unused")
		ArrayList<Notification> notifications = null;
		try {
			tx = session.beginTransaction();
			String query = "delete Position p ";			
			session.createQuery(query).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static void deleteGroups(){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		@SuppressWarnings("unused")
		ArrayList<Notification> notifications = null;
		try {
			tx = session.beginTransaction();
			String query = "delete Group g ";			
			session.createQuery(query).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static void deleteUsers(){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		@SuppressWarnings("unused")
		ArrayList<Notification> notifications = null;
		try {
			tx = session.beginTransaction();
			String query = "delete User u ";			
			session.createQuery(query).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}	
}
