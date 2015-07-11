package service;

import java.util.ArrayList;
import java.util.List;

import modele.User;
import dao.UserDao;

public class RepertoireManager {
	public static ArrayList<User> getRepertoire(List<String> numeros) {
		return UserDao.getRepertoire(numeros);
	}
}
