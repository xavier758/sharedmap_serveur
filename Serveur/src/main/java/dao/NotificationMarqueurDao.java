package dao;

import java.util.ArrayList;

import modele.Group;
import modele.NotificationMarqueur;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class NotificationMarqueurDao {
	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	public static NotificationMarqueur getNotificationMarqueur(Integer id) {
		Session session = sessionFactory.openSession();
		String query = "select n from NotificationMarqueur n where n.id = :id";
		NotificationMarqueur notificationMarqueur = (NotificationMarqueur) session
				.createQuery(query).setInteger("id", id).uniqueResult();
		session.close();
		return notificationMarqueur;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<NotificationMarqueur> getNotificationsMarqueur() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<NotificationMarqueur> notificationsMarqueur = null;
		try {
			tx = session.beginTransaction();
			notificationsMarqueur = (ArrayList<NotificationMarqueur>) session
					.createQuery("FROM NotificationMarqueur").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notificationsMarqueur;
	}

	public static Integer addNotificationMarqueur(
			NotificationMarqueur notificationMarqueur) {
		Integer notificationMarqueurID = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			notificationMarqueurID = (Integer) session.save(notificationMarqueur);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notificationMarqueurID;
	}

	public static void deleteNotificationMarqueur(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		NotificationMarqueur nM = getNotificationMarqueur(id);
		try {
			tx = session.beginTransaction();
			session.delete(nM);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static void deleteNotificationMarqueurs(Group group, User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createQuery("delete from NotificationMarqueur n where n.marqueur in (select m from Marqueur m where m.group = :group) and n.user = :user").setParameter("group", group).setParameter("user", user).executeUpdate();
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
