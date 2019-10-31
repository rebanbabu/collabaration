package collaboration.backend;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dao.Blogpostdao;
import dao.Frienddao;
import dao.Userdao;
import main.Blogpost;
import main.Friend;
import main.User;





/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
    {
    	 {
    		 try 
    		 {
    	
    			 ApplicationContext ctx=new AnnotationConfigApplicationContext(Dbconfig.class);
    			 SessionFactory factory =(SessionFactory)ctx.getBean("sessionFactory");
    			//Userdao a=(Userdao )ctx.getBean("Userdao");
    			Blogpostdao b=(Blogpostdao )ctx.getBean("blogpostdao");
    			//User user=new User();
    			 Blogpost blogpost=new Blogpost();
    			 blogpost.setApproved(true);
    			 blogpost.setBlogTitle("blog");
    			 blogpost.setDescription("dd");
    			 blogpost.setId(1);
    			 
    			 Frienddao e=(Frienddao )ctx.getBean("Frienddao");
    			 Friend friend=new Friend();
    			 friend.setFromId("1");
    			 friend.setId(1);
    			 friend.setToId("yes");
    			 
    			/*user.setEmail("trebanbabu27@gmail.com");
    			user.setFirstname("reban");
    			user.setLastname("babu");
    			user.setPassword("123");
    			user.setPhonenumber("9047013633");
    			user.setOnline(true);
    			user.setUsername("reban");
    			user.setRole("user");*/
    			/*if(a.registerUser(user))
    	    	 {
    	    		 System.out.println("user added");
    	    	 }*/
    			/*if(b.saveBlogPost(blogpost))
   	    	 {
   	    		 System.out.println("blogpost added");
   	    	 }*/
    			 if(e.addFriendRequest(friend))
       	    	 {
       	    		 System.out.println("friend added");
       	    	 }
        	
    	    		 
    			
    		 }
    			 catch (Throwable e) 
        		 {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        		 }
    		 }
    	 
    }
        	
   			
   				
   			
}