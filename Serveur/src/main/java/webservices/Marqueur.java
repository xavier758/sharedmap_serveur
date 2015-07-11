package webservices;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modele.User;
import dao.MarqueurDao;
import dao.UserDao;
import service.AccountManager;
import service.GroupManager;
import service.MarqueurManager;

@Path("/marqueur")
public class Marqueur {

	  @POST	  
	  @Path("/addMarqueur")
	  @Produces(MediaType.TEXT_XML) 
	  public modele.Marqueur addMarqueur(@QueryParam("tel") String tel,@QueryParam("hashtag") String hashtag, @QueryParam("desc") String description, @QueryParam("longitude") double longitude, @QueryParam("lattitude") double lattitude, @QueryParam("fin") long dateFin) {
		User user = AccountManager.getAccount(tel);
		modele.Group group = GroupManager.getGroup(hashtag);
		Timestamp ts = new Timestamp(dateFin);
		return MarqueurManager.addMarqueur(group,description,lattitude, longitude, ts, user);
	  }

	  @DELETE
	  @Path("/deleteMarqueur")
	  public boolean deleteMarqueur(@QueryParam("id") int idMarqueur)
	  {
		  modele.Marqueur marqueur = MarqueurManager.getMarqueur(idMarqueur);
		  MarqueurDao.deleteMarqueur(marqueur);
		  return true;
	  }

	  @GET
	  @Path("/getMarqueurs")
	  @Produces(MediaType.TEXT_XML) 
	  public ArrayList<modele.Marqueur> getMarqueurs(@QueryParam("hashtag") String hashtag, @QueryParam("tel") String tel) {		
		modele.Group group = GroupManager.getGroup(hashtag);
		User user = UserDao.getUser(tel);
		MarqueurManager.deleteMarqueurs(group,user);
		return MarqueurManager.getMarqueurs(group);
	  }
	  
	  @DELETE
	  @Path("/deleteOldMarqueurs")
	  public void deleteOldMarqueurs(){
		  MarqueurManager.deleteOldMarqueur();
	  }
}
