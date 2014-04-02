package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.User;

public class MySqlUserDao implements UserDao {
	private final Connection connection;
	
	public MySqlUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(User user) throws SQLException {
		String sql = "INSERT INTO `users` (`login`, `password`) VALUES (? , ?)";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.executeUpdate();
		stm.setString(1, user.getLogin());
		stm.setString(2, user.getPassword());
	}

	@Override
	public void read(String login) throws SQLException {
		String sql = "SELECT * FROM `users` WHERE `login` = ?";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.setString(1, login);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			int id = rs.getInt(1);
			System.out.println(id);
		}
		
	}

	@Override
	public void create(String login, String password) throws SQLException {
		String sql = "INSERT INTO `users` (`login`, `password`) VALUES (? , ?)";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.setString(1, login);
		stm.setString(2, password);
		stm.executeUpdate();
	}

	@Override
	public boolean userExists(String login) throws SQLException {
		String sql = "SELECT * FROM `users` WHERE `login` = ?";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.setString(1, login);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean userExists(String login, String password) throws SQLException {
		String sql = "SELECT * FROM `users` WHERE `login` = ? AND `password` = ?";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.setString(1, login);
		stm.setString(2, password);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			return true;
		}
		return false;
	}
}
