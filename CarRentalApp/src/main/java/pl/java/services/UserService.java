package pl.java.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.java.dao.UserDao;
import pl.java.model.User;

@RequestScoped
public class UserService {
	@Inject
	private UserDao userDao;
	
	public void createUser(String firstName, String lastName, String pesel) {
		
	}
	
	public void readUser(String userId) {
		
	}
	
	public void updateUser(String firstName, String lastName, String pesel) {
		
	}
	
	public void deleteUser(String userId) {
		
	}
}
