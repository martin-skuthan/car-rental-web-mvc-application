package pl.java.dao;

import java.util.ArrayList;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.java.model.Role;
import pl.java.model.User;


@ApplicationScoped
@Default
public class MySqlUserDao implements UserDao {
	@Inject
	private EntityManager entityManager;
	@Inject
	private RoleDao roleDao;

	public void create(User user) {
		addAdminRoleToUser(user);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
	}

	public User read(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String userId) {
		// TODO Auto-generated method stub
		
	}
	
	public User readUserByUsername(String username) {
		User user = null;
		List<User> users = readAllUsers().stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
		if (users.size() != 0) {
			user = users.get(0);
		}
		return user;
	}
	
	public List<User> readAllUsers() {
		List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
		return users;
	}
	
	public List<User> readRangeOfUser() {
		return null;
	}
	
	private void addAdminRoleToUser(User user) {
		List<Role> roles = new ArrayList<Role>();
		final String adminRoleName = "admin";
		Role role = null;
		if ((role = roleDao.read(adminRoleName)) == null) {
			role = new Role(adminRoleName, "All privilages");
			roleDao.create(role);
		}
		roles.add(role);
		user.setRoles(roles);
	}
}
