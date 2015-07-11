package dao;

import java.util.ArrayList;

import junit.framework.Assert;
import modele.Demande;
import modele.Group;
import modele.User;

import org.junit.Test;

public class DemandeDaoTest {

	@Test
	public void demandeTest() {
		User user = new User("tel", "pseudo", "email", "deviceToken");
		User demandeur = new User("tel1", "pseudo1", "email1", "deviceToken");

		UserDao.addUser(user);
		user = UserDao.getUser(user.getTelephone());

		UserDao.addUser(demandeur);
		demandeur = UserDao.getUser(demandeur.getTelephone());

		Group group = new Group("description", "hashtag", user, "password");

		GroupDao.addGroup(group);
		group = GroupDao.getGroup(group.getHashtag());

		// add test
		Demande demande = new Demande(group, demandeur);

		int idDemande = DemandeDao.addDemande(demande);

		Demande demandeAdded = DemandeDao.getDemande(idDemande);

		Assert.assertEquals(demande.getId(), demandeAdded.getId());
		Assert.assertEquals(demande.getUser().getId(), demandeAdded.getUser()
				.getId());
		Assert.assertEquals(demande.getGroup().getId(), demandeAdded.getGroup()
				.getId());
		Assert.assertEquals(demande.getDemandeur().getId(), demandeAdded
				.getDemandeur().getId());

		// getDemandes test
		ArrayList<Demande> demandes = DemandeDao.getDemandes(demandeur);

		Assert.assertTrue(demandes.contains(demandeAdded));

		// delete test
		DemandeDao.deleteDemande(idDemande);

		GroupDao.deleteGroup(group.getHashtag());

		UserDao.deleteUser(user.getTelephone());

		UserDao.deleteUser(demandeur.getTelephone());

		Demande demandeDeleted = DemandeDao.getDemande(idDemande);

		Assert.assertNull(demandeDeleted);
	}

}
