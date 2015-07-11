package webservices;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.GroupDao;
import modele.Invitation;
import modele.User;
import service.RepertoireManager;

@Path("/repertoire")
public class Repertoire{

	  @POST
	  @Produces(MediaType.TEXT_XML)
	  @Consumes(MediaType.TEXT_XML)
	  @Path("/getFriends")
	  public ArrayList<User> getRepertoire(String xml, @QueryParam("hashtag") String hashtag){		  
		  Document doc = null;
		  try { 
			DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder(); 
			doc = newDocumentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
		} catch (SAXException | IOException | ParserConfigurationException | FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(hashtag);
		  
		  Node repertoire = doc.getFirstChild();		  
		  NodeList users = repertoire.getChildNodes();		  
		  List<String> numeros = new ArrayList<String>();

		  modele.Group group = GroupDao.getGroup(hashtag);
		  Set<User> usersInGroup = new HashSet<User>();
		  usersInGroup = group.getInvites();
		  Iterator<User> itu = usersInGroup.iterator();
		  List<String> usersInGroup2= new ArrayList<String>();
		  
		  while (itu.hasNext()){
			  usersInGroup2.add(itu.next().getTelephone());
		  }
		  
		  Set<User> usersInvited = new HashSet<User>();
		  Iterator<Invitation> iti = group.getInvitations().iterator();
		  while (iti.hasNext()){				  
				  usersInvited.add(iti.next().getUser());
		  }
		  Iterator<User> itui = usersInvited.iterator();
		  List<String> usersInvited2 = new ArrayList<String>();
		  
		  while (itui.hasNext()){
			  usersInvited2.add(itui.next().getTelephone());
		  }
		  
		  //loop through the users of the repertory
		  for (int i=0;i<users.getLength();i++){
			  //filter the users that are already in the group and the ones who already have been invited
			  if (!usersInGroup2.contains(users.item(i).getFirstChild().getNodeValue()) && !usersInvited2.contains(users.item(i).getFirstChild().getNodeValue())){
				  numeros.add(users.item(i).getFirstChild().getNodeValue());
			  }				 
		  }		  
		  
		  ArrayList<User> u = RepertoireManager.getRepertoire(numeros);
		  
		  Iterator<User> ituu =u.iterator();
	      while (ituu.hasNext()){
	    	  System.out.println(ituu.next().getPseudo());
	      }
		  
		  return u;	  
	  }
}
