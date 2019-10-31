package dao;

import main.Profilepicture;

public interface Profilepicturedao {
	void saveProfilePicture(Profilepicture profilepicture);

	Profilepicture getProfilepicture(String username);
	
}
