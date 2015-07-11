package webservices;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import service.AccountManager;
import modele.User;

@Path("/account")
public class Account {
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/get")
	public User getAccount(@QueryParam("tel") String telephone) {
		return AccountManager.getAccount(telephone);
	}

	@POST
	@Produces(MediaType.TEXT_XML)
	@Path("/create")
	public User signup(@QueryParam("tel") String telephone,
			@QueryParam("pseudo") String pseudo,
			@QueryParam("email") String email, @QueryParam("deviceToken") String deviceToken) {
		return AccountManager.createAccount(telephone, pseudo, email, deviceToken);
	}

	@PUT
	@Produces(MediaType.TEXT_XML)
	@Path("/modify")
	public User modifyUser(@QueryParam("tel") String telephone,
			@QueryParam("pseudo") String pseudo,
			@QueryParam("email") String email,
			@QueryParam("deviceToken") String deviceToken) {
		return AccountManager.modifyAccount(telephone, pseudo, email, deviceToken);
	}
}
