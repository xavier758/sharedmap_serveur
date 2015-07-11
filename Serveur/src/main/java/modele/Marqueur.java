package modele;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe permettant de gerer un marqueur appartenant à un groupe
 * @author SharedMap
 *
 */
@XmlRootElement
public class Marqueur extends Position {
	
	// Attributs
	private Group group;

	private String description;
	
	private User createur;
	
	@XmlTransient
	private Set<NotificationMarqueur> notificationMarqueur;

	private Date fin;

	public User getCreateur() {
		return createur;
	}
	
	/**
	 * 
	 * @param createur
	 */
	@XmlElement
	public void setCreateur(User createur) {
		this.createur = createur;
	}

	public Marqueur() {
		super();
	}
	
	/**
	 * 
	 * @param group
	 * @param description
	 * @param lattitude
	 * @param longitude
	 * @param fin
	 * @param createur
	 */
	public Marqueur(Group group, String description, double lattitude,
			double longitude, Date fin, User createur) {
		super(lattitude, longitude);
		this.group = group;
		this.description = description;
		this.fin = fin;
		this.createur=createur;
	}
	
	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @param description
	 */
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
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
	 * @return notificationMarqueur
	 */
	@XmlTransient
	public Set<NotificationMarqueur> getNotificationMarqueur() {
		return notificationMarqueur;
	}
	
	/**
	 * 
	 * @param hashSet
	 */
	public void setNotificationMarqueur(
			Set<NotificationMarqueur> hashSet) {
		this.notificationMarqueur = hashSet;
	}
	
	public void addNm(NotificationMarqueur nm){
		this.notificationMarqueur.add(nm);
	}
	

	/**
	 * 
	 * @return dateFin
	 */
	public Date getFin() {
		return fin;
	}
	
	/**
	 * 
	 * @param dateFin
	 */
	@XmlElement
	public void setFin(Date fin) {
		this.fin = fin;
	}
}
