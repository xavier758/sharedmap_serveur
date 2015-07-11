package modele;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlSeeAlso({ Demande.class, Invitation.class, NotificationMarqueur.class })
public class Notification {

	@XmlTransient
	private Integer id;

	private User user;

	public Notification() {
	}

	public Notification(User user) {
		this.user = user;
	}

	@XmlTransient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	@XmlElement
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object other) {
		if (this.getId() == ((Notification) other).getId())
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
