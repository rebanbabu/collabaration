package dao;

import main.User;

public interface Userdao {
	 boolean registerUser(User user);
	User validateUsername(String username);
	User validateEmail(String email);
	User login(User user);
	void update(User user);
	User getUserByUsername(String username);
}
