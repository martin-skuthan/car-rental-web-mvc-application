package pl.java.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Customer.readAllCustomers", query = "SELECT c FROM Customer c"),
	@NamedQuery(name = "Customer.deleteCustomerByPesel", query = "DELETE FROM Customer c WHERE c.pesel = :pesel")
})
public class Customer implements Serializable {
	private static final long serialVersionUID = 3943117829271152503L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(nullable = false, length = 13, unique = true)
	private String pesel;
	
	public Customer() {}
	
	public Customer(String firstName, String lastName, String pesel) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPesel() {
		return pesel;
	}
	
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}	
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " ,with pesel: " + pesel;
	}
}
