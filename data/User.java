package data;

import net.Connection;
import ObserverInterface.Observer;
import ObserverInterface.Subject;


public class User implements Observer {
	private Connection connection;
	private IdentificationData id;
	
	public User(IdentificationData id, Connection connection, Subject messages) {
		this.id = id;
		this.connection = connection;
		messages.registerObserver(this);
	}
	
	public void send(String msg) {
		connection.send(msg);
	}
	
	public String read() {
		String msg = null;
		msg = connection.read();
		return msg;
	}
	
	@Override
	public void update(String msg) {
		send(msg);
	}
	
	public String getLogin() {
		return id.getLogin();
	}
	
	public String getPassword() {
		return id.getPassword();
	}
}
