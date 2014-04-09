package data;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

import net.Connection;

public class Session implements Runnable {

	private Connection connection;
	private IdentificationData id;
	private Messages message;
	private List<User> users;
	private User user;
	private boolean state = true;

	public Session() {
		super();
	}

	public Session(Connection connection, Messages message, List<User> users) {
		this.connection = connection;
		this.message = message;
		this.users = users;
	}

	@Override
	public void run() {
		System.out.println("Client connected...");
		String incMsg = "";

		while (state) {
			
//			try {
				incMsg = connection.read();
//			} catch (NoSuchElementException e) {
//				System.out.println("client close form");
//				state = false;
//				message.add(id.getLogin() + ": Disconnected");
//				message.removeObserver(user);
//				users.remove(user);
//				message.updateListUsers(users);
//				connection.close();
//			}

			System.out.println("start loop");

			switch (incMsg) {

			case "registerStart":
				System.out.println("register");
				new Registration(fillPrimaryId()).register();
				connection.send("OK");
				break;

			case "authStart":
				System.out.println("auth");
				this.id = fillPrimaryId();
				Authentication a = new Authentication(id);
				a.auth();
				if (a.isAuth()) {
					user = new User(id, connection, message);
					user.send(a.getResultCode());
					user.send("Connected " + user.getLogin());
					users.add(user);
					message.updateListUsers(users);
				} else {
					connection.send(a.getResultCode());
					// connection.close();
				}
				break;

			case "0xfff":
				System.out.println("disconnect");
				state = false;
				message.add(id.getLogin() + ": Disconnected");
				message.removeObserver(user);
				users.remove(user);
				message.updateListUsers(users);
				connection.close();
				break;

			default:
				System.out.println("default");
				message.add(id.getLogin() + ": " + incMsg);
			}
		}
	}

	private IdentificationData fillPrimaryId() {
		String login = connection.read();
		String password = connection.read();
		return new IdentificationData(login, password);
	}

	private String getTime() {
		long curTime = System.currentTimeMillis();
		String curStringDate = new SimpleDateFormat("HH:mm:ss").format(curTime);
		return curStringDate;
	}
}
