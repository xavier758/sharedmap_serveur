package modele;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class User {

	/** Attributs */

	private Integer id;

	private String pseudo;

	private String telephone;

	private String email;
	
	private String deviceToken;

	@XmlTransient
	private Set<Demande> demandes;

	@XmlTransient
	private Set<Invitation> aInvite;

	@XmlTransient
	private Set<Notification> notifications;

	private DernierePosition dernierePosition;

	@XmlTransient
	private Set<Group> groups;

	@XmlTransient
	private Set<Group> proprietaire;
	
	@XmlTransient
	private Set<Marqueur> marqueurs;

	/** Constructeur */
	public User() {
	}

	public User(String telephone, String pseudo, String email, String deviceToken) {
		super();
		this.pseudo = pseudo;
		this.telephone = telephone;
		this.email = email;
		this.deviceToken=deviceToken;
		this.demandes = new HashSet<Demande>();
		this.aInvite = new HashSet<Invitation>();
		this.notifications = new HashSet<Notification>();
		this.groups = new HashSet<Group>();
		this.proprietaire = new HashSet<Group>();
	}

	public Integer getId() {
		return id;
	}

	@XmlElement
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	@XmlElement
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getTelephone() {
		return telephone;
	}

	@XmlElement
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	@XmlTransient
	public Set<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(Set<Demande> demandes) {
		this.demandes = demandes;
	}

	public DernierePosition getDernierePosition() {
		return dernierePosition;
	}

	@XmlElement
	public void setDernierePosition(DernierePosition dernierePosition) {
		this.dernierePosition = dernierePosition;
	}

	@XmlTransient
	public Set<Group> getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Set<Group> proprietaire) {
		this.proprietaire = proprietaire;
	}

	@XmlTransient
	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	@XmlTransient
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@XmlTransient
	public Set<Invitation> getaInvite() {
		return aInvite;
	}

	public void setaInvite(Set<Invitation> aInvite) {
		this.aInvite = aInvite;
	}

	public void addToGroups(Group group) {
		this.groups.add(group);
	}

	public void removeFromGroup(Group group) {
		this.getGroups().remove(group);
		group.getInvites().remove(this);
	}

	@XmlTransient
	public Set<Marqueur> getMarqueurs() {
		return marqueurs;
	}

	public void setMarqueurs(Set<Marqueur> marqueurs) {
		this.marqueurs = marqueurs;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	@XmlElement
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Override
	public boolean equals(Object other) {
		if (this.getId() == ((User) other).getId())
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
