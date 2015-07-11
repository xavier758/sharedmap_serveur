package service;

import java.util.Date;

import modele.DernierePosition;
import modele.User;
import dao.DernierePositionDao;
import dao.UserDao;

public class PositionManager {
	public static User setPosition(String tel, double lattitude, double longitude, Date date){
		User user = UserDao.getUser(tel);
		DernierePosition dp = DernierePositionDao.getDernierePosition(user);
		if (dp==null){
			DernierePosition dernierePosition = new DernierePosition(lattitude, longitude, date, user);
			DernierePositionDao.addDernierePosition(dernierePosition);
		}
		else{
			dp.setLattitude(lattitude);
			dp.setLongitude(longitude);
			dp.setTime(date);
			DernierePositionDao.modifyDernierePosition(dp);
		}		
		user = UserDao.getUser(tel);
		return user;
	}
}
