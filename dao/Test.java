package dao;

import java.sql.SQLException;

public class Test {
	
	public static void main (String[] args) {
		try {
			DaoFactory factory = new MySqlDaoFactory();
			UserDao userDao = factory.getUserDao(factory.getConnection());
//			userDao.create("bogdanov", "123456");
			userDao.read("2311");
			System.out.println(userDao.userExists("bogda"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
