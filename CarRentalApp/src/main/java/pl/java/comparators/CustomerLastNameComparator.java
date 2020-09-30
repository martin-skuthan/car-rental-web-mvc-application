package pl.java.comparators;

import java.util.Comparator;

import pl.java.model.Customer;

public class CustomerLastNameComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer customer1, Customer customer2) {
		return customer1.getLastName().compareTo(customer2.getLastName());
	}

}
