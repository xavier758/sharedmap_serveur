package modele;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe permettant de gerer les demandes d'un user pour un groupe
 * 
 * @author SharedMap
 *
 */

@XmlRootElement
public class Demande extends Notification {
	
	// Attributs

	private Group group;

	private User demandeur;

	public Demande() {
		super();
	}
	
	/**
	 * 
	 * @param group
	 * @param demandeur
	 */
	public Demande(Group group, User demandeur) {
		super(group.getProprietaire());
		this.group = group;
		this.demandeur = demandeur;
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
	
	/**
	 * 
	 * @return demandeur
	 */
	public User getDemandeur() {
		return demandeur;
	}
	
	/**
	 * 
	 * @param demandeur
	 */
	@XmlElement
	public void setDemandeur(User demandeur) {
		this.demandeur = demandeur;
	}	
}
