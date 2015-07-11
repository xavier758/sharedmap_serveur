package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import modele.Demande;
import modele.DernierePosition;
import modele.Group;
import modele.Invitation;
import modele.Notification;
import modele.User;

import org.junit.Test;

public class UserDaoTest {

	private final String tel = "0601020304";
	private final String pseudo = "pseudo";
	private final String pseudoModified = "pseudo2";
	private final String email = "email@email.com";

	@Test
	public void addUserTest() {

		User initialUser = new User(tel, pseudo, email, "deviceToken");

		// Add user in database
		UserDao.addUser(initialUser);

		User addedUser = UserDao.getUser(tel);

		// Add Proprietaire in Group
		Group group = new Group();
		group.setDescription("description");
		group.setHashtag("hashtag");
		group.setPassword("password");
		// Set group proprietaire
		group.setProprietaire(addedUser);

		// Add group in database
		GroupDao.addGroup(group);

		// Get group with its id
		group = GroupDao.getGroup("hashtag");

		// Add Demandeur in demande
		Demande demande = new Demande();
		demande.setDemandeur(addedUser);
		demande.setGroup(group);

		// Add demande in database
		DemandeDao.addDemande(demande);

		// Add inviteur in Invitation
		Invitation invitation = new Invitation();
		invitation.setInviteur(addedUser);
		invitation.setGroup(group);

		// Add invitation in database
		InvitationDao.addInvitation(invitation);

		// Add user in Notification
		Notification notification = new Notification();
		notification.setUser(addedUser);

		// Add notification in database
		NotificationDao.addNotification(notification);
		User testUser = UserDao.getUser(tel);
		System.out.println(testUser.getNotifications().size());

		// Add dernierePosition in user
		Date date = new java.util.Date();
		DernierePosition dPosition = new DernierePosition();
		dPosition.setLattitude(5.0);
		dPosition.setLongitude(5.0);
		dPosition.setUser(addedUser);
		dPosition.setTime(new Timestamp(date.getTime()));

		// Add dernierePosition in Database
		DernierePositionDao.addDernierePosition(dPosition);

		// Get dernierePosition for user
		dPosition = DernierePositionDao.getDernierePosition(addedUser);

		// Add dernierePosition in user
		addedUser.setDernierePosition(dPosition);

		// Get demandes for a user
		ArrayList<Demande> lDemandes = DemandeDao.getDemandes(addedUser);
		Set<Demande> sDemandes = new HashSet<Demande>(lDemandes);

		// Add demandes in Users
		addedUser.setDemandes(sDemandes);

		// Add Group in User
		Set<Group> groups = new HashSet<Group>();
		groups.add(group);
		addedUser.setGroups(groups);

		// Get invitations for a user
		ArrayList<Invitation> lInvitations = InvitationDao
				.getInvitations(addedUser);
		Set<Invitation> sInvitations = new HashSet<Invitation>(lInvitations);

		// Add invitations in User
		addedUser.setaInvite(sInvitations);

		// Get notifications in User
		ArrayList<Notification> lNotifications = NotificationDao
				.getNotifications(addedUser);
		Set<Notification> sNotifications = new HashSet<Notification>(
				lNotifications);

		// Add notifications in User
		addedUser.setNotifications(sNotifications);

		Assert.assertEquals(addedUser.getTelephone(),
				initialUser.getTelephone());
		Assert.assertEquals(addedUser.getPseudo(), initialUser.getPseudo());
		Assert.assertEquals(addedUser.getEmail(), initialUser.getEmail());

		List<String> numeros = new ArrayList<String>();

		numeros.add("987493");
		numeros.add("863872763");
		numeros.add("7387387");
		numeros.add("0601020304");

		ArrayList<User> l = UserDao.getRepertoire(numeros);
		Assert.assertTrue(l.contains(addedUser));
	}

	@Test
	public void modifyUserTest() {
		User user = UserDao.getUser(tel);

		user.setPseudo(pseudoModified);
		user.setEmail(email);
		UserDao.modifyUser(user);

		User modifiedUser = UserDao.getUser(tel);

		Assert.assertEquals(modifiedUser.getTelephone(), tel);
		Assert.assertEquals(modifiedUser.getPseudo(), pseudoModified);
		Assert.assertEquals(modifiedUser.getEmail(), email);

	}

	@Test
	public void listUserTest() {
		User modifiedUser = UserDao.getUser(tel);

		List<User> listUsers = UserDao.listUser();

		User listedUser = listUsers.get(listUsers.indexOf(modifiedUser));

		Assert.assertEquals(listedUser.getTelephone(),
				modifiedUser.getTelephone());
		Assert.assertEquals(listedUser.getPseudo(), modifiedUser.getPseudo());
		Assert.assertEquals(listedUser.getEmail(), modifiedUser.getEmail());
	}

	/*
	 * @Test public void deleteUserTest(){ User user = UserDao.getUser(tel);
	 * 
	 * UserDao.deleteUser(user.getTelephone());
	 * 
	 * List<User> listUsers = UserDao.listUser();
	 * 
	 * Assert.assertFalse(listUsers.contains(user)); }
	 */

	// @Test
	public void getGroupsTest() {
		User user1 = new User("telU1", "u1", "mail", "deviceToken");
		User prorio = new User("proprio", "proprio", "mail", "deviceToken");
		UserDao.addUser(user1);
		UserDao.addUser(prorio);

		prorio = UserDao.getUser(prorio.getTelephone());
		user1 = UserDao.getUser(user1.getTelephone());

		Group g1 = new Group("description", "#h", prorio, "password");
		Group g2 = new Group("description", "#test", prorio, "password");
		Group g3 = new Group("description", "#test2", prorio, "password");
		Group g4 = new Group("description", "#test3", prorio, "password");

		g1.addParticipation(user1);
		g2.addParticipation(user1);
		g3.addParticipation(user1);
		g4.addParticipation(user1);

		GroupDao.addGroup(g1);
		GroupDao.addGroup(g2);
		GroupDao.addGroup(g3);
		GroupDao.addGroup(g4);

		User user2 = UserDao.getUser(user1.getTelephone());
		User pro = UserDao.getUser(prorio.getTelephone());
		Set<Group> groups = user2.getGroups();
		Set<Group> groups2 = pro.getProprietaire();

		System.out.println(groups2.size());
		// Assert.assertEquals(4,groups2.size());
		// Assert.assertEquals(4,groups.size());

		/*
		 * GroupDao.deleteGroup("#h"); GroupDao.deleteGroup("#test");
		 * GroupDao.deleteGroup("#test2"); GroupDao.deleteGroup("#test3");
		 * UserDao.deleteUser(prorio.getTelephone());
		 * UserDao.deleteUser(user1.getTelephone());
		 */

	}
}
