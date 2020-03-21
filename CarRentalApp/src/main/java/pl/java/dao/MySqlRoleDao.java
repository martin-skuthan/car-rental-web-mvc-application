package pl.java.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.java.model.Role;

@ApplicationScoped
@Default
public class MySqlRoleDao implements RoleDao {
	@Inject
	private EntityManager entityManager;

	@Override
	public void create(Role role) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(role);
		entityTransaction.commit();
	}

	@Override
	public Role read(String roleName) {
		Role role = entityManager.find(Role.class, roleName);
		return role;
	}

	@Override
	public void update(Role roleName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String roleName) {
		// TODO Auto-generated method stub
		
	}
}
