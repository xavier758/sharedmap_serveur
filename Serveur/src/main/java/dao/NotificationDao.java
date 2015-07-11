package dao;

import java.util.ArrayList;

import modele.Notification;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class NotificationDao {
	
	private static SessionFactory sessionFactory = SessionConfiguration.getFactory();
	
	public static Notification addNotification(Notification notification)
	{
		Integer id = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(notification);			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		notification.setId(id);
		return notification;			
	}
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Notification> getNotifications(User user)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<Notification> notifications = null;
		try {
			tx = session.beginTransaction();
			String query = "select n from Notification n where n.user = :user";			
			notifications = (ArrayList<Notification>) session.createQuery(query).setParameter("user", user).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notifications;		
	}
	

}
