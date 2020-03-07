package pl.java.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.java.model.User;

@RequestScoped
@Default
public class MySqlUserDao implements UserDao {
	
	public void createUser(User user) {
		
	}

	public User readUser(String userId) {

	}

	public void updateUser(User user) {

	}

	public void deleteUser(String userId) {
	
	}
	
	
}
