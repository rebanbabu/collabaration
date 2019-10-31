package controller;

import java.util.Date;
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

import dao.Jobdao;
import dao.Userdao;
import main.Job;
import main.User;

@Controller
public class JobController {
	@Autowired
	private Jobdao jobdao;
	
	@Autowired
	private Userdao userdao;
	
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job, HttpSession session)
	{
		System.out.println(session.getAttribute("username"));
		if(session.getAttribute("username")==null)
		{
			Error error = new Error(5,"Unauthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username = (String)session.getAttribute("username");
		User user = userdao.getUserByUsername(username);
		if(user.getRole().equals("ADMIN"))
		{
			try{
				job.setPostedOn(new Date());
			jobdao.saveJob(job);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			}catch(Exception e){
				Error error = new Error (7, "Unable to insert job details" + e.getMessage());
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			Error error = new Error(6,"Access Denied");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
	}
	@RequestMapping(value="/getallJobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error = new Error(5,"Unauthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobdao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getjobbyid/{job}",method=RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int job,HttpSession session)
	{
		if(session.getAttribute("username")==null)
		{
			Error error = new Error(5,"Unauthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Job job1 = jobdao.getJobById(job);
		return new ResponseEntity<Job>(job1,HttpStatus.OK);	
	}
	
}
