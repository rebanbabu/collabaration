package dao;

import java.util.List;

import main.Friend;
import main.User;

public interface Frienddao {
	List<User> getListOfSuggestedUsers(String username);

	void addFriendRequest(String username, String toId);

	List<Friend> getPendingRequests(String username);

	void updatePendingRequest(Friend pendingRequest);

	List<Friend> listOfFriends(String username);
}
