package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import modele.Group;
import modele.Marqueur;
import modele.NotificationMarqueur;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class MarqueurDao {
	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	public static Marqueur getMarqueur(Integer idMarqueur) {
		Session session = sessionFactory.openSession();
		String query = "select m from Marqueur m where m.id = :id";
		Marqueur marqueur = (Marqueur) session.createQuery(query)
				.setInteger("id", idMarqueur).uniqueResult();
		session.close();
		return marqueur;
	}

	public static Integer addMarqueur(Marqueur marqueur) {
		Integer marqueurID = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			marqueurID = (Integer) session.save(marqueur);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return marqueurID;
	}
	
	/**
	 * Delete marqueur in database
	 * @param marqueur
	 */

	public static void deleteMarqueur(Marqueur marqueur) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(marqueur);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * Method to READ all the users
	 * 
	 * @return users
	 */
	
	public static ArrayList<Marqueur> getMarqueurs(Group group) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<Marqueur> marqueurs = null;
		try {
			tx = session.beginTransaction();
			marqueurs = (ArrayList<Marqueur>) session
					.createQuery(
							"select m from Marqueur m where m.group.id = :id")
					.setInteger("id", group.getId()).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return marqueurs;
	}
	
	/**
	 * 
	 * @param createur
	 * @return All marqueurs for a user
	 */
	
	public static ArrayList<Marqueur> getMarqueursByCreateur(User createur)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<Marqueur> marqueurs = null;
		try {
			tx = session.beginTransaction();
			marqueurs = (ArrayList<Marqueur>) session
					.createQuery(
							"select m from Marqueur m where m.user.id = :id")
					.setInteger("id", createur.getId()).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return marqueurs;		
	}

	public static void deleteOldMarqueurs() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ArrayList<Marqueur> ms = (ArrayList<Marqueur>) session.createQuery("select m from Marqueur m where m.fin < :date").setParameter("date", new Date()).list();
			session.createQuery("delete from NotificationMarqueur nm where nm.marqueur in :marqueurs").setParameterList("marqueurs", ms).executeUpdate();
			session.createQuery("delete from Marqueur m where m in :marqueurs").setParameterList("marqueurs", ms).executeUpdate();
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
