package webservices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.DemandeDao;
import dao.InvitationDao;
import modele.Demande;
import modele.Invitation;
import modele.Notification;
import modele.User;
import service.AccountManager;
import service.GroupManager;
import service.NotificationManager;

/**
 * 
 * @author SharedMap
 *
 */
@Path("/notification")
public class Notifications {
	  /**
	   * Retourne toutes les demandes d'un user
	   * 
	   * @param telephone
	   * @return demandes
	   */
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  @Path("/getNotifications")
	  public ArrayList<Notification> getDemandes(@QueryParam("tel") String telephone){
		  User user = AccountManager.getAccount(telephone);
		  return NotificationManager.getNotifications(user);
	  }	  
	  
	  /**
	   * Envoyer une invitation à sa liste d'amis
	   * 
	   * @param telProprio
	   * @param telFriend
	   * @param hashtag
	   * @return
	   */
	  @POST	  
	  @Path("/inviteFriends")
	  public boolean invite(@QueryParam("tel") String telProprio, @QueryParam("telFriend") List<String> telFriend, @QueryParam("hashtag") String hashtag) {
		
		  System.out.println(telProprio);
		  System.out.println(hashtag);
		  System.out.println(telFriend.size());
		  
		  User proprio = AccountManager.getAccount(telProprio);
		  modele.Group group = GroupManager.getGroup(hashtag);

		  for (int i=0;i<telFriend.size();i++){
			  User friend = AccountManager.getAccount(telFriend.get(i));
			  NotificationManager.inviteFriend(proprio, friend, group);
		  }		  
		  return true;
	  }
	  
	  /**
	   * Envoyer une demande pour un groupe 
	   * 
	   * @param tel
	   * @param hashtag
	   * @return
	   */
	  @POST	  
	  @Path("/demande")
	  public boolean demande(@QueryParam("tel") String tel, @QueryParam("hashtag") String hashtag) {
		  User user = AccountManager.getAccount(tel);		 
		  modele.Group group = GroupManager.getGroup(hashtag);
		  
		  boolean add = true;
		  //on n'ajoute pas de demande si l'user en a déjà fait une
		  Set<Demande> demandes = user.getDemandes();
		  Iterator<Demande> itd = demandes.iterator();
		  while(itd.hasNext()){
			  if (itd.next().getGroup().equals(group)){
				  add = false;
			  }
		  }
		 //on n'ajoute pas de demande si l'user a été invité
		  Set<Invitation> invitations = group.getInvitations();
		  Iterator<Invitation> iti = invitations.iterator();
		  while(iti.hasNext()){
			  if (iti.next().getUser().equals(user)){
				  add = false;
			  }
		  }
		  
		  if (add==true){
			  NotificationManager.demande(user, group);
		  }		  
		  
		  return add;
	  }
	  

	  /**
	   * Recuperer la reponse à une invitation
	   * @param idInvitation
	   * @param reponse
	   * @return
	   */
	  @POST
	  @Path("/repondInvitation")
	  public boolean repondInvitation(@QueryParam("idInvitation") int idInvitation, @QueryParam("reponse") int reponse){
		  Invitation invit = NotificationManager.getInvitation(idInvitation);
		  //decline invitation
		  if (reponse==1){
			  
		  }
		  //accept
		  else if (reponse==2){
			  User participant = invit.getUser();
			  modele.Group group = invit.getGroup();
			  GroupManager.addParticipant(participant, group);
		  }		  
		  InvitationDao.deleteInvitation(idInvitation);
		  return true;
	  }
	  
	  /**
	   * Recuperer la reponse à une demande
	   * @param idDemande
	   * @param reponse
	   * @return
	   */
	  @POST
	  @Path("/repondDemande")
	  public boolean repondDemande(@QueryParam("idDemande") int idDemande, @QueryParam("reponse") int reponse){
		  Demande demande = NotificationManager.getDemande(idDemande);
		  //decline invitation
		  if (reponse==1){
			  
		  }
		  //accept
		  else if (reponse==2){
			  User participant = demande.getDemandeur();
			  modele.Group group = demande.getGroup();
			  GroupManager.addParticipant(participant, group);
		  }		  
		  
		  DemandeDao.deleteDemande(idDemande);
		  return true;
	  }
	  
}
