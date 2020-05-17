package pl.java.services;

import java.util.List;

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
	
	public void readCustomer(String userId) {
		
	}
	
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}
	
	public void deleteCustomerByPesel(String pesel) {
		customerDao.deleteCustomerByPesel(pesel);
	}
	
	public Customer readCustomerByPesel(String pesel) {
		Customer customer = customerDao.readCustomerByPesel(pesel);
		return customer;
	}
	
	public List<Customer> readAllCustomers() {
		List<Customer> customers = customerDao.readAllCustomers();
		return customers;
	}
	
	public List<Customer> readRangeOfCustomers(int noOfPage, int noOfRecords) {
		int firstResult = noOfPage * noOfRecords - noOfRecords;
		List<Customer> customers = customerDao.readRangeOfCustomers(noOfRecords, firstResult);
		return customers;
	}
}
