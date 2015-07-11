package dao;

import java.util.ArrayList;
import java.util.List;

import modele.Group;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDao {

	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	/**
	 * 
	 * @param telephone
	 * @return
	 */
	public static User getUser(String telephone) {
		Session session = sessionFactory.openSession();	
		String query = "select u from User u where u.telephone = :telephone";
		User user = (User) session.createQuery(query)
				.setString("telephone", telephone).uniqueResult();
		session.close();
		return user;
	}
	
	/**
	 * 
	 * @param telephone
	 * @return
	 */
	public static ArrayList<User> getUsers(Group group) {
		Session session = sessionFactory.openSession();
		String query = "select u from User u where :group in elements(u.groups)";
		@SuppressWarnings("unchecked")
		ArrayList<User> users = (ArrayList<User>) session.createQuery(query).setParameter("group", group).list();
		session.close();
		return users;
	}
	/**
	 * Method to ADD a user in the database
	 * 
	 * @param user
	 * @return user_id
	 */
	public static Integer addUser(User user) {
		Integer userID = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			userID = (Integer) session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userID;
	}

	/**
	 * 
	 * Method to UPDATE pseudo and email for a user
	 * 
	 * @param telephone
	 * @param pseudo
	 * @param email
	 * @return
	 */
	public static User modifyUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	/**
	 * 
	 * Method to DELETE a user from the records
	 * 
	 * @param telephone
	 */
	public static void deleteUser(String telephone) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;		
		User user = getUser(telephone);
		
		try {
			tx = session.beginTransaction();
			session.delete(user);
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
	@SuppressWarnings("unchecked")
	public static ArrayList<User> listUser() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<User> users = null;
		try {
			tx = session.beginTransaction();
			users = (ArrayList<User>) session.createQuery("FROM User").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	/**
	 * 
	 * @param numeros
	 *            : Liste des numeros du repertoire de l'utilisateur
	 * @return Set des User présents en base de donnée parmi cette liste
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<User> getRepertoire(List<String> numeros) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<User> users = null;
		try {
			tx = session.beginTransaction();
			users = (ArrayList<User>) session
					.createQuery("Select u FROM User u WHERE u.telephone IN :numeros").setParameterList("numeros", numeros).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}
}
