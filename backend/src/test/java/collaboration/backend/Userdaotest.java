package collaboration.backend;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import dao.Userdao;

import main.User;




public class Userdaotest 
{
	
	public static ApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		context=new AnnotationConfigApplicationContext(Dbconfig.class);
	}
@Test
	public void testaddUser() 
	{
		Userdao dao=(Userdao)context.getBean("userdao");
		User a=new User();
		a.setEmail("trebanbabu27@gmail.com");
		a.setFirstname("reban");
		a.setLastname("babu");
		a.setPhonenumber("9843582772");
		a.setPassword("123");
		a.setRole("user");
		a.setOnline(true);
				a.setUsername("reban");
		
	}
	
}
