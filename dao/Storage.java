package dao;
import java.sql.*;


public class Storage {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	final private String conString = "jdbc:mysql://localhost:3306/chat_db?user=root&password=demo";
	final private String SQL_addUser = "INSERT INTO `users` (`login`, `password`) VALUES (? , ?)";
	final private String SQL_isUser = "SELECT * FROM `users` WHERE `login` = '?'";
	
	public Connection getConnection() throws SQLException {
		connection = DriverManager.getConnection(conString);
		return connection;
	}
	
	public boolean isExistUser(String login) throws SQLException {
		ps = connection.prepareStatement(SQL_isUser);
		ps.setString(1, login);
		rs = ps.executeQuery();
		
		return rs.next();
	}
	
	public void addUser (String login, String password) throws SQLException {
		ps = connection.prepareStatement(SQL_addUser);
		ps.setString(1, login);
		ps.setString(2, password);
		ps.executeUpdate();
	}
}
