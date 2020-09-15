package pl.java.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.java.dao.UserDao;
import pl.java.model.User;

@RequestScoped
public class UserService {
	@Inject
	private UserDao userDao;
	
	public void createUser(String username, String mail, String password) {
		User user = new User();
		user.setUsername(username);
		user.setMail(mail);
		String encyptedPassword = encryptPassword(password);
		user.setPassword(encyptedPassword);
		user.setActive(true);
		userDao.create(user);
	}
	
	private String encryptPassword(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(password.getBytes());
		String md5Password = new BigInteger(1, digest.digest()).toString(16);
		return md5Password;
	}
		
	public User readUser(String userId) {
		return null;
	}
	
	public User readUserByUsername(String username) {
		User user = userDao.readUserByUsername(username);
		return user;
	}
	
	public void updateUser(String firstName, String lastName, String pesel) {
		
	}
	
	public void deleteUser(String userId) {
		
	}
}
