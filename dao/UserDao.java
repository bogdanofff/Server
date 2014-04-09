package dao;

import java.sql.SQLException;

import data.User;

public interface UserDao {
	
	public void create(User user) throws SQLException;
	
	public void create(String login, String password) throws SQLException;
	
	public void read(String login) throws SQLException;
	
	public boolean userExists(String login) throws SQLException;
	
	public boolean userExists(String login, String password) throws SQLException;
	
}
