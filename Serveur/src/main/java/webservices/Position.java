package webservices;

import java.sql.Timestamp;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modele.User;
import service.PositionManager;

@Path("/position")
public class Position {

	@PUT
	@Produces(MediaType.TEXT_XML)
	@Path("/setPosition")
	public User setPosition(@QueryParam("tel") String tel,
			@QueryParam("lattitude") double lattitude,
			@QueryParam("longitude") double longitude,
			@QueryParam("date") long date) {
		Timestamp ts = new Timestamp(date);
		return PositionManager.setPosition(tel, lattitude, longitude, ts);
		
	}
}
