package data;

import java.sql.SQLException;

import dao.DaoFactory;
import dao.MySqlDaoFactory;
import dao.UserDao;

public class Registration {
	
	private UserDao userDao;
	private IdentificationData id;
	
	public Registration(IdentificationData id) {
		this.id = id;
		try {
			DaoFactory factory = new MySqlDaoFactory();
			userDao = factory.getUserDao(factory.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void register() {
		try {
			if(!userDao.userExists(id.getLogin())) {
				userDao.create(id.getLogin(), id.getPassword());
				System.out.println("ѕользователь успешно зарегестрирован");
			} else {
				System.out.println("ѕользователь с таким именем уже существует");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
