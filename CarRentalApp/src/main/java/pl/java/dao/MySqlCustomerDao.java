package pl.java.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.java.model.Customer;

@RequestScoped
@Default
public class MySqlCustomerDao implements CustomerDao {
	@Inject
	private EntityManager entityManager;
	
	public void createCustomer(Customer user) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
	}

	public Customer readCustomer(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(Customer user) {
		// TODO Auto-generated method stub
	}

	public void deleteCustomer(String userId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
