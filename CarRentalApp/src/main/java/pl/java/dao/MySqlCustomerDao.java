package pl.java.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pl.java.exceptions.ItemWithThisIdAlreadyExistsExcpetion;
import pl.java.model.Customer;

@ApplicationScoped
@Default
public class MySqlCustomerDao implements CustomerDao {
	@Inject
	private EntityManager entityManager;
	
	public void createCustomer(Customer customer) {
		if (checkIfUserExists(customer.getPesel()) == true) {
			throw new ItemWithThisIdAlreadyExistsExcpetion("Customer with pesel:" + customer.getPesel() + " alredy exists");
		}else {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(customer);
			entityTransaction.commit();
		}	
	}

	public Customer readCustomer(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(Customer customer) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(customer);
		entityTransaction.commit();
	}

	public void deleteCustomerByPesel(String pesel) {
		Query query = entityManager.createNamedQuery("Customer.deleteCustomerByPesel");
		query.setParameter("pesel", pesel);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
	}
	
	public Customer readCustomerByPesel(String pesel) {
		TypedQuery<Customer> typedQuery = entityManager.createNamedQuery("Customer.readCustomerByPesel", Customer.class);
		typedQuery.setParameter("pesel", pesel);
		return typedQuery.getSingleResult();
	}	
	
	public boolean checkIfUserExists(String pesel) {
		TypedQuery<Customer> typedQuery = entityManager.createNamedQuery("Customer.readCustomerByPesel", Customer.class);
		typedQuery.setParameter("pesel", pesel);
		List<Customer> customers = typedQuery.getResultList();
		if (customers == null || customers.isEmpty()) {
			return false;
		}else {
			return true;
		}
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
