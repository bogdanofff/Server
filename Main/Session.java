package Main;

import java.io.*;
import java.net.Socket;
import java.util.*;

import Net.Connection;

public class Session implements Runnable {

	private Connection connection;
	private Messages message;
	private List<User> users;

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
		String fMsg = connection.read();
		
		if (fMsg.equals("registerStart")) {
			// TODO регистрация в бд нового пользователя
			System.out.println("Заглушка, регистрация нового пользователя");
			String login = connection.read();
			System.out.println("Логин - " + login);
			String password = connection.read();
			System.out.println("Пароль - "+ password);
			new Registration().register(login, password);
			connection.send("OK");
			fMsg = connection.read();
		}
		
		if (fMsg.equals("authStart")) {
			
			User user = new User(connection, message);
			ServerAuth auth = new ServerAuth();
			auth.auth(user);

			// Успешная авторизация

			if (auth.isAuth()) {
				user.send(auth.getResultCode());
				user.send("Connected " + user.getLogin());
				users.add(user);
				message.updateListUsers(users); // отсылаем список людей в чате
				listen(user);

				// Ошибка авторизации

			} else {
				user.send(auth.getResultCode());
				System.out.println("Close...");
				connection.close();
				user = null;
			}

		}

	}

	public void listen(User user) {
		String msg = null;
		String login = user.getLogin();
		while (true) {
			msg = user.read();
			if (msg.equals("0xfff")) {
				message.add(login + ": Disconnected");
				message.removeObserver(user);
				users.remove(user);
				message.updateListUsers(users);
				connection.close();
			} else {
				message.add(login + ": " + msg);
			}
		}
	}
}
