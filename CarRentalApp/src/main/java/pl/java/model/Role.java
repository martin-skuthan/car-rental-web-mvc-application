package pl.java.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = -767788221454792426L;
	
	@Id
	@Column(name = "role_name", nullable = false, unique = true)
	private String role;
	private String description;
	
	public Role() {}
	
	public Role(String role, String description) {
		this.role = role;
		this.description = description;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
