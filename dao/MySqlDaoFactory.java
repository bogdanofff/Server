package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {
	
	private String user = "root";
	private String password = "demo";
	private String url = "jdbc:mysql://localhost:3306/chat_db";
	private String driver = "com.mysql.jdbc.Driver";
	
	public MySqlDaoFactory() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public UserDao getUserDao(Connection connection) {
		return new MySqlUserDao(connection);
	}

}
