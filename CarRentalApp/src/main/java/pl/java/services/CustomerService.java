package pl.java.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.java.dao.CustomerDao;
import pl.java.model.Customer;

@RequestScoped
public class CustomerService {
	@Inject
	private CustomerDao customerDao;
	
	public void createCustomer(String firstName, String lastName, String pesel) {
		Customer user = new Customer(firstName, lastName, pesel);
		customerDao.createCustomer(user);
	}
	
	public void readUser(String userId) {
		
	}
	
	public void updateUser(String firstName, String lastName, String pesel) {
		
	}
	
	public void deleteUser(String userId) {
		
	}
}
