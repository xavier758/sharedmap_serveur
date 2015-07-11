package dao;

import modele.Position;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PositionDao {
	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	public static Position getPosition(Integer id) {
		Session session = sessionFactory.openSession();
		String query = "select p from Position p where p.id = :id";
		Position position = (Position) session.createQuery(query)
				.setInteger("id", id).uniqueResult();
		session.close();
		return position;
	}

	public static Integer addPosition(Position position) {
		Integer positionID = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			positionID = (Integer) session.save(position);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return positionID;
	}

	public static void deletePosition(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		String query = "select p from Position p where p.id = :id";
		Position position = (Position) session.createQuery(query)
				.setInteger("id", id).uniqueResult();
		try {
			tx = session.beginTransaction();
			session.delete(position);
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
