package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Set;

import junit.framework.Assert;
import modele.Group;
import modele.User;

import org.junit.Test;

public class GroupDaoTest {

	private final String description = "Description";
	private final String hashtag = "#Event";
	private final String password = "password";

	@Test
	public void addGroupTest() {
		// Creation des users
		User user = new User("0678356576", "toto", "toto@gogol.com",
				"deviceToken");
		User invite1 = new User("tel23", "toto2", "mail", "deviceToken");
		User invite2 = new User("tel24", "toto3", "mail", "deviceToken");

		UserDao.addUser(user);
		UserDao.addUser(invite1);
		UserDao.addUser(invite2);

		// Creation du groupe
		User proprietaire = UserDao.getUser("0678356576");

		Group g1 = new Group(description, hashtag, proprietaire, password);
		g1.addParticipation(invite1);
		g1.addParticipation(invite2);
		GroupDao.addGroup(g1);

		// Verification

		Group g2 = GroupDao.getGroup(hashtag);
		User userTest = UserDao.getUser("tel23");

		assertEquals(hashtag, g2.getHashtag());
		assertEquals(password, g2.getPassword());
		Assert.assertTrue(g2.getInvites().contains(userTest));

		// Supression d'un user du groupe
		User userToRemove = UserDao.getUser("tel23");
		Group group1 = GroupDao.getGroup(hashtag);

		group1.removeParticipation(userToRemove);

		GroupDao.modifyGroup(group1);

		// Verification

		Group group2 = GroupDao.getGroup(hashtag);
		User userToKeep = UserDao.getUser("tel24");

		Set<User> invites = group2.getInvites();
		Assert.assertFalse(invites.contains(userToRemove));
		Assert.assertTrue(invites.contains(userToKeep));

		// Supression d'un groupe
		GroupDao.deleteGroup(hashtag);
		Group g3 = GroupDao.getGroup(hashtag);
		assertNull(g3);

		User participant = UserDao.getUser("tel24");
		Assert.assertFalse(participant.getGroups().contains(g3));

		// Supression des users
		UserDao.deleteUser("0678356576");

		UserDao.deleteUser("tel23");
		UserDao.deleteUser("tel24");

	}

}
