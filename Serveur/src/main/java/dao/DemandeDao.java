package dao;

import java.util.ArrayList;

import modele.Demande;
import modele.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DemandeDao {

	private static SessionFactory sessionFactory = SessionConfiguration
			.getFactory();

	public static Demande getDemande(Integer idDemande) {

		Session session = sessionFactory.openSession();

		String query = "select d from Demande d where d.id = :id";

		Demande demande = (Demande) session.createQuery(query)
				.setInteger("id", idDemande).uniqueResult();
		session.close();
		return demande;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Demande> getDemandes(User user) {
		Session session = sessionFactory.openSession();

		String query = "select d from Demande d where d.demandeur.id = :idUser";

		ArrayList<Demande> demandes = (ArrayList<Demande>) session
				.createQuery(query).setInteger("idUser", user.getId()).list();

		session.close();

		return demandes;
	}

	public static Integer addDemande(Demande demande) {
		Integer demandeID = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			demandeID = (Integer) session.save(demande);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return demandeID;
	}

	public static void deleteDemande(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Demande demande = getDemande(id);
		try {
			tx = session.beginTransaction();
			session.delete(demande);
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
