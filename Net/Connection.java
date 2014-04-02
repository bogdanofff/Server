package Net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Connection {
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private ObjectOutputStream oos;
	
	public Connection(Socket socket) {
		this.socket = socket;
		try {
			in = new Scanner (new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Не возможно открыть потоки I/O");
			e.printStackTrace();
		}
	}
	
	public String read() {
		String msg = in.nextLine();
		return msg;
	}
	
	public void send(String msg) {
		out.println(msg);
		out.flush();
	}
	
	public void sendObject(List<String> userList) {
		try {
			oos.writeObject(userList);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Scanner getInStream() {
		return in;
	}
	
	public PrintWriter getOutStream() {
		return out;
	}
	
	

}
