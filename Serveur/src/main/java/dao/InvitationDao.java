package dao;

import java.util.ArrayList;

import modele.Invitation;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InvitationDao {

	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	@SuppressWarnings("unchecked")
	public static ArrayList<Invitation> getInvitations(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<Invitation> invitations = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("FROM Invitation where user_id = :user_id");
			query.setParameter("user_id", user.getId());
			invitations = (ArrayList<Invitation>) query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return invitations;
	}

	public static Invitation getInvitation(Integer id) {
		Session session = sessionFactory.openSession();
		String query = "select i from Invitation i where i.id = :id";
		Invitation invitation = (Invitation) session.createQuery(query)
				.setInteger("id", id).uniqueResult();
		session.close();
		return invitation;
	}

	public static Integer addInvitation(Invitation invitation) {
		Integer invitationID = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			invitationID = (Integer) session.save(invitation);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return invitationID;
	}

	public static void deleteInvitation(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Invitation invitation = getInvitation(id);
		try {
			tx = session.beginTransaction();
			session.delete(invitation);
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
