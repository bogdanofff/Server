package Main;
import java.io.PrintWriter;
import java.util.List;

import Net.Connection;
import ObserverInterface.Observer;
import ObserverInterface.Subject;


public class User implements Observer {
	private String login;
	private String password;
	private Connection connection;
	private Subject messages;
	
	public User(Connection connection, Subject messages) {
		this.connection = connection;
		this.login = connection.read();
		this.password = connection.read();
		this.messages = messages;
		messages.registerObserver(this);
	}
	
	public void send(String msg) {
		System.out.println("Sending to client..." + msg);
		connection.send(msg);
	}
	
	public String read() {
		String msg = null;
		msg = connection.read();
		return msg;
	}
	
	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		send(msg);
		System.out.println("User " + login + " recieve message " +  msg);
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public void update(List<String> userList) {
		// TODO Auto-generated method stub
		connection.sendObject(userList);
	}
}
