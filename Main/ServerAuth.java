package Main;

import java.sql.SQLException;

import dao.DaoFactory;
import dao.MySqlDaoFactory;
import dao.UserDao;

public class ServerAuth {
	private final String AUTH_OK = "OK200";
	private final String AUTH_NO = "NO403";
	private UserDao userDao;
	private boolean state;
	
	public ServerAuth() {
		System.out.println("конструктор ServerAuth");
		try {
			DaoFactory factory = new MySqlDaoFactory();
			userDao = factory.getUserDao(factory.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		auth(user.getLogin(), user.getPassword());
	}
	
	public void auth(User user) {
		try {
			System.out.println(user.getLogin());
			System.out.println(user.getPassword());
			state = userDao.userExists(user.getLogin(), user.getPassword());
			System.out.println(state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
