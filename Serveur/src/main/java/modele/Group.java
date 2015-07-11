package modele;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe permettant de gerer un groupe
 * @author SharedMap
 *
 */
@XmlRootElement
public class Group {

	/** Attributs */

	private Integer id;

	private String description;

	private String hashtag;

	private String password;

	@XmlTransient
	private Set<Marqueur> marqueurs;

	@XmlTransient
	private Set<Invitation> invitations;

	@XmlTransient
	private Set<Demande> demandes;

	private User proprietaire;

	private Set<User> invites;

	/**
	 * Constructeur
	 * */
	public Group() {

	}
	
	/**
	 * 
	 * @param description
	 * @param hashtag
	 * @param proprietaire
	 * @param password
	 */
	public Group(String description, String hashtag, User proprietaire,
			String password) {
		super();
		this.description = description;
		this.hashtag = hashtag;
		this.proprietaire = proprietaire;
		this.password = password;
		this.marqueurs = new HashSet<Marqueur>();
		this.invitations = new HashSet<Invitation>();
		this.demandes = new HashSet<Demande>();
		this.invites = new HashSet<User>();
	}

	/**
	 * Méthodes
	 * 
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
	 * @return hashtag
	 */
	public String getHashtag() {
		return hashtag;
	}
	
	/**
	 * 
	 * @param hashtag
	 */
	@XmlElement
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 
	 * @return marqueurs
	 */
	@XmlTransient
	public Set<Marqueur> getMarqueurs() {
		return marqueurs;
	}
	
	/**
	 * 
	 * @param marqueurs
	 */
	public void setMarqueurs(Set<Marqueur> marqueurs) {
		this.marqueurs = marqueurs;
	}
	
	/**
	 * 
	 * @return invitations
	 */
	@XmlTransient
	public Set<Invitation> getInvitations() {
		return invitations;
	}
	
	/**
	 * 
	 * @param invitations
	 */
	public void setInvitations(Set<Invitation> invitations) {
		this.invitations = invitations;
	}
	
	/**
	 * 
	 * @return proprietaire
	 */
	public User getProprietaire() {
		return proprietaire;
	}
	
	/**
	 * 
	 * @param proprietaire
	 */
	@XmlElement
	public void setProprietaire(User proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	/**
	 * 
	 * @return invites
	 */
	public Set<User> getInvites() {
		return invites;
	}
	
	/**
	 * 
	 * @param invites
	 */
	@XmlElement
	public void setInvites(Set<User> invites) {
		this.invites = invites;
	}
	
	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password
	 */
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @param id
	 */
	@XmlElement
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return demandes
	 */
	@XmlTransient
	public Set<Demande> getDemandes() {
		return demandes;
	}
	
	/**
	 * 
	 * @param demandes
	 */
	public void setDemandes(Set<Demande> demandes) {
		this.demandes = demandes;
	}
	
	/**
	 * 
	 * @param participant
	 */
	public void addParticipation(User participant) {
		this.invites.add(participant);		
	}
	
	/**
	 * 
	 * @param participant
	 */
	public void removeParticipation(User participant) {
		this.invites.remove(participant);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		if (this.getId() == ((Group) other).getId())
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
