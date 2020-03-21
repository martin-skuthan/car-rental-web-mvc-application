package pl.java.dao;

import pl.java.model.Role;

public interface RoleDao {
	
	public void create(Role car);
	
	public Role read(String roleName);
	
	public void update(Role roleName);
	
	public void delete(String roleName);
}
