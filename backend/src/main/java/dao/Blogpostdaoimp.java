package dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.Blogpost;
import main.Blogcomment;
import main.Blogpost;
@Repository
@Transactional
public class Blogpostdaoimp implements Blogpostdao {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	public List<Blogpost> getBlogposts(int approved)
	{
		Session session=sessionFactory.getCurrentSession();
		//if approved method parameter is 0 => select * from blogpost where approved = 0;[ waiting for approval]
		//if approved method parameter is 1 => select * from blogpost where approved = 1;[ approved blogpost]
		Query query=session.createQuery("from BlogPost where approved="+approved);
		//session.flush();
		return query.list();
	}

	
	public Blogpost getBlogpostById(int id)
	{
		Session session=sessionFactory.getCurrentSession();
		Blogpost blogpost=(Blogpost)session.get(Blogpost.class, id);
		//session.flush();
		return blogpost;
	}

	
	

	
	

	public void addBlogcomment(Blogcomment blogcomment)
	{
		if(blogcomment==null)
		{
			System.out.println("null found");
		}
		
		
		Session session=sessionFactory.getCurrentSession();
		System.out.println("executing");
		try {

	           System.out.println(blogcomment.getBlogpost());
	           System.out.println(blogcomment.getCommentedOn());
			session.save(blogcomment);
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		System.out.println("inserted");
		//session.flush();
		  
		
		//session.close();
	}

	

	public List<Blogcomment> getAllBlogcomments(int blogpostId) 
	{
		Session session=sessionFactory.getCurrentSession();
		//select * from blogcomment where blogpost_id=246
		Query query=session.createQuery("from BlogComment where blogPost.id=?");
		query.setInteger(0,  blogpostId);
		//session.flush();
		return query.list();
	}



	



	public void updateBlogpost(Blogpost blogpost) {
		{
			Session session=sessionFactory.getCurrentSession();
			System.out.println(blogpost.isApproved()+" approved ");
			session.saveOrUpdate(blogpost);//update blogpost set approved=1 wehere id=248
	        //session.flush();
	 
			System.out.println(blogpost.isApproved());
			
		}
		
	}



	public boolean saveBlogPost(Blogpost blogpost) {
		
		{
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(blogpost);
			return true;
			//session.flush();
			//session.close();

		}

	}



	



	
	


}
