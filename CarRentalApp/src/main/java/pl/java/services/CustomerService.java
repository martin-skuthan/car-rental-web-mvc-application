package pl.java.services;

import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.java.comparators.CustomerFirstNameComparator;
import pl.java.comparators.CustomerLastNameComparator;
import pl.java.comparators.enums.CustomerComparatorType;
import pl.java.dao.CustomerDao;
import pl.java.exceptions.NoSuchActionException;
import pl.java.model.Car;
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
	
	public void updateCustomer(String pesel, String firstName, String lastName) {
		Customer customer = readCustomerByPesel(pesel);
		customer.setPesel(pesel);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
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
	
	public List<Customer> readRangeOfCustomers(int noOfPage, int noOfRecords, CustomerComparatorType customerComparatorType) {
		List<Customer> customers = readAllCustomers();
		if (customerComparatorType != null) {
			Comparator<Customer> comparator = getComparatorFromDescription(customerComparatorType);
			customers.sort(comparator);
		}
		
		int firstResult = noOfPage * noOfRecords - noOfRecords;
		int lastResult = firstResult + noOfRecords;
		if (lastResult > customers.size()) {
			lastResult = customers.size();
		}
		
		List<Customer> rangeCustomers = customers.subList(firstResult, lastResult);
		return rangeCustomers;
	}
	
	private Comparator<Customer> getComparatorFromDescription(CustomerComparatorType customerComparatorType) {
		switch (customerComparatorType) {
		case FIRST_NAME:
			return new CustomerFirstNameComparator();
		case LAST_NAME:
			return new CustomerLastNameComparator();
		default:
			throw new NoSuchActionException("");
		}

	}
}
