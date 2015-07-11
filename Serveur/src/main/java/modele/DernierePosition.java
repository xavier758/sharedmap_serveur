package modele;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe permettant de gerer la derniere position d'un user
 * @author SharedMap
 *
 */
@XmlRootElement
public class DernierePosition extends Position {
	
	// Attributs

	private Date time;

	@XmlTransient
	private User user;
	
	public DernierePosition() {
		super();
	}
	
	/**
	 * 
	 * @param lattitude
	 * @param longitude
	 * @param user
	 */
	public DernierePosition(double lattitude, double longitude, Date time, User user) {
		super(lattitude, longitude);
		this.time = time;
		this.user=user;
	}
	
	/**
	 * 
	 * @return date
	 */
	public Date getTime() {
		return time;
	}
	
	/**
	 * 
	 * @param time
	 */
	@XmlElement
	public void setTime(Date time) {
		this.time = time;
	}

	@XmlTransient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
