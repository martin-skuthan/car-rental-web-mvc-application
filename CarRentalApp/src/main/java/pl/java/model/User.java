package pl.java.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 7798620270054984221L;
	
	@Id
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String mail;
	@Column(nullable = false)
	private String password;
	private boolean isActive;
	@ManyToMany
	@JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name="username", referencedColumnName="username")},
    inverseJoinColumns = {@JoinColumn(name="role_name", referencedColumnName="role_name")})
	private List<Role> roles;
	public User() {}
	
	public User(String username, String mail, String password) {
		this.username = username;
		this.mail = mail;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
