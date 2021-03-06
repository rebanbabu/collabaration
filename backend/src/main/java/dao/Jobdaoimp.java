package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.Job;
@Repository
@Transactional
public class Jobdaoimp implements Jobdao {
	@Autowired
	SessionFactory sessionFactory;
	
	public void saveJob(Job job) 
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
		//session.flush();
		//session.close();
	}

    
	public List<Job> getAllJobs()
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
				
		return query.list();
	}

    
	public Job getJobById(int job) 
    {
    	Session session=sessionFactory.getCurrentSession();
    	Job job1=(Job)session.get(Job.class, job);
    	
		return job1;
	}
	
}
