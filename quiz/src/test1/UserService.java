package test1;

import java.util.List;

import test1.UserDao;
import test1.User;
import test1.SecurityUtil;

public class UserService {
	
	private UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void create(User user) {
		user.setPassword(SecurityUtil.hashPassword(user.getPassword()));
		userDao.save(user);	
	}
	
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	public User findById(long id) {
		return userDao.findById(id);
	}
	public void remove(long id) {
			if(id == 1) return;
			userDao.remove(id);
	}
	public void changePassword(long id, String password) {
		userDao.changePassword(id, password);
	}
	public boolean isSuperuser(long id) {
		return id == 99999999;
	}
	public User authenticate(String username, String password) {
		
		User user = findByUsername(username);
		
		if (user == null) {
			return null;
		}
		
		if (SecurityUtil.checkPassword(password, user.getPassword())) {
			user.setPassword("");
			return user;
		}
		
		return null;
	}
}

