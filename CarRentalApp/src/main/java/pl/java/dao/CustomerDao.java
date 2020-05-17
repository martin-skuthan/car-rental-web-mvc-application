package pl.java.dao;

import java.util.List;

import pl.java.model.Customer;

public interface CustomerDao {
	
	public void createCustomer(Customer user);
	
	public Customer readCustomer(String userId);
	
	public void updateCustomer(Customer user);
	
	public void deleteCustomerByPesel(String pesel);	
	
	public List<Customer> readAllCustomers();
	
	public List<Customer> readRangeOfCustomers(int noOfRecords, int firstResults);
	
	public Customer readCustomerByPesel(String pesel);
}
