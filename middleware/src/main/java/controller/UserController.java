package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.Userdao;
import main.User;

@RestController
public class UserController
{
	@Autowired
	private Userdao userdao;
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
    	try{
    	User duplicateUser	=userdao.validateUsername(user.getUsername());
    	if(duplicateUser!=null){
    		//response.data is error
    		//response.status is 406 NOT_ACCEPTABLE
    		Error error=new Error(2,"Username already exists.. please enter different username");
    		return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
    	}
    	User duplicateEmail=userdao.validateEmail(user.getEmail());
    	if(duplicateEmail!=null){
    		//response.data is error
    		//response.status is 406 NOT_ACCEPTABLE
    		Error error=new Error(3,"Email address already exists.. please enter different email address");
    		return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
    	}
		userdao.registerUser(user);
	
		return new ResponseEntity<User>(user,HttpStatus.OK);
    	}catch(Exception e){
    		//response.data is error
    		//response.status is 406 NOT_ACCEPTABLE
    		Error error=new Error(1,"Unable to register user details " + e.getMessage());
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	//HttpRequest Body : {username:"adam" , password:"123"}
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session)
	{
		User validuser = userdao.login(user);
		if (validuser==null)//invalid credentials
		{
			Error error = new Error (4, "Invalid username/password..please enter valid username/password");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		validuser.setOnline(true);
		userdao.update(validuser);//update only the online status from 0 to 1
		session.setAttribute("username", validuser.getUsername());
		//HttpResponse Body;
		// {username :"adam" , password:"123", firstname="Adam", lastname="Eve", email :"adam@gmail.com", online_status :"true"
		return new ResponseEntity<User>(validuser, HttpStatus.OK);
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session)
	{
		if (session.getAttribute("username")==null)
		{
			Error error = new Error(5,"UnAuthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String)session.getAttribute("username");
		User user = userdao.getUserByUsername(username);
		user.setOnline(false);
		userdao.update(user);
		session.removeAttribute("username");
        session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session)
	{
		if (session.getAttribute("username")==null)
		{
			Error error = new Error(5,"UnAuthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username=(String)session.getAttribute("username");
		User user=userdao.getUserByUsername(username);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	@RequestMapping(value="/updateuser",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user, HttpSession session)
	{
		if (session.getAttribute("username")==null)
		{
			Error error = new Error(5,"UnAuthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		try{
			userdao.update(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e){
			Error error = new Error(6,"Unable to edit user profile"+ e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}


