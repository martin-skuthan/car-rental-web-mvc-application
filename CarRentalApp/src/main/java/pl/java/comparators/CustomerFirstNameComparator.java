package pl.java.comparators;

import java.util.Comparator;

import pl.java.model.Customer;

public class CustomerFirstNameComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer customer1, Customer customer2) {
		if (customer1.getFirstName().compareTo(customer2.getFirstName()) == 0) {
			return customer1.getLastName().compareTo(customer2.getLastName());
		}
		
		return customer1.getFirstName().compareTo(customer2.getFirstName());
	}

}
