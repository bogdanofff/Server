package net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	
	public Connection(Socket socket) {
		this.socket = socket;
		try {
			in = new Scanner (new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
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
