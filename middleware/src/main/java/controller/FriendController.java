package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.Frienddao;
import dao.Userdao;
import main.Friend;
import main.User;

@Controller
public class FriendController {
	
	 
	
	  @Autowired
	  private Frienddao frienddao;
	  
	  @Autowired
	  private Userdao userdao;
	  
	  @RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)
	  public ResponseEntity<?> getListOfSuggestedUsers(HttpSession session)
	  {
		  if(session.getAttribute("username")==null)
			{
				Error error=new Error(5,"UnAuthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
		  String username=(String)session.getAttribute("username");
		  List<User> suggestedUsers=frienddao.getListOfSuggestedUsers(username);
		  return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	  }
	  @RequestMapping(value="/friendrequest/{toId}",method=RequestMethod.POST)
	  public ResponseEntity<?> friendRequest(@PathVariable String toId,HttpSession session)
	  {
		  if(session.getAttribute("username")==null)
			{
				Error error=new Error(5,"UnAuthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
		  String username=(String)session.getAttribute("username");
		  frienddao.addFriendRequest(username,toId);
		  return new ResponseEntity<Void>(HttpStatus.OK);
	  }
	  @RequestMapping(value="/getpendingrequests",method=RequestMethod.GET)
	  public ResponseEntity<?> getPendingRequests(HttpSession session)
	  {
		  if(session.getAttribute("username")==null)
			{
				Error error=new Error(5,"UnAuthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
		  String username=(String)session.getAttribute("username");
		  List<Friend> pendingRequests=frienddao.getPendingRequests(username);
		  return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	  }
	  @RequestMapping(value="/getuserdetails/{fromId}",method=RequestMethod.GET)
	  public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session)
	  {
		  if(session.getAttribute("username")==null)
			{
				Error error=new Error(5,"UnAuthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
		  User user=userdao.getUserByUsername(fromId);
		  return new ResponseEntity<User>(user,HttpStatus.OK);
	  }
	  @RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
	  public ResponseEntity<?> updatePendingRequest(@RequestBody Friend pendingRequest,HttpSession session)
	  {
		  if(session.getAttribute("username")==null)
			{
				Error error=new Error(5,"UnAuthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
		  frienddao.updatePendingRequest(pendingRequest);//updated status 'A'/'D'
		  return new ResponseEntity<Friend>(pendingRequest,HttpStatus.OK);
	  }
	  	  
	  @RequestMapping(value="/listoffriends",method=RequestMethod.GET)
	  public ResponseEntity<?> listOfFriends(HttpSession session){
		  if(session.getAttribute("username")==null)
				{
					Error error=new Error(5,"UnAuthorized User");
					return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
				}
		  String username=(String)session.getAttribute("username");
		  List<Friend> friends=frienddao.listOfFriends(username);
		  return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
	  }
}
