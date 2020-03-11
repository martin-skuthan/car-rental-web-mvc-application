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
	@Inject
	private EntityManager entityManager;
	
	public void createUser(User user) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
	}

	public User readUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
	}

	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
