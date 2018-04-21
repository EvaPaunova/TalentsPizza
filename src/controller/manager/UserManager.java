package controller.manager;

import java.sql.SQLException;
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
		
		return false;
	}
	
	public User logIn(String username, String password) throws SQLException {
		
		return null;
	}
	
	public void updateUser(User user) throws SQLException {
		
	}

	public void deleteUser(User user) throws SQLException {
		
	}

}
