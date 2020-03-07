package pl.java.dao;

import pl.java.model.User;

public interface UserDao {
	
	public void createUser(User user);
	
	public User readUser(String userId);
	
	public void updateUser(User user);
	
	public void deleteUser(String userId);	
}
