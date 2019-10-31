package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.Userdao;
import main.Blogcomment;
import main.Blogpost;
import main.User;

public class BlogpostController {
	@Autowired
	private Blogpost blogpostdao;
	@Autowired
	private Userdao userdao;
	@RequestMapping(value="/saveblogpost",method=RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody Blogpost blogpost,HttpSession session)
	{
			if(session.getAttribute("username")==null)
			{
				Error error=new Error(5,"UnAuthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
			String username=(String)session.getAttribute("username");
			
			User user=userdao.getUserByUsername(username);
			blogpost.setPostedOn(new Date());
			blogpost.setPostedBy(user);
			try{
			blogpostdao.saveBlogPost(blogpost);
			return new ResponseEntity<Blogpost>(blogpost,HttpStatus.OK);//200 - 1st call back function will be called
			}catch(Exception e)
			{
				Error error=new Error(6,"Unable to insert blog post details " + e.getMessage());
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);//500 - 2nd call back func will be executed
			}
	}
	@RequestMapping(value="/getblogposts/{approved}")
	public ResponseEntity<?> getBlogPosts(@PathVariable int approved , HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		
		List<Blogpost> blogPosts=blogpostdao.getBlogposts(approved);
		return new ResponseEntity<List<Blogpost>>(blogPosts,HttpStatus.OK);
				
		
	}
	@RequestMapping(value="/getblogpostbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPostById(@PathVariable int id, HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		
		Blogpost blogPost=blogpostdao.getBlogpostById(id);
		return new ResponseEntity<Blogpost>(blogPost,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody Blogpost blogPost,HttpSession session)
	{
		if (session.getAttribute("username")==null)
		{
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		try{
			blogpostdao.updateBlogpost(blogPost);
			return new ResponseEntity<Blogpost>(blogPost,HttpStatus.OK);
		}catch(Exception e)
		{
			Error error=new Error(6,"Unable to update blog post" + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogcomment(@RequestBody Blogcomment blogcomment,HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		String username=(String)session.getAttribute("username");
		User user=userdao.getUserByUsername(username);
		blogcomment.setCommentedBy(user);//set the value for foreign key 'username' in blogcomment table
		blogcomment.setCommentedOn(new Date());//set the value for commentedOn
		try{
			System.out.println(blogcomment.getCommentText());
			blogpostdao.addBlogcomment(blogcomment);
			System.out.println("blogcomment added");
			return new ResponseEntity<Blogcomment>(blogcomment,HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			Error error=new Error(7, "Unable to add blogcomment" + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getblogcomments/{blogpostId}")
	public ResponseEntity<?> getAllBlogcomments(@PathVariable int blogpostId,HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		
		List<Blogcomment> blogComments=blogpostdao.getAllBlogcomments( blogpostId);
		return new ResponseEntity<List<Blogcomment>>(blogComments,HttpStatus.OK);
		
	}
}
