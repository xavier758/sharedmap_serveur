package dao;

import java.util.ArrayList;

import modele.Group;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GroupDao {

	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	public static Group getGroup(String hashtag) {

		Session session = sessionFactory.openSession();
		String query = "select e from Group e where e.hashtag = :hashtag";

		Group group = (Group) session.createQuery(query)
				.setString("hashtag", hashtag).uniqueResult();
		session.close();
		return group;
	}

	public static int addGroup(Group group) {
		int g = 0;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			g = (Integer) session.save(group);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return g;
	}

	public static void modifyGroup(Group group) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(group);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void deleteGroup(String hashtag) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Group group = getGroup(hashtag);
		try {
			tx = session.beginTransaction();
			group.getInvites().clear();
			session.update(group);
			session.delete(group);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static ArrayList<Group> getGroups(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String query = "select g from Group g where :user in elements(g.invites)";
		@SuppressWarnings("unchecked")
		ArrayList<Group> group = (ArrayList<Group>) session.createQuery(query).setParameter("user", user).list();		
		session.close();
		return group;
	}
}
