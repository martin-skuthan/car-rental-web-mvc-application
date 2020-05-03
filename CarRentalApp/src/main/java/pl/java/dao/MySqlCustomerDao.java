package pl.java.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

	public void deleteCustomerByPesel(String pesel) {
		Query query = entityManager.createNamedQuery("Customer.deleteCustomerByPesel");
		query.setParameter("pesel", pesel);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
	}
	
	public List<Customer> readAllCustomers() {
		TypedQuery<Customer> typedQuery = entityManager.createNamedQuery("Customer.readAllCustomers", Customer.class);
		List<Customer> customers = typedQuery.getResultList();
		return customers;
	}
	
	public List<Customer> readRangeOfCustomers(int noOfRecords, int firstResults) {
		TypedQuery<Customer> typedQuery = entityManager.createNamedQuery("Customer.readAllCustomers", Customer.class).
				                          setFirstResult(firstResults).setMaxResults(noOfRecords);
		List<Customer> customers = typedQuery.getResultList();
		return customers;
	}	
}
