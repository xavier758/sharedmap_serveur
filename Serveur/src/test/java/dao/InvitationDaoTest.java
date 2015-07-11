package dao;

import java.util.ArrayList;

import junit.framework.Assert;
import modele.Group;
import modele.Invitation;
import modele.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InvitationDaoTest {
	private User inviteur;
	private User user;

	private Group group;

	@Before
	public void initialize() {
		// create dependencies
		inviteur = new User("tel1", "pseudo1", "email1", "deviceToken");
		user = new User("tel2", "pseudo2", "email2", "deviceToken");

		UserDao.addUser(user);
		user = UserDao.getUser(user.getTelephone());

		UserDao.addUser(inviteur);
		inviteur = UserDao.getUser(inviteur.getTelephone());

		group = new Group("description", "hashtag", inviteur, "password");

		GroupDao.addGroup(group);
		group = GroupDao.getGroup(group.getHashtag());
	}

	@Test
	public void invitationTest() {
		// add test
		Invitation initialInvitation = new Invitation(inviteur, user, group);

		Integer idInvitation = InvitationDao.addInvitation(initialInvitation);

		Invitation invitationAdded = InvitationDao.getInvitation(idInvitation);

		Assert.assertEquals(initialInvitation.getId(), invitationAdded.getId());
		Assert.assertEquals(initialInvitation.getGroup().getId(),
				invitationAdded.getGroup().getId());
		Assert.assertEquals(initialInvitation.getUser().getId(),
				invitationAdded.getUser().getId());
		Assert.assertEquals(initialInvitation.getInviteur().getId(),
				invitationAdded.getInviteur().getId());

		// getInvitations test
		Invitation invitation = InvitationDao.getInvitation(idInvitation);

		ArrayList<Invitation> invitations = InvitationDao.getInvitations(user);

		Assert.assertTrue(invitations.contains(invitation));

		// delete test
		InvitationDao.deleteInvitation(idInvitation);

		invitation = InvitationDao.getInvitation(idInvitation);

		Assert.assertNull(invitation);
	}

	@After
	public void delete() {
		GroupDao.deleteGroup(group.getHashtag());
		UserDao.deleteUser(user.getTelephone());
		UserDao.deleteUser(inviteur.getTelephone());
	}
}
