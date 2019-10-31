package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.Profilepicture;
@Repository
@Transactional
public class Profilepicturedaoimp implements Profilepicturedao {
	@Autowired
	SessionFactory sessionFactory;
	
	public Profilepicture getProfilepicture(String username) {
		Session session=sessionFactory.getCurrentSession();
		//select * from profilepicture where username='admin'
		Profilepicture profilepicture=(Profilepicture)session.get(Profilepicture.class, username);
		//session.close();
		return profilepicture;
	}

	public void saveProfilePicture(Profilepicture profilepicture) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilepicture);
		//session.flush();
		//session.close();
		
		
	}
}
