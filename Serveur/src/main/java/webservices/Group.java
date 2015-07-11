package webservices;

import java.util.ArrayList;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modele.User;
import service.AccountManager;
import service.GroupManager;
import service.NotificationManager;

@Path("/group")
public class Group {
	@POST
	@Produces(MediaType.TEXT_XML)
	@Path("/create")
	public modele.Group newGroup(@QueryParam("desc") String description,
			@QueryParam("hashtag") String hashtag,
			@QueryParam("password") String password,
			@QueryParam("tel") String telProprio) {
		return GroupManager.createGroup(description, hashtag, password,
				telProprio);
	}

	@DELETE
	@Produces(MediaType.TEXT_XML)
	@Path("/removeUser")
	public modele.Group removeUser(@QueryParam("hashtag") String hashtag, @QueryParam("telephone") String telephone) {
		return GroupManager.removeUser(hashtag, telephone);
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/get")
	public modele.Group getGroup(@QueryParam("hashtag") String hashtag) {
		return GroupManager.getGroup(hashtag);
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/getGroups")
	public ArrayList<modele.Group> getGroups(@QueryParam("tel") String telephone) {
		System.out.println(telephone);
		return GroupManager.getGroups(telephone);
	}

	@DELETE
	@Path("/delete")
	public boolean deleteGroup(@QueryParam("hashtag") String hashtag) {
		GroupManager.deleteGroup(hashtag);
		return true;
	}

	@PUT
	@Produces(MediaType.TEXT_XML)
	@Path("/modify")
	public modele.Group modifyGroup(@QueryParam("hashtag") String hashtag,@QueryParam("description") String description,@QueryParam("password") String password) {
		return GroupManager.modifyGroup(hashtag, description, password);
	}
	
	@POST
	@Path("/joinGroup")
	public boolean joinGroup(@QueryParam("hashtag") String hashtag,  @QueryParam("password") String password, @QueryParam("tel") String telephone) {
		User user = AccountManager.getAccount(telephone);
		modele.Group group = GroupManager.getGroup(hashtag);
	
		if (group.getPassword().equals(password) && !group.getInvites().contains(user)){
			GroupManager.addParticipant(user, group);
			NotificationManager.deleteDemande(group, user);
			NotificationManager.deleteInvitation(group, user);
			return true;
		}
		else{
			return false;
		}
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/getUsers")
	public Set<User> getUsers(@QueryParam("hashtag") String hashtag)
	{
		System.out.println(hashtag);
		return GroupManager.getUsers(hashtag);	
	}
	
	
}
