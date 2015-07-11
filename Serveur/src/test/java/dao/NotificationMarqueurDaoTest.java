package dao;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;
import modele.Group;
import modele.Marqueur;
import modele.NotificationMarqueur;
import modele.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotificationMarqueurDaoTest {
	private Marqueur marqueur;
	private User user;
	private Group group;

	private Integer idNotificationMarqueur;

	@Before
	public void initialize() {
		// create dependencies
		user = new User("tel", "pseudo", "email", "deviceToken");
		group = new Group("description", "hashtag", user, "password");
		marqueur = new Marqueur(group, "description", 1.0, 2.0, new Date(),
				user);

		UserDao.addUser(user);
		user = UserDao.getUser(user.getTelephone());

		GroupDao.addGroup(group);
		group = GroupDao.getGroup(group.getHashtag());

		MarqueurDao.addMarqueur(marqueur);
		marqueur = MarqueurDao.getMarqueur(marqueur.getId());
	}

	@Test
	public void notificationMarqueurTest() {

		// Test addNotificationMarqueur
		NotificationMarqueur notificationMarqueur = new NotificationMarqueur(
				user, marqueur);

		idNotificationMarqueur = NotificationMarqueurDao
				.addNotificationMarqueur(notificationMarqueur);

		NotificationMarqueur notificationMarqueurAdded = NotificationMarqueurDao
				.getNotificationMarqueur(idNotificationMarqueur);

		Assert.assertEquals(notificationMarqueur.getId(),
				notificationMarqueurAdded.getId());
		Assert.assertEquals(notificationMarqueur.getUser().getId(),
				notificationMarqueurAdded.getUser().getId());
		Assert.assertEquals(notificationMarqueur.getMarqueur().getId(),
				notificationMarqueurAdded.getMarqueur().getId());

		// Test getNotificationsMarqueur
		NotificationMarqueur notificationMarqueurToGet = NotificationMarqueurDao
				.getNotificationMarqueur(idNotificationMarqueur);

		ArrayList<NotificationMarqueur> notificationsMarqueur = NotificationMarqueurDao
				.getNotificationsMarqueur();

		Assert.assertTrue(notificationsMarqueur
				.contains(notificationMarqueurToGet));

		// Test deleteNotificationMarqueur
		NotificationMarqueur notificationMarqueurToDelete = NotificationMarqueurDao
				.getNotificationMarqueur(idNotificationMarqueur);

		NotificationMarqueurDao
				.deleteNotificationMarqueur(idNotificationMarqueur);

		ArrayList<NotificationMarqueur> notificationsMarqueurFinal = NotificationMarqueurDao
				.getNotificationsMarqueur();

		Assert.assertFalse(notificationsMarqueurFinal
				.contains(notificationMarqueurToDelete));
	}

	@After
	public void delete() {
		MarqueurDao.deleteMarqueur(marqueur);
		GroupDao.deleteGroup(group.getHashtag());
		UserDao.deleteUser(user.getTelephone());
	}
}
