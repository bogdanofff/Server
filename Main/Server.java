package Main;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.omg.CORBA.portable.InputStream;

import Net.Connection;


public class Server {
	
	final static int PORT = 8189;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Start Server");
		Thread t = null;
//		Users users = new Users();
		List<User> users = new ArrayList<>();
		Messages message = new Messages();
		Runnable newSession = null;
		
		ServerSocket ss = new ServerSocket(PORT);
		while (true) {
			System.out.println("Listen...");
			Socket socket = ss.accept();
			Connection connection = new Connection(socket);
			
			newSession = new Session(connection, message, users);
			t = new Thread(newSession);
			t.start();
		}
	}
}
