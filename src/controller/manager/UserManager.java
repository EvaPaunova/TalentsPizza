package controller.manager;

import java.sql.SQLException;

import exception.InvalidArgumentsException;
import model.User;
import model.dao.IUserDao;
import model.dao.UserDao;

public class UserManager {
	
	private static UserManager instance;
	private IUserDao userDao;
	
	private UserManager() {
		this.userDao = UserDao.getInstance();
	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public boolean register(User user) throws SQLException {
		userDao.addNewUser(user);
		//todo
		return false;
	}
	
	public User logIn(String username, String password) throws SQLException, InvalidArgumentsException {
		User user = null;
		if(userDao.checkUserData(username, password)) {
			user = userDao.getUserByUsername(username);
		}
		return user;
	}
	
	public void updateUser(User user) throws SQLException {
		userDao.updateUser(user);
	}

	public void deleteUser(User user) throws SQLException {
		userDao.deleteUser(user);
	}

}
