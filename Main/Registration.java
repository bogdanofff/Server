package Main;

import java.sql.Connection;
import java.sql.SQLException;

import dao.DaoFactory;
import dao.MySqlDaoFactory;
import dao.UserDao;

public class Registration {
	
	private UserDao userDao;

	public Registration() {
		try {
			DaoFactory factory = new MySqlDaoFactory();
			userDao = factory.getUserDao(factory.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void register(String login, String password) {
		System.out.println(login + "  " +  password);

		try {
			if(!userDao.userExists(login)) {
				userDao.create(login, password);
				System.out.println("ѕользователь успешно зарегестрирован");
			} else {
				System.out.println("ѕользователь с таким именем уже существует");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
