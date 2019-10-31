package collaboration.backend;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dao.Blogpostdao;
import dao.Blogpostdaoimp;
import dao.Frienddao;
import dao.Frienddaoimp;
import dao.Jobdao;
import dao.Jobdaoimp;
import dao.Profilepicturedao;
import dao.Profilepicturedaoimp;
import dao.Userdao;
import dao.Userdaoimp;

import main.User;
import main.Blogcomment;
import main.Blogpost;

import main.Friend;
import main.Job;
import main.Profilepicture;


@Configuration
@EnableTransactionManagement
public class Dbconfig {
	@Bean( "datasource")
	public DataSource getOracleDatasource()
    {
    	
    	DriverManagerDataSource dt=new DriverManagerDataSource();
    	dt.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    	dt.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
    	dt.setUsername("hr");
    	dt.setPassword("oracle");
		return dt;
    }
	@Autowired
    @Bean(name="sessionFactory")
    public SessionFactory getSessionFactory() {	
    	Properties pro=new Properties();
    	pro.put("hibernate.hbm2ddl.auto","create");
    	pro.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
    	LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(getOracleDatasource());
    	builder.addProperties(pro);
    	builder.addAnnotatedClass(User.class);
    	builder.addAnnotatedClass(Blogpost.class);
    	builder.addAnnotatedClass(Blogcomment.class);
    	builder.addAnnotatedClass(Job.class);
    	builder.addAnnotatedClass(Friend.class);
    	builder.addAnnotatedClass(Profilepicture.class);
    	
    	SessionFactory session=builder.buildSessionFactory();
    	return session;
    }
    
	@Autowired
    @Bean("tx")
    public HibernateTransactionManager getHibernateTransctionManager() {
    	HibernateTransactionManager tx=new HibernateTransactionManager(getSessionFactory());
    	return tx;
    }
	 @Bean ("Userdao")
  	public Userdao getUserdao()
	  {
		  Userdaoimp a=new Userdaoimp();
		  return a;
	  }

@Bean ("blogpostdao")
	public Blogpostdao getBlogpostdao()
  {
	  Blogpostdaoimp b=new Blogpostdaoimp();
	  return b;
  }

@Bean ("Jobdao")
public Jobdao getJobdao()
{
	Jobdaoimp c=new Jobdaoimp();
	return c;
}
@Bean ("frienddao")
public Frienddao getFrienddao()
{
  Frienddaoimp e=new Frienddaoimp();
  return e;
}
@Bean ("profilepicturedao")
public Profilepicturedao getProfilepicturedao()
{
  Profilepicturedaoimp f=new Profilepicturedaoimp();
  return f;
}
}
