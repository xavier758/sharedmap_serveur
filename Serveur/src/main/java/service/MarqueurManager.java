package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import modele.Group;
import modele.Marqueur;
import modele.NotificationMarqueur;
import modele.User;
import dao.MarqueurDao;
import dao.NotificationMarqueurDao;

public class MarqueurManager {
	
	/**
	 * 
	 * @param idMarqueur
	 * @return
	 */
	
	public static Marqueur getMarqueur(int idMarqueur)
	{
		Marqueur marqueur = MarqueurDao.getMarqueur(idMarqueur);
		return marqueur;
	}
	
	
	/**
	 * 
	 * @param group
	 * @param description
	 * @param lattitude
	 * @param longitude
	 * @param date
	 * @param user
	 * @return
	 */
	public static Marqueur addMarqueur(Group group, String description,	double lattitude, double longitude, Date date, User user) {
		// TODO Auto-generated method stub
		Marqueur marqueur = new Marqueur(group,description,lattitude, longitude, date, user);
		int id = MarqueurDao.addMarqueur(marqueur);
		marqueur = MarqueurDao.getMarqueur(id);
		Set<User> users = group.getInvites();
		Iterator<User> it = users.iterator();
		
		while (it.hasNext()){
			User participant = it.next();
			//Pas de notification pour le createur
			if (!participant.equals(user)){
				NotificationMarqueur nm = new NotificationMarqueur(participant, marqueur);
				NotificationMarqueurDao.addNotificationMarqueur(nm);
				//envoi d'une notification en push
				try{
					Sender sender = new Sender("AIzaSyBUBqozlx4e2TwMLwSzAFr9eyLbjJXgByQ");	
					ArrayList<String> devicesList = new ArrayList<String>();
					devicesList.add(participant.getDeviceToken());
					// Use this line to send message without payload data
					// Message message = new Message.Builder().build();
			
					// use this line to send message with payload data
					Message message = new Message.Builder()
							.collapseKey("1")
							.delayWhileIdle(true)
							.addData("message",	user.getPseudo()+" a ajouté un marqueur sur la carte #" + group.getHashtag())
							.build();
			
					// Use this code to send to a single device
					 Result result = sender.send(message, participant.getDeviceToken(), 1);
			
					// Use this for multicast messages
//					MulticastResult result = sender.send(message, devicesList, 1);
//					sender.send(message, devicesList, 1);
		//	
//					System.out.println(result.toString());
//					if (result.getResults() != null) {
//						int canonicalRegId = result.getCanonicalIds();
//						if (canonicalRegId != 0) {
//						}
//					} else {
//						int error = result.getFailure();
//						System.out.println(error);
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		
		return marqueur;
	}
	
	/**
	 * 
	 * @param marqueur
	 * @return
	 */
	public static boolean deleteMarqueur(Marqueur marqueur)
	{
		MarqueurDao.deleteMarqueur(marqueur);
		return true;
	}

	public static ArrayList<Marqueur> getMarqueurs(Group group){
		return MarqueurDao.getMarqueurs(group);
	}


	public static void deleteMarqueurs(Group group, User user) {
		NotificationMarqueurDao.deleteNotificationMarqueurs(group,user);		
	}
	
	public static void deleteOldMarqueur(){
		MarqueurDao.deleteOldMarqueurs();
	}
	
}
