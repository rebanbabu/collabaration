package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.Profilepicturedao;
import main.Profilepicture;

@Controller
public class ProfilepictureController {
	@Autowired
	private Profilepicturedao profilepicturedao;
	
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePic(@RequestParam CommonsMultipartFile image,HttpSession session)
    {
		System.out.println("hello");
    	if(session.getAttribute("username")==null)
		{
			Error error = new Error(5,"Unauthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
    	String username=(String)session.getAttribute("username");
    	Profilepicture profilepicture=new Profilepicture();
    	profilepicture.setUsername(username);
    	profilepicture.setImage(image.getBytes());
    	try{
    		profilepicturedao.saveProfilePicture(profilepicture);
    		return new ResponseEntity<Profilepicture>(profilepicture,HttpStatus.OK);
    	}catch(Exception e){
    		Error error=new Error(6,"Unable to upload profile picture");
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
	@RequestMapping(value="/getimage/{username}",method=RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable String username,HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
		  return null;
		}
		else{
			Profilepicture profilePicture=profilepicturedao.getProfilepicture(username);
			if(profilePicture==null)
				return null;
			return profilePicture.getImage();
		}
	}
	
}
