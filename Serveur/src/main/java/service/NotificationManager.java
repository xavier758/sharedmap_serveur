package service;

import java.util.ArrayList;

import dao.DemandeDao;
import dao.InvitationDao;
import dao.NotificationDao;
import modele.Demande;
import modele.Group;
import modele.Invitation;
import modele.Notification;
import modele.User;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class NotificationManager {

	public static boolean inviteFriend(User inviteur, User friend, Group group) {
		Invitation invitation = new Invitation(inviteur, friend, group);
		InvitationDao.addInvitation(invitation);
		try{
			Sender sender = new Sender("AIzaSyBUBqozlx4e2TwMLwSzAFr9eyLbjJXgByQ");	
			ArrayList<String> devicesList = new ArrayList<String>();
			devicesList.add(friend.getDeviceToken());
			// Use this line to send message without payload data
			// Message message = new Message.Builder().build();
	
			// use this line to send message with payload data
			Message message = new Message.Builder()
					.collapseKey("1")
					.delayWhileIdle(true)
					.addData("message",	inviteur.getPseudo()+" vous a invité à rejoindre le groupe #" + group.getHashtag())
					.build();
	
			// Use this code to send to a single device
			 Result result = sender.send(message, friend.getDeviceToken(), 1);
	
			// Use this for multicast messages
//			MulticastResult result = sender.send(message, devicesList, 1);
//			sender.send(message, devicesList, 1);
//	
			System.out.println(result.toString());
//			if (result.getResults() != null) {
//				int canonicalRegId = result.getCanonicalIds();
//				if (canonicalRegId != 0) {
//				}
//			} else {
//				int error = result.getFailure();
//				System.out.println(error);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public static boolean demande(User demandeur, modele.Group group) {
		Demande demande = new Demande(group, demandeur);
		DemandeDao.addDemande(demande);
		//envoi d'une notification en push
		try{
			Sender sender = new Sender("AIzaSyBUBqozlx4e2TwMLwSzAFr9eyLbjJXgByQ");	
			ArrayList<String> devicesList = new ArrayList<String>();
			devicesList.add(group.getProprietaire().getDeviceToken());
			// Use this line to send message without payload data
			// Message message = new Message.Builder().build();
	
			// use this line to send message with payload data
			Message message = new Message.Builder()
					.collapseKey("1")
					.delayWhileIdle(true)
					.addData("message",	demandeur.getPseudo()+" a demand� � rejoindre le groupe #" + group.getHashtag())
					.build();
	
			// Use this code to send to a single device
			 Result result = sender.send(message, group.getProprietaire().getDeviceToken(), 1);
	
			// Use this for multicast messages
//			MulticastResult result = sender.send(message, devicesList, 1);
//			sender.send(message, devicesList, 1);
//	
//			System.out.println(result.toString());
//			if (result.getResults() != null) {
//				int canonicalRegId = result.getCanonicalIds();
//				if (canonicalRegId != 0) {
//				}
//			} else {
//				int error = result.getFailure();
//				System.out.println(error);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Demande getDemande(int id){
		return DemandeDao.getDemande(id);
	}
	
	public static Invitation getInvitation(int id){
		return InvitationDao.getInvitation(id);
	}

	public static ArrayList<Notification> getNotifications(User user) {
		return NotificationDao.getNotifications(user);
	}
	
	public static void deleteDemande(Group group, User user)
	{	
		ArrayList <Demande> demandes = DemandeDao.getDemandes(user);
		
		for (int i=0; i<demandes.size(); i++)
		{
			if (demandes.get(i).getGroup().equals(group))
			{
				DemandeDao.deleteDemande(demandes.get(i).getId());;
			}
		}

	}
	
	public static void deleteInvitation(Group group,User user)
	{
		ArrayList <Invitation> invtations = InvitationDao.getInvitations(user);
		
		for (int i=0; i<invtations.size(); i++)
		{
			if (invtations.get(i).getGroup().equals(group))
			{
				InvitationDao.deleteInvitation(invtations.get(i).getId());
			}
		}
		
	}


}
