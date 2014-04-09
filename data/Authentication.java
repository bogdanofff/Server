package data;

import java.sql.SQLException;

import dao.DaoFactory;
import dao.MySqlDaoFactory;
import dao.UserDao;


public class Authentication {
	private final String AUTH_OK = "OK200";
	private final String AUTH_NO = "NO403";
	private UserDao userDao;
	private boolean state;
	private IdentificationData id;
	
	public Authentication(IdentificationData id) {
		this.id = id;
		try {
			DaoFactory factory = new MySqlDaoFactory();
			userDao = factory.getUserDao(factory.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void auth() {
		try {
			state = userDao.userExists(id.getLogin(), id.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAuth() {
		return state;
	}
	
	public String getResultCode() {
		if (state) 
			return AUTH_OK;
		else
			return AUTH_NO;
	}
}
