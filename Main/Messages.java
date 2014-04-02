package Main;
import java.io.ObjectOutputStream;
import java.util.*;

import ObserverInterface.Observer;
import ObserverInterface.Subject;

public class Messages implements Subject {
	private final int SIZE = 100;
	private List<String> list;
	private List<Observer> observers;

	public Messages() {
		list = new LinkedList<>();
		observers = new ArrayList<>();
	}

	public synchronized void add(String msg) {
		if (msg != null) {
			if (list.size() < SIZE) {
				list.add(msg);
			} else {
				list.remove(0);
				list.add(msg);
			}
			notifyObserver(msg);
		}
	}
	
	public synchronized String _get() {
		String msg = list.get(list.size()-1);
		return msg;
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}

	@Override
	public void notifyObserver(String msg) {
		// TODO Auto-generated method stub
		for (Observer o : observers) {
			o.update(msg);
		}
	}

	@Override
	public void updateListUsers(List<User> users) {
		StringBuilder sb = new StringBuilder();
		
		for (User user : users) {
			sb.append(user.getLogin());
			sb.append("|");
		}
		
		for(Observer o : observers) {
			o.update("UpdateUserList");
			o.update(sb.toString());
//			o.update("StopUpdateUserList");
		}
		
	}
}