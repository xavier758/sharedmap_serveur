package service;

import java.util.ArrayList;
import java.util.Set;

import modele.Group;
import modele.User;
import dao.GroupDao;
import dao.UserDao;

public abstract class GroupManager {
	public static Group createGroup(String desc, String hashtag, String password, String telProprio) {
		User proprio = UserDao.getUser(telProprio);
		Group group = new Group(desc, hashtag, proprio, password);
		group.addParticipation(proprio);
		GroupDao.addGroup(group);
		group = GroupDao.getGroup(hashtag);
		return group;
	}

	public static Group getGroup(String hashtag) {
		return GroupDao.getGroup(hashtag);
	}

	public static boolean addParticipant(User participant, Group group) {
		group.addParticipation(participant);
		GroupDao.modifyGroup(group);
		return true;
	}

	public static Group removeUser(String hashtag, String telephone) {
		Group group = GroupDao.getGroup(hashtag);
		User userToRemove = UserDao.getUser(telephone);
		userToRemove.removeFromGroup(group);
		UserDao.modifyUser(userToRemove);
		return GroupDao.getGroup(hashtag);

	}

	public static void deleteGroup(String hashtag) {
		GroupDao.deleteGroup(hashtag);
	}

	public static Group modifyGroup(String hashtag, String description, String password) {
		Group group = GroupDao.getGroup(hashtag);
		group.setDescription(description);
		group.setPassword(password);
		GroupDao.modifyGroup(group);
		return GroupDao.getGroup(group.getHashtag());
	}

	public static ArrayList<Group> getGroups(String telephone) {
		// TODO Auto-generated method stub
		User user = UserDao.getUser(telephone);		
		return GroupDao.getGroups(user);
	}
	
	public static Set<User> getUsers(String hashtag)
	{	
		Group group= GroupDao.getGroup(hashtag);
		return  group.getInvites();
		
	}

}
