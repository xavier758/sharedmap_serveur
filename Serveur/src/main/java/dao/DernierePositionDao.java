package dao;

import modele.DernierePosition;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DernierePositionDao {

	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	public static DernierePosition getDernierePosition(User user) {

		Session session = sessionFactory.openSession();

		String query = "select d from DernierePosition d where d.user.id = :id";

		DernierePosition dernierePosition = (DernierePosition) session
				.createQuery(query).setInteger("id", user.getId())
				.uniqueResult();
		session.close();
		return dernierePosition;
	}

	public static Integer addDernierePosition(DernierePosition dernierePosition) {
		Integer dernierePositionID = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			dernierePositionID = (Integer) session.save(dernierePosition);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dernierePositionID;
	}

	public static void deleteDernierePosition(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		String query = "select d from DernierePosition d where d.id = :id";
		DernierePosition dernierePosition = (DernierePosition) session
				.createQuery(query).setInteger("id", id).uniqueResult();
		try {
			tx = session.beginTransaction();
			session.delete(dernierePosition);
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
 * @param user
 * @return
 */
	public static DernierePosition modifyDernierePosition(DernierePosition dernierePosition) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(dernierePosition);
			tx.commit();			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dernierePosition;
	}
}
