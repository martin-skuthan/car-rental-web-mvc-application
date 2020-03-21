package pl.java.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.java.dao.UserDao;
import pl.java.model.User;

@RequestScoped
public class UserService {
	@Inject
	private UserDao userDao;
	
	public void createUser(String username, String mail, String password) {
		User user = new User(username, mail, password);
		user.setActive(true);
		userDao.create(user);
	}
	
	public User readUser(String userId) {
		return null;
	}
	
	public User readUserByUsername(String username) {
		User user = userDao.readUserByUsername(username);
		return user;
	}
	
	public void updateUser(String firstName, String lastName, String pesel) {
		
	}
	
	public void deleteUser(String userId) {
		
	}
}
