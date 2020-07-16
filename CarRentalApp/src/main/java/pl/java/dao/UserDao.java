package pl.java.dao;

import java.util.List;

import pl.java.model.User;

public interface UserDao {
	
	public void create(User user);
	
	public User read(String userId);
	
	public void update(User user);
	
	public void delete(String userId);
	
	public User readUserByUsername(String username);
	
	public List<User> readAllUsers();
	
	public List<User> readRangeOfUser();
	
	public boolean checkIfUserExists(String username);
}
