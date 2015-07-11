package modele;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationMarqueur extends Notification{
	
	private Marqueur marqueur;

	public NotificationMarqueur(){
		super();
	}
	
	public NotificationMarqueur(User user, Marqueur marqueur) {
		super(user);
		this.marqueur = marqueur;
	}

	public Marqueur getMarqueur() {
		return marqueur;
	}

	@XmlElement
	public void setMarqueur(Marqueur marqueur) {
		this.marqueur = marqueur;
	}

}
