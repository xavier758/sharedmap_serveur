package service;

import dao.UserDao;
import modele.User;

public abstract class AccountManager {
	
	public static User getAccount(String telephone){
		User user = UserDao.getUser(telephone);
		return user;
	}
	
	public static User createAccount(String telephone, String pseudo, String email, String deviceToken){
		User user = new User(telephone,pseudo,email,deviceToken);
		UserDao.addUser(user);
		UserDao.getUser(telephone);
		return user;
	}
	
	public static User modifyAccount(String telephone, String pseudo, String email, String deviceToken){
		User user = UserDao.getUser(telephone);
		user.setPseudo(pseudo);
		user.setEmail(email);
		user.setDeviceToken(deviceToken);
		UserDao.modifyUser(user);
		return user;
	}
}
