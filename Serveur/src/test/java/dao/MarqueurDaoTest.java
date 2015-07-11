package dao;

import java.util.ArrayList;
import java.util.Date;

import modele.Group;
import modele.Marqueur;
import modele.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MarqueurDaoTest {
	private User proprietaire;
	private Group group;

	private Integer idMarqueur;

	@Before
	public void initialize() {
		proprietaire = new User("telephone", "pseudo", "email", "deviceToken");
		group = new Group("description", "hashtag", proprietaire, "password");

		UserDao.addUser(proprietaire);
		proprietaire = UserDao.getUser(proprietaire.getTelephone());

		GroupDao.addGroup(group);
		group = GroupDao.getGroup(group.getHashtag());
	}

	@Test
	public void marqueurTest() {
		// Test Add
		Marqueur marqueur = new Marqueur(group, "description", 1.0, 2.0,
				new Date(), proprietaire);

		idMarqueur = MarqueurDao.addMarqueur(marqueur);

		Marqueur marqueurAdded = MarqueurDao.getMarqueur(idMarqueur);

		Assert.assertEquals(marqueur.getId(), marqueurAdded.getId());
		Assert.assertEquals(marqueur.getGroup().getId(), marqueurAdded
				.getGroup().getId());

		// Test Get Marqueurs

		ArrayList<Marqueur> marqueurs = MarqueurDao.getMarqueurs(group);

		Assert.assertTrue(marqueurs.contains(marqueurAdded));

		// Test Delete marqueur

		MarqueurDao.deleteMarqueur(marqueur);

		Marqueur marqueurToGet = MarqueurDao.getMarqueur(idMarqueur);

		Assert.assertNull(marqueurToGet);
	}

	@After
	public void delete() {
		GroupDao.deleteGroup(group.getHashtag());
		UserDao.deleteUser(proprietaire.getTelephone());
	}
}
