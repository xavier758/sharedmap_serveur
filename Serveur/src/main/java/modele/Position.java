package modele;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Position {

	private Integer id;

	private double lattitude;

	private double longitude;

	public Position() {
	}

	public Position(double lattitude, double longitude) {
		super();
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public double getLattitude() {
		return lattitude;
	}

	@XmlElement
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@XmlElement
	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
		if (this.getId() == ((Position) other).getId())
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
