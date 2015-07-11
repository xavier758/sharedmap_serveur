package modele;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe permettant de gerer les invitations à un groupe
 * @author SharedMap
 *
 */
@XmlRootElement
public class Invitation extends Notification {
	
	// Attributs
	private User inviteur;
	
	private Group group;

	public Invitation() {
		super();
	}
	
	/**
	 * 
	 * @param inviteur
	 * @param friend
	 * @param group
	 */
	public Invitation(User inviteur, User friend, Group group) {
		super(friend);
		this.inviteur = inviteur;
		this.group = group;
	}
	
	/**
	 * 
	 * @return inviteur
	 */
	public User getInviteur() {
		return inviteur;
	}
	
	/**
	 * 
	 * @param inviteur
	 */
	@XmlElement
	public void setInviteur(User inviteur) {
		this.inviteur = inviteur;
	}
	
	/**
	 * 
	 * @return group
	 */
	public Group getGroup() {
		return group;
	}
	
	/**
	 * 
	 * @param group
	 */
	@XmlElement
	public void setGroup(Group group) {
		this.group = group;
	}
}
