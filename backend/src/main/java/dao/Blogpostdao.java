package dao;

import java.util.List;

import main.Blogcomment;
import main.Blogpost;

public interface Blogpostdao {
	boolean saveBlogPost(Blogpost blogpost);

	List<Blogpost> getBlogposts(int approved);
	
	Blogpost getBlogpostById(int id);

	void updateBlogpost(Blogpost blogpost);

	void addBlogcomment (Blogcomment blogcomment);
	
	List<Blogcomment> getAllBlogcomments(int blogpostId);

}
